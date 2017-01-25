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
 *
 * @author Richard
 */
public class StringValueParser extends ValueParser {  //Todo: change to implements Searcher, also don't parse to double, useless
  
  private String preValueString;
  private String postValueString;
  
  public StringValueParser(ContentEquals tagCheck, ContentEquals attributeNameCheck) {
    super(tagCheck, attributeNameCheck);
  }
  
  private boolean isNumber(char c) {
    if (c >= '0' && c<= '9')
      return true;
    else
      return false;
  }
  
  @Override
  protected int parseValue(XMLEntry e) {
    int retVal = 0;
    this.attributeNameCheck.search(e);
    retVal = this.attributeNameCheck.getAttributeMatchPosition();
    
    if (retVal != -1)
    {
      try {
        String val = e.getAttributes().get(retVal).getValue();
        int numPos = 0;
        for (int i=0; i<val.length(); i+=1)
        {
          if (this.isNumber(val.charAt(i)))
          {
            numPos = i;
            break;
          }
        }
        
        this.preValueString = "";
        
        if (numPos > 0)
        {
          this.preValueString = (val.subSequence(0, numPos).toString());
          val = val.substring(numPos, val.length());
        }
        this.parsedValue = Double.parseDouble(val);
        
        for (ValueChanger changer : this.valueChanger)
        {
          this.parsedValue = changer.changeValue(this.parsedValue);
        }
//        this.parsedValue += this.value;
        val = this.preValueString.concat(Double.toString(this.parsedValue));
        e.getAttributes().get(retVal).setValue(val);
      }
      catch (NumberFormatException nfex)
      {
        RSLogger.getLogger().log(Level.WARNING, "Node {0} does not contain a number", e.getName());
      }
    }
    return retVal;
  }
  
}
