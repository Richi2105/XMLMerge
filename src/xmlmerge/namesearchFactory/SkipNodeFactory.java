/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlmerge.namesearchFactory;

import xmlmerge.data.XMLEntry;
import xmlmerge.search.ContentEquals;

/**
 *
 * @author Richard
 */
public class SkipNodeFactory {
  
  private static ContentEquals skipTagCheck;
  
  private static void init_skipTagCheck() {
    SkipNodeFactory.skipTagCheck = new ContentEquals(XMLEntry.BIT_NAME);
    SkipNodeFactory.skipTagCheck.addSearchString("settings");
    SkipNodeFactory.skipTagCheck.addSearchString("grid");
    SkipNodeFactory.skipTagCheck.addSearchString("designrules");
    SkipNodeFactory.skipTagCheck.addSearchString("autorouter");
  }
  
  public static ContentEquals get_skipTagCheck() {
    if (SkipNodeFactory.skipTagCheck == null) {
      SkipNodeFactory.init_skipTagCheck();
    }
    return SkipNodeFactory.skipTagCheck;
  }
  
}
