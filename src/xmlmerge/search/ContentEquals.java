/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlmerge.search;

import java.util.ArrayList;
import java.util.Observable;
import java.util.logging.Level;
import rslogger.RSLogger;
import xmlmerge.data.XMLEntry;

/**
 *
 * This class searches XMLEntries for strings
 * Namecheck can search in the elements name, value, attribute and parent
 * but only one at a time
 * if any string is found, the function @see search returns immediately
 * 
 * when searching for attribute name or value, every attribute is searched
 * @author Richard
 */
public class ContentEquals extends ContentCheck {
  

  protected final ArrayList<String> searchStrings;
  
  /**
   * construct a new instance of namecheck
   * @param searchType the Type to search for. 
   * Use XMLEntry public values for parameter
   * Only one type can be searched
   */
  public ContentEquals(int searchType) {
    super(searchType);
    this.searchStrings = new ArrayList<>();
  }
  
  /**
   * copyconstructor
   * references the search strings and copys the type
   * @param contentcheck 
   */
  public ContentEquals(ContentEquals contentcheck) {
    super(contentcheck.searchtype);
    this.searchStrings = contentcheck.getSearchStrings();
    this.attributeMatchPosition = -1;
  }
  
  /**
   * add search strings
   * @param text a string to be searched inside a XMLEntry
   */
  public void addSearchString(String text) {
    this.searchStrings.add(text);
  }
  
  private ArrayList<String> getSearchStrings() {
    return this.searchStrings;
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
      for (String s : this.searchStrings) {
        switch (searchtype) {
          case XMLEntry.BIT_NAME: {
            {
              if (entry.getName().equals(s))
                retVal = true;
            }
            break;
          }
          case XMLEntry.BIT_VALUE: {
            {
              if (entry.getValue().equals(s))
                retVal = true;
            }
            break;
          }
          case XMLEntry.BIT_ATTRIBUTE_NAME: {
            {
              for (XMLEntry e : entry.getAttributes())
              {
                if (e.getName().equals(s))
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
            {
              for (XMLEntry e : entry.getAttributes())
              {
                if (e.getValue().equals(s))
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
            {
              if (entry.getParent().getName().equals(s))
              {
                retVal = true;
                break;
              }
            }
            break;
          }
          case XMLEntry.BIT_PARENT_VALUE: {
            {
              if (entry.getParent().getValue().equals(s))
              {
                retVal = true;
                break;
              }
            }
            break;
          }
          default: break;
        }
        if (retVal) {
          break;
        }
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
      if (str.equals(s)) {
        retVal = true;
        break;
      }
    }
    return retVal;
  }


  
}
