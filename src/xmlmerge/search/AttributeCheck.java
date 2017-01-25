/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlmerge.search;

import java.util.logging.Level;
import rslogger.RSLogger;
import xmlmerge.data.XMLEntry;

/**
 *
 * @author Richard
 */
public class AttributeCheck extends ContentCheck {
  
  private final ContentCheck attributeNameCheck;
  private final ContentCheck attributeValueCheck;
  
  public AttributeCheck(ContentCheck attributeNameCheck, ContentCheck attributeValueCheck) {
    super(XMLEntry.BIT_ATTRIBUTE_NAME + XMLEntry.BIT_ATTRIBUTE_VALUE);
    this.attributeNameCheck = attributeNameCheck;
    this.attributeValueCheck = attributeValueCheck;    
  }

  @Override
  public boolean search(XMLEntry entry) {
    boolean retVal = false;
    try {
      this.attributeMatchPosition = -1;
      int position = 0;
      for (XMLEntry attribute : entry.getAttributes()) {
        if (this.attributeNameCheck.search(attribute.getName()) && 
            this.attributeValueCheck.search(attribute.getValue())) {
          this.attributeMatchPosition = position;
          retVal = true;
          break;
        }
        position += 1;
      }
    }
    catch (NullPointerException npex) {
      RSLogger.getLogger().log(Level.WARNING, "Node {0} is an attribute", entry.getName());
    }
    return retVal;
  }

  @Override
  protected boolean search(String s) {
    if (this.attributeNameCheck.search(s) && 
        this.attributeValueCheck.search(s)) {
      return true;
    }
    return false;
  }
  
}
