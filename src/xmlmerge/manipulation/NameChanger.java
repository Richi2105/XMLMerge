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
import xmlmerge.search.ContentCheck;
import xmlmerge.search.ContentEquals;
import xmlmerge.search.Searcher;

/**
 * This class adds an prefix to an (attribute) value
 * @author Richard
 */
public class NameChanger implements Searcher {
  
  private final ArrayList<ContentCheck> tagChecks;
  private final ContentCheck attributeNameCheck;
  private final String prefix;
  
  /**
   * Construct a new Instance of Rename
   * @param tagCheck the search class filled with the search strings
   * if a string is found, this class steps into action
   * @param attributeNameCheck additional object to verify the attribute to
   * be renamed, may be null
   * @param prefix a string to be set in front of a name, to make it unique
   * amongst other names that may be added to a xml document
   */
  public NameChanger(ContentCheck tagCheck, ContentCheck attributeNameCheck, String prefix) {
    this.tagChecks = new ArrayList<>();
    this.tagChecks.add(tagCheck);
    this.attributeNameCheck = attributeNameCheck;
    this.prefix = prefix;
  }
  
  private int changeName(XMLEntry e) {
    int retVal = 0;
    int pos = 0;
    boolean rename = false;
    if (this.attributeNameCheck != null)
    {
      if (this.attributeNameCheck.search(e))
      {
        pos = this.attributeNameCheck.getAttributeMatchPosition();
        rename = true;
      }
    }
    else
    {
      rename = true;
    }

    if (rename)
    {
      try
      {
        if (e.getAttributes().isEmpty())
        {
          RSLogger.getLogger().log(Level.WARNING, "No attributes attached to Node {0}", e.getName());
          retVal = -1;
        }
        else
        {
          e.getAttributes().get(pos).addPrefix(prefix);
          retVal = 1;
        }
      }
      catch (NullPointerException ex)
      {
        RSLogger.getLogger().log(Level.SEVERE, "\"Node\" {0} Already an Attribute", e.getName());
        retVal = -1;
      }
    }
    return retVal;
  }

  @Override
  public int search(XMLEntry entry) {
    for (ContentCheck check : this.tagChecks)
    {
      if (!check.search(entry))
        return 0;
    }
    return this.changeName(entry);
  }

  @Override
  public void addContentCheck(ContentCheck n) {
    this.tagChecks.add(n);
  }
  
}
