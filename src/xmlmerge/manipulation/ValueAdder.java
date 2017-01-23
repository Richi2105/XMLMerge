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
 *
 * @author Richard
 */
public class ValueAdder implements Searcher{
  
  private final ArrayList<Namecheck> tagChecks;
  private final Namecheck attributeNameCheck;
  private final String attributeName;
  private final String attributeValue;
  
  public ValueAdder(Namecheck tagCheck, Namecheck attributeNameCheck, String attributeName, String attributeValue) {
    this.tagChecks = new ArrayList<>();
    this.tagChecks.add(tagCheck);
    this.attributeNameCheck = attributeNameCheck;
    this.attributeName = attributeName;
    this.attributeValue = attributeValue;
  }
  
  private int addAttribute(XMLEntry entry) {
    int retVal = 0;
    boolean addAttr = false;
    if (this.attributeNameCheck != null)
    {
      if (!this.attributeNameCheck.search(entry))
      {
        addAttr = true;
      }
    }
    else
    {
      addAttr = true;
    }

    if (addAttr)
    {
      try
      {
        XMLEntry newAttribute = new XMLEntry(entry, this.attributeName, this.attributeValue);
        entry.getAttributes().add(newAttribute);
      }
      catch (NullPointerException ex)
      {
        RSLogger.getLogger().log(Level.SEVERE, "\"Node\" {0} Already an Attribute", entry.getName());
        retVal = -1;
      }
    }
    return retVal;
  }

  @Override
  public int search(XMLEntry entry) {
    for (Namecheck check : this.tagChecks)
    {
      if (!check.search(entry))
        return 0;
    }
    return this.addAttribute(entry);
  }

  @Override
  public void addNamecheck(Namecheck n) {
    this.tagChecks.add(n);
  }
  
}
