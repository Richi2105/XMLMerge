/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlmerge.search;

import xmlmerge.data.XMLEntry;

/**
 *
 * @author Richard
 */
public interface Searcher {
  
  public static final int SEARCHER_NOTHING_FOUND = 0;
  public static final int SEARCHER_ERROR = -1;
  public static final int SEARCHER_DELETE_ENTRY = Integer.MIN_VALUE;
  
  public int search(XMLEntry entry);
  
  public void addNamecheck(Namecheck n);
  
}
