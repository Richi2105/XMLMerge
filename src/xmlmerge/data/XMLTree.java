/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlmerge.data;

import java.util.logging.Level;
import nu.xom.Document;
import nu.xom.Element;
import org.vishia.xmlSimple.XmlException;
import org.vishia.xmlSimple.XmlNode;
import rslogger.RSLogger;
import xmlmerge.xmlReader.XMLReader;


/**
 *
 * @author Richard
 */
public class XMLTree {
    
  private XMLEntry root;
  
  public XMLTree () {
  }
  
  public void fill(Document doc) {
    root = new XMLEntry(doc.getRootElement(), null);
  }
  
  public void fill(Document doc, XMLReader reader) {
    root = new XMLEntry(doc.getRootElement(), null, reader);
  }
  
  public void add(Document doc, XMLReader source) {
    root.add(doc.getRootElement(), source);
  }
  
  public Element write() {
    return root.toElement();
  }
  
  public XmlNode writeSimpleXML() {
    try {
      return root.toSimpleXML();
    } catch (XmlException ex) {
      RSLogger.getLogger().log(Level.SEVERE, null, ex);
      return null;
    }
  }
  
  public void write(Document doc) {    
    doc.setRootElement(root.toElement());
  } 
  
  public XMLEntry getRoot() {
    return root;
  }
  
}
