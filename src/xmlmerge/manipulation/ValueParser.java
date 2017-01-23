/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlmerge.manipulation;

import java.util.ArrayList;
import java.util.logging.Level;
import rslogger.RSLogger;
import xmlmerge.data.XMLEntry;
import xmlmerge.search.Namecheck;
import xmlmerge.search.Searcher;

/**
 * This class searches for tags, attributes, values etc..
 * (via Namecheck class instances) and tries to parse a double value if all
 * tags match.
 * With the help of one or more ValueChanger instances, it can perform mathematical
 * operations and restores the parsed value back to it's origin.
 * The mathematical operations will be executed in the order the valueChanger
 * instances are added (via the function ValueParser.addValueChanger())
 * @author Richard
 */
public class ValueParser implements Searcher {
  
  protected final ArrayList<Namecheck> tagChecks;
  protected final Namecheck attributeNameCheck;
//  private final double value;
  protected double parsedValue;
  
  protected final ArrayList<ValueChanger> valueChanger;
  
  public ValueParser(Namecheck tagCheck, Namecheck attributeNameCheck) throws NullPointerException {
    this.tagChecks = new ArrayList<>();
    this.valueChanger = new ArrayList<>();
    this.tagChecks.add(tagCheck);
    if (attributeNameCheck == null)
    {
      throw new NullPointerException("Attribute Name Check may not be null");
    }
    this.attributeNameCheck = attributeNameCheck;
//    this.value = value;
    this.parsedValue = 0;
  }
  
  /**
   * Construct a new ValueChanger with the help of an existing one.
   * tagCheck and attributeNameCheck references are copied, but a new value will be assigned
   * also, no valueChanger will be copied, must be added again
   * @param changer
   * @param value 
   */
  public ValueParser(ValueParser changer) {
    this.tagChecks = new ArrayList<>();
    this.valueChanger = new ArrayList<>();
    this.tagChecks.addAll(changer.tagChecks);
    this.attributeNameCheck = changer.attributeNameCheck;
//    this.value = value;
  }
  
  public double getParsedValue() {
    return this.parsedValue;
  }
  
  public void setValue(double value) {
    this.parsedValue = value;
  }
  
  public void addValueChanger(ValueChanger c) {
    this.valueChanger.add(c);
  }
  
  public void removeValueChanger(ValueChanger c) {
    this.valueChanger.remove(c);
  }
  
  protected int parseValue(XMLEntry e) {
    int retVal = 0;
    this.attributeNameCheck.search(e);
    retVal = this.attributeNameCheck.getAttributeMatchPosition();
    
    if (retVal != -1)
    {
      try {
        this.parsedValue = Double.parseDouble(e.getAttributes().get(retVal).getValue());
        
        for (ValueChanger changer : this.valueChanger)
        {
          this.parsedValue = changer.changeValue(this.parsedValue);
        }
//        this.parsedValue += this.value;
        e.getAttributes().get(retVal).setValue(Double.toString(this.parsedValue));
      }
      catch (NumberFormatException nfex)
      {
        RSLogger.getLogger().log(Level.WARNING, "Node {0} does not contain a number", e.getName());
      }
    }
    return retVal;
  }

  @Override
  public int search(XMLEntry entry) {
    int number = 0;
    for (Namecheck check : this.tagChecks)
    {
      if (check.search(entry))
        number += 1;
    }
    if (number == this.tagChecks.size())
      return this.parseValue(entry);
    else
      return 0;
  }
  
  @Override
  public void addNamecheck(Namecheck n) {
    this.tagChecks.add(n);
  }
  
}
