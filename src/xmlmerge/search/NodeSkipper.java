/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlmerge.search;

import java.util.ArrayList;
import xmlmerge.data.XMLEntry;

/**
 *
 * @author Richard
 */
public class NodeSkipper implements Searcher {
  
  private final ArrayList<ContentCheck> tagChecks;
  
  public NodeSkipper(ContentEquals tagCheck) {
    this.tagChecks = new ArrayList<>();
    this.tagChecks.add(tagCheck);
  }

  @Override
  public int search(XMLEntry entry) {
    int number = 0;
    for (ContentCheck check : this.tagChecks)
    {
      if (check.search(entry))
        number += 1;
    }
    if (number == this.tagChecks.size())
      return Searcher.SEARCHER_DELETE_ENTRY;
    else
      return 0;
  }
  
  @Override
  public void addContentCheck(ContentCheck n) {
    this.tagChecks.add(n);
  }
  
}
