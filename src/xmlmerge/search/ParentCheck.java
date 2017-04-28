/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlmerge.search;

import java.util.ArrayList;
import java.util.logging.Level;
import rslogger.RSLogger;
import xmlmerge.data.XMLEntry;

/**
 *
 * @author Richard
 */
public class ParentCheck extends ContentCheck {
  
  protected final ContentCheck checker;
  protected final int maxDepth;
  
  public ParentCheck(ContentCheck checker, int maxDepth) {
    super(XMLEntry.BIT_PARENT_NAME);
    this.checker = checker;
    this.maxDepth = maxDepth;
  }
  
  private boolean searchParent(XMLEntry entry, int depth) {
    
    if (depth == 0) {
      return false;
    }
    
    try {
      
      if (checker.search(entry.getParent())) {
        return true;
      }

      return this.searchParent(entry.getParent(), --depth);
    } catch (NullPointerException npex) {
      RSLogger.getLogger().log(Level.WARNING, "Node {0} has no parent", entry.getName());
      return false;
    }
  }

  @Override
  public boolean search(XMLEntry entry) {
    return this.searchParent(entry, maxDepth);
  }

  @Override
  protected boolean search(String s) {
    return this.checker.search(s);
  }
  
}
