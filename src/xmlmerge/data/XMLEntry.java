/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlmerge.data;

import java.util.ArrayList;
import java.util.logging.Level;
import nu.xom.Attribute;
import nu.xom.Element;
import nu.xom.Text;
import org.vishia.xmlSimple.XmlException;
import org.vishia.xmlSimple.XmlNode;
import org.vishia.xmlSimple.XmlNodeSimple;
import rslogger.RSLogger;
import static xmlmerge.data.XMLEntry.BIT_NAME;
import xmlmerge.xmlReader.XMLReader;

/**
 *
 * @author Richard
 */
public class XMLEntry {
  
  private class AttributeDifference {
    public int bits;
    public int position;
  }
  
  private final String name;
  private final ArrayList<XMLEntry> attributes;
  private String value;
  private final XMLEntry parent;
  private final ArrayList<XMLEntry> subEntries;
  
  /**
   * Bit identifier for name
   */
  public final static int BIT_NAME = 1 << 0;
  
  /**
   * Bit identifier for value
   */
  public final static int BIT_VALUE = 1 << 1;
  
  /**
   * Bit identifier for Attribute name
   */
  public final static int BIT_ATTRIBUTE_NAME = 1 << 2;
  
  /**
   * Bit identifier for Attribute value
   */
  public final static int BIT_ATTRIBUTE_VALUE = 1 << 3;
  
  /**
   * Bit identifier for Parent name
   */
  public final static int BIT_PARENT_NAME = 1 << 4;
  
  /**
   * Bit identifier for Parent value
   */
  public final static int BIT_PARENT_VALUE = 1 << 5;
  
  /**
   * Bit identifier for Parent Attribute name
   */
  public final static int BIT_PARENT_ATTRIBUTE_NAME = 1 << 6;
  
  /**
   * Bit identifier for Parent Attribute value
   */
  public final static int BIT_PARENT_ATTRIBUTE_VALUE = 1 << 7;
  
  
  /**
   * Construct XMLEntry as Attribute
   * @param parent the parent node
   * @param name Attribute's name
   * @param value Attribute's value
   */
  public XMLEntry (XMLEntry parent, String name, String value) {
    this.parent = parent;
    this.name = name;
    this.value = value;
    this.attributes = null;
    this.subEntries = null;
  }
  
  /**
   * Construct XMLEntry as Node
   * @param e the element to be representatet by this instance
   * @param parent the parent node
   * @param depth the max depth to go into child nodes
   * 0: no limit, 1: get only this node (including attributes)
   * 2: include first level of children and so on
   * @param reader the source, is needed to construct children
   */
  public XMLEntry (Element e, XMLEntry parent, int depth, XMLReader reader) {
    this.parent = parent;
    this.name = e.getLocalName();
    this.value = "";
    this.attributes = new ArrayList<>();
    this.subEntries = new ArrayList<>();
    
    
    for (int i = 0; i<e.getChildCount(); i+=1)
    {
      if (e.getChild(i) instanceof Element)
      {
        if (reader == null) {
          break;
        }
        XMLEntry newChild = null;
        if (depth == 0)
        {
          newChild = reader.getNewEntry((Element) e.getChild(i), this, 0);
        }
        else if (depth > 1)
        {
          newChild = reader.getNewEntry((Element) e.getChild(i), this, --depth);
        }
        if (newChild != null)
        {
          subEntries.add(newChild);
        }
      }
      else if (e.getChild(i) instanceof Text)
      {
        this.value = e.getChild(i).getValue();
      }
      else {
        RSLogger.getLogger().log(Level.WARNING, "unknown XML Node type");
      }
    }

    for (int i = 0; i < e.getAttributeCount(); i+=1)
    {
      Attribute a = e.getAttribute(i);
      this.attributes.add(new XMLEntry(this, a.getLocalName(), a.getValue()));
    }
  }
  
  /**
   * Construct XMLEntry as Node
   * @param e the element to be representatet by this instance
   * @param parent the parent node
   * @param reader the source, is needed to construct children
   */
  public XMLEntry (Element e, XMLEntry parent, XMLReader reader) {
    this(e, parent, 0, reader);
  }
  
  /**
   * Construct XMLEntry as Node with depth of 1 (only this entry and attributes)
   * @param e
   * @param parent 
   */
  public XMLEntry (Element e, XMLEntry parent) {
    this(e, parent, 0, null);
  }
  
  /**
   * checks this XMLEntry for difference and recursively adds missing data
   * @param e an Element to be added to this XMLEntry
   * @param source an XMLReader needed for constructing a XMLEntry out of the Element e
   */
  public void add(Element e, XMLReader source) {
    if (!this.name.equals(e.getLocalName()))
    {
      return;      
    }
    
    for (int i = 0; i<e.getChildCount(); i+=1)
    {
      if (e.getChild(i) instanceof Element)
      {
        //XMLEntry newChild = new XMLEntry((Element) e.getChild(i), this, 1, null); //TODO: construct via reader, check if null!!!
        XMLEntry newChild = source.getNewEntry((Element) e.getChild(i), this, 1);
        if (newChild != null) {
          boolean writeChild = true;
          for (XMLEntry child : subEntries)
          {
            int diff = child.checkDifference(newChild);
            if (diff == 0)
            {
              child.add((Element) e.getChild(i), source);
              writeChild = false;
              break;  //Todo: test
            }
          }
          if (writeChild)
          {
              XMLEntry newChildComplete = source.getNewEntry((Element) e.getChild(i), this, 0);
              if (newChildComplete != null)
              {
                subEntries.add(newChildComplete);
                /*
                if (newChildComplete.attributes != null) {
                  if (!newChildComplete.attributes.isEmpty()) {
                    if (this.attributes == null) {
                      RSLogger.getLogger().log(Level.WARNING, "this node {0} is an attribute", this.name);
                    }
                    else {
                      this.attributes.addAll(this.getDifferentAttributes(newChildComplete));
                    }
                  }
                }
                */
              }
          }
        }
      }
      else if (e.getChild(i) instanceof Text)
      {
        this.value = e.getChild(i).getValue();
      }
      else {
        RSLogger.getLogger().log(Level.WARNING, "unknown XML Node type");
      }
    }
/*    
    XMLEntry attCheck = new XMLEntry(e, null, 1, null);
    if (attCheck.attributes != null) {
      if (!attCheck.attributes.isEmpty()) {
        if (this.attributes == null) {
          RSLogger.getLogger().log(Level.WARNING, "this node {0} is an attribute", this.name);
        }
        else {
          this.attributes.addAll(this.getDifferentAttributes(attCheck));
        }
      }
    }
*/
  }
  
  private ArrayList<XMLEntry> getDifferentAttributes(XMLEntry e) {
    ArrayList<XMLEntry> retVal = new ArrayList<>();
    for (XMLEntry otherAtt : e.attributes)
    {
      boolean add = true;
      for (XMLEntry att : this.attributes)
      {
        if ( (att.checkDifference(otherAtt) & XMLEntry.BIT_NAME) == 0)
        {
          add = false;
          break;
        }
      }
      if (add)
      {
        retVal.add(otherAtt);
      }
    }
    return retVal;
  }
  
  /**
   * checks Attribute Difference:
   * compares the first attribute if both attributes contain members
   * 
   * if this entry has no attributes, but other XMLEntry has attributes
   *    BIT_ATTRIBUTE_NAME + BIT_ATTRIBUTE_VALUE is returned
   * 
   * if other XMLEntry has no attributes, (wether this one has or has not)
   *    0 is returned
   * 
   * if both have attributes:
   *    check if first attribte differ from one another, and proper bits are returned
   * 
   * @param e the other XMLEntry which attribute is compared
   * @return 
   */
  private int checkAttributeDifference(XMLEntry e) {
    int retVal = 0;
    if (this.attributes == null) {
      if (e.attributes != null) {
        retVal = (BIT_ATTRIBUTE_NAME | BIT_ATTRIBUTE_VALUE);
        return retVal;
      }
      else {
        retVal = 0;
        return retVal;
      }
    }
/*
    else if (this.attributes != null) {
      if (e.attributes == null) {
        retVal = (BIT_ATTRIBUTE_NAME | BIT_ATTRIBUTE_VALUE);
      }
    }
*/    
    if (this.attributes != null && e.getAttributes() != null)
    { 
      if (this.attributes.isEmpty()) {
        if (!e.attributes.isEmpty()) {
          retVal = (BIT_ATTRIBUTE_NAME | BIT_ATTRIBUTE_VALUE);
          return retVal;
        }
      }
/*
      else if (!this.attributes.isEmpty()) {
        if (e.attributes.isEmpty()) {
          retVal = (BIT_ATTRIBUTE_NAME | BIT_ATTRIBUTE_VALUE);
        }
      }
*/      
      if (!this.attributes.isEmpty() && !e.attributes.isEmpty())
      {
        if (this.attributes.size() != e.attributes.size()) {
          retVal = (BIT_ATTRIBUTE_NAME | BIT_ATTRIBUTE_VALUE);
          return retVal;
        }
        else {
          for (int i=0; i<this.attributes.size(); i+=1) {
            retVal = this.attributes.get(i).checkDifference(e.attributes.get(i)) << 2;
            if (retVal != 0) {
              return retVal;
            }
          }
        }
        
      }
    }
    return retVal;
  }
  
  /**
   * checks the name, value for equality
   * @param e the XMLEntry to check
   * @return a bitfield containing the differentials in name, value
   * @see BIT_NAME, BIT_VALUE, ...
   */
  public int checkDifference(XMLEntry e) {
    int retVal = 0;
    if (!this.name.equals(e.getName())) {
      retVal |= BIT_NAME;
    }
    if (!this.value.equals(e.getValue())) {
      retVal |= BIT_VALUE;
    }
    retVal |= this.checkAttributeDifference(e);
    //Todo: check via checkAttributeDifference, different attributes->new entry!
    return retVal;
  }
  
  public XmlNode toSimpleXML() throws XmlException {
    XmlNodeSimple thisElement = new XmlNodeSimple(this.name);
    if (!this.value.isEmpty())
    {
      thisElement.addContent(value);
    }
    if (!this.attributes.isEmpty())
    {
      for (XMLEntry att : attributes)
      {
        thisElement.setAttribute(att.getName(), att.getValue());
      }
    }
    
    if (!this.subEntries.isEmpty())
    {
      for (XMLEntry child : subEntries)
      {
        thisElement.addContent(child.toSimpleXML());
      }
    }
    return thisElement;
  }
  
  /**
   * builds an Element including children
   * @return an Element which represents this XMLEntry
   */
  public Element toElement() {
    Element thisElement = new Element(name);
    if (!this.value.isEmpty())
    {
      thisElement.appendChild(value);
    }
    if (!this.attributes.isEmpty())
    {
      for (XMLEntry att : attributes)
      {
        thisElement.addAttribute(new Attribute(att.getName(), att.getValue()));
      }
    }
    
    if (!this.subEntries.isEmpty())
    {
      for (XMLEntry childs : subEntries)
      {
        thisElement.appendChild(childs.toElement());
      }
    }
    return thisElement;    
  }
  
  public String getName() {
    return this.name;
  }
  
  public String addPrefix(String prefix) {
    this.value = prefix + this.value;
    return this.value;
  }
  
  public void setValue(String value) {
    this.value = value;
  }
  
  public String getValue() {
    return this.value;
  }
  
  public ArrayList<XMLEntry> getAttributes() throws NullPointerException {
    if (this.attributes == null)
    {
      throw new NullPointerException("This is no Node, just an Attribute");
    }
    else
    {
      return this.attributes;
    }
  }
  
  public XMLEntry getParent() throws NullPointerException {
    if (this.parent == null)
    {
      throw new NullPointerException("This is the root node");
    }  
    else
    {
      return this.parent;
    }
  }
  
}
