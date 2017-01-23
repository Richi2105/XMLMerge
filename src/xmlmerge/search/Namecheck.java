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
public class Namecheck extends Observable {
  

  private final ArrayList<String> searchStrings;
  private final int searchtype;
  private int attributeMatchPosition;
  
  /**
   * construct a new instance of namecheck
   * @param searchType the Type to search for. 
   * Use XMLEntry public values for parameter
   * Only one type can be searched
   */
  public Namecheck(int searchType) {
    this.searchStrings = new ArrayList<>();
    this.searchtype = searchType;
    this.attributeMatchPosition = -1;
  }
  
  /**
   * copyconstructor
   * references the search strings and copys the type
   * @param namecheck 
   */
  public Namecheck(Namecheck namecheck) {
    this.searchStrings = namecheck.getSearchStrings();
    this.searchtype = namecheck.searchtype;
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
   * if this Namecheck searches Attributes (either names or values)
   * one might want to know the position of a match. This function returns
   * the match position of the elements attribute array
   * @return a position inside the last searched element. -1 if no string was
   * found or this Namecheck does not search inside attributes.
   */
  public int getAttributeMatchPosition() {
    return this.attributeMatchPosition;
  }  
  /**
   * takes an XMLEntry and checks if any searchstring is occuring
   * @param entry the entry to check
   * @return false if no string was found, true if something was found
   */
  public boolean search(XMLEntry entry) {
    this.attributeMatchPosition = -1;
    boolean retVal = false;
    try
    {      
      switch (searchtype) {
        case XMLEntry.BIT_NAME: {
          for (String s : searchStrings)
          {
            if (entry.getName().equals(s))
              retVal = true;
          }
          break;
        }
        case XMLEntry.BIT_VALUE: {
          for (String s : searchStrings)
          {
            if (entry.getValue().equals(s))
              retVal = true;
          }
          break;
        }
        case XMLEntry.BIT_ATTRIBUTE_NAME: {
          for (String s : searchStrings)
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
          for (String s : searchStrings)
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
          for (String s : searchStrings)
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
          for (String s : searchStrings)
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
      if (retVal)
      {
        this.setChanged();
        this.notifyObservers(entry);
      }
    }
    catch (Exception ex)
    {
      RSLogger.getLogger().log(Level.WARNING, ex.toString());
    }
    return retVal;
  }
  
}
