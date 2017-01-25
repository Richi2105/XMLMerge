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
public class ContentContains extends ContentEquals {
  
  public ContentContains(int searchtype) {
    super(searchtype);
  }
  
  public ContentContains(ContentEquals namecheck) {
    super(namecheck);
  }
  
  /**
   * takes an XMLEntry and checks if any searchstring is occuring
   * @param entry the entry to check
   * @return false if no string was found, true if something was found
   */
  @Override
  public boolean search(XMLEntry entry) {
    this.attributeMatchPosition = -1;
    boolean retVal = false;
    try
    {      
      switch (searchtype) {
        case XMLEntry.BIT_NAME: {
          for (String s : searchStrings)
          {
            if (entry.getName().contains(s))
              retVal = true;
          }
          break;
        }
        case XMLEntry.BIT_VALUE: {
          for (String s : searchStrings)
          {
            if (entry.getValue().contains(s))
              retVal = true;
          }
          break;
        }
        case XMLEntry.BIT_ATTRIBUTE_NAME: {
          for (String s : searchStrings)
          {
            for (XMLEntry e : entry.getAttributes())
            {
              if (e.getName().contains(s))
              {
                retVal = true;
                this.attributeMatchPosition = entry.getAttributes().indexOf(e);
                break;
              }

            }
          }
          break;
        }
        case XMLEntry.BIT_ATTRIBUTE_VALUE: {
          for (String s : searchStrings)
          {
            for (XMLEntry e : entry.getAttributes())
            {
              if (e.getValue().contains(s))
              {
                retVal = true;
                this.attributeMatchPosition = entry.getAttributes().indexOf(e);
                break;
              }

            }
          }
          break;
        }
        case XMLEntry.BIT_PARENT_NAME: {
          for (String s : searchStrings)
          {
            if (entry.getParent().getName().contains(s))
            {
              retVal = true;
              break;
            }
          }
          break;
        }
        case XMLEntry.BIT_PARENT_VALUE: {
          for (String s : searchStrings)
          {
            if (entry.getParent().getValue().contains(s))
            {
              retVal = true;
              break;
            }
          }
          break;
        }
        default: break;
      }
    }
    catch (Exception ex)
    {
      RSLogger.getLogger().log(Level.WARNING, ex.toString());
    }
    return retVal;
  }
  
  @Override
  protected boolean search(String s) {
    boolean retVal = false;
    for (String str : this.searchStrings) {
      if (s.contains(str)) {
        retVal = true;
        break;
      }
    }
    return retVal;
  }
  
}
