/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlmerge.manipulation;

import java.util.logging.Level;
import rslogger.RSLogger;
import xmlmerge.data.XMLEntry;
import xmlmerge.search.ContentEquals;

/**
 * This class swaps number values of attributes
 * @author Richard
 */
public class ValueSwapper extends ValueParser {
  
  private final ContentEquals attributeNameCheck2;
  private double parsedValue2;
  
  /**
   * Construct a new valueSwapper
   * @param tagCheck defines the tag to be searched
   * @param attributeNameCheck first attribute name of the swap
   * @param attributeNameCheck2 second attribute name of the swap
   */
  public ValueSwapper(ContentEquals tagCheck, ContentEquals attributeNameCheck, ContentEquals attributeNameCheck2)
  {
    super(tagCheck, attributeNameCheck);
    this.attributeNameCheck2 = attributeNameCheck2;
    this.parsedValue2 = 0;
  }
  
  @Override
  protected int parseValue(XMLEntry entry) {
    int retVal = 0;
    this.attributeNameCheck.search(entry);
    retVal = this.attributeNameCheck.getAttributeMatchPosition();
    this.attributeNameCheck2.search(entry);
    int secondMatch = this.attributeNameCheck2.getAttributeMatchPosition();
    
    if (retVal != -1 && secondMatch != -1)
    {
      try {
        this.parsedValue = Double.parseDouble(entry.getAttributes().get(retVal).getValue());
        this.parsedValue2 = Double.parseDouble(entry.getAttributes().get(secondMatch).getValue());
        
        entry.getAttributes().get(retVal).setValue(Double.toString(this.parsedValue2));
        entry.getAttributes().get(secondMatch).setValue(Double.toString(this.parsedValue));
      }
      catch (NumberFormatException nfex)
      {
        RSLogger.getLogger().log(Level.WARNING, null, nfex);
        RSLogger.getLogger().log(Level.WARNING, "Node {0} does not contain a number", entry.getName());
      }
    }
    else
    {
      return -1;
    }
    return retVal;
  }  
}
