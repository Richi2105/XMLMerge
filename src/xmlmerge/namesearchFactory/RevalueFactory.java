/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlmerge.namesearchFactory;

import xmlmerge.data.XMLEntry;
import xmlmerge.search.Namecheck;

/**
 *
 * @author Richard
 */
public class RevalueFactory {
  
  private static Namecheck schematic_revalueTagCheck;
  
  private static Namecheck schematic_revalueParentTagCheck;
  
  private static Namecheck board_revalueTagCheck;
  
  private static Namecheck board_revalueParentTagCheck;
  
  private static Namecheck revalueAttributeCheck_X;
  private static Namecheck revalueAttributeCheck_Y;
  
  private static Namecheck revalueAttributeCheck_X1;
  private static Namecheck revalueAttributeCheck_Y1;
  
  private static Namecheck revalueAttributeCheck_X2;
  private static Namecheck revalueAttributeCheck_Y2;
  
  private static void init_schematic_revalueTagCheck() {
    RevalueFactory.schematic_revalueTagCheck = new Namecheck(XMLEntry.BIT_NAME);
    RevalueFactory.schematic_revalueTagCheck.addSearchString("instance");
    RevalueFactory.schematic_revalueTagCheck.addSearchString("wire");
    RevalueFactory.schematic_revalueTagCheck.addSearchString("label");
    RevalueFactory.schematic_revalueTagCheck.addSearchString("junction");
  }
  
  public static Namecheck get_schematic_revalueTagCheck() {
    if (RevalueFactory.schematic_revalueTagCheck == null) {
      RevalueFactory.init_schematic_revalueTagCheck();
    }
    return RevalueFactory.schematic_revalueTagCheck;
  }
  
  private static void init_schematic_revalueParentTagCheck() {
    RevalueFactory.schematic_revalueParentTagCheck = new Namecheck(XMLEntry.BIT_PARENT_NAME);
    RevalueFactory.schematic_revalueParentTagCheck.addSearchString("instances");
    RevalueFactory.schematic_revalueParentTagCheck.addSearchString("segment");
  }
  
  public static Namecheck get_schematic_revalueParentTagCheck() {
    if (RevalueFactory.schematic_revalueParentTagCheck == null) {
      RevalueFactory.init_schematic_revalueParentTagCheck();
    }
    return RevalueFactory.schematic_revalueParentTagCheck;
  }
  
  private static void init_board_revalueTagCheck() {
    RevalueFactory.board_revalueTagCheck = new Namecheck(XMLEntry.BIT_NAME);
    RevalueFactory.board_revalueTagCheck.addSearchString("element");
    RevalueFactory.board_revalueTagCheck.addSearchString("attribute");
    RevalueFactory.board_revalueTagCheck.addSearchString("wire");
    RevalueFactory.board_revalueTagCheck.addSearchString("via");
    RevalueFactory.board_revalueTagCheck.addSearchString("vertex");
  }
  
  public static Namecheck get_board_revalueTagCheck() {
    if (RevalueFactory.board_revalueTagCheck == null) {
      RevalueFactory.init_board_revalueTagCheck();
    }
    return RevalueFactory.board_revalueTagCheck;
  }
  
  private static void init_board_revalueParentTagCheck() {
    RevalueFactory.board_revalueParentTagCheck = new Namecheck(XMLEntry.BIT_PARENT_NAME);
    RevalueFactory.board_revalueParentTagCheck.addSearchString("plain");
    RevalueFactory.board_revalueParentTagCheck.addSearchString("elements");
    RevalueFactory.board_revalueParentTagCheck.addSearchString("element");
    RevalueFactory.board_revalueParentTagCheck.addSearchString("signal");
    RevalueFactory.board_revalueParentTagCheck.addSearchString("polygon");
  }
  
  public static Namecheck get_board_revalueParentTagCheck() {
    if (RevalueFactory.board_revalueParentTagCheck == null) {
      RevalueFactory.init_board_revalueParentTagCheck();
    }
    return RevalueFactory.board_revalueParentTagCheck;
  }
  
  private static void init_revalueAttributeCheck_X() {
    RevalueFactory.revalueAttributeCheck_X = new Namecheck(XMLEntry.BIT_ATTRIBUTE_NAME);
    RevalueFactory.revalueAttributeCheck_X.addSearchString("x");
  }
  
  public static Namecheck get_revalueAttributeCheck_X() {
    if (RevalueFactory.revalueAttributeCheck_X == null) {
      RevalueFactory.init_revalueAttributeCheck_X();
    }
    return RevalueFactory.revalueAttributeCheck_X;
  }
  
  private static void init_revalueAttributeCheck_Y() {
    RevalueFactory.revalueAttributeCheck_Y = new Namecheck(XMLEntry.BIT_ATTRIBUTE_NAME);
    RevalueFactory.revalueAttributeCheck_Y.addSearchString("y");
  }
  
  public static Namecheck get_revalueAttributeCheck_Y() {
    if (RevalueFactory.revalueAttributeCheck_Y == null) {
      RevalueFactory.init_revalueAttributeCheck_Y();
    }
    return RevalueFactory.revalueAttributeCheck_Y;
  }
  
    private static void init_revalueAttributeCheck_X1() {
    RevalueFactory.revalueAttributeCheck_X1 = new Namecheck(XMLEntry.BIT_ATTRIBUTE_NAME);
    RevalueFactory.revalueAttributeCheck_X1.addSearchString("x1");
  }
  
  public static Namecheck get_revalueAttributeCheck_X1() {
    if (RevalueFactory.revalueAttributeCheck_X1 == null) {
      RevalueFactory.init_revalueAttributeCheck_X1();
    }
    return RevalueFactory.revalueAttributeCheck_X1;
  }
  
  private static void init_revalueAttributeCheck_Y1() {
    RevalueFactory.revalueAttributeCheck_Y1 = new Namecheck(XMLEntry.BIT_ATTRIBUTE_NAME);
    RevalueFactory.revalueAttributeCheck_Y1.addSearchString("y1");
  }
  
  public static Namecheck get_revalueAttributeCheck_Y1() {
    if (RevalueFactory.revalueAttributeCheck_Y1 == null) {
      RevalueFactory.init_revalueAttributeCheck_Y1();
    }
    return RevalueFactory.revalueAttributeCheck_Y1;
  }
  
    private static void init_revalueAttributeCheck_X2() {
    RevalueFactory.revalueAttributeCheck_X2 = new Namecheck(XMLEntry.BIT_ATTRIBUTE_NAME);
    RevalueFactory.revalueAttributeCheck_X2.addSearchString("x2");
  }
  
  public static Namecheck get_revalueAttributeCheck_X2() {
    if (RevalueFactory.revalueAttributeCheck_X2 == null) {
      RevalueFactory.init_revalueAttributeCheck_X2();
    }
    return RevalueFactory.revalueAttributeCheck_X2;
  }
  
  private static void init_revalueAttributeCheck_Y2() {
    RevalueFactory.revalueAttributeCheck_Y2 = new Namecheck(XMLEntry.BIT_ATTRIBUTE_NAME);
    RevalueFactory.revalueAttributeCheck_Y2.addSearchString("y2");
  }
  
  public static Namecheck get_revalueAttributeCheck_Y2() {
    if (RevalueFactory.revalueAttributeCheck_Y2 == null) {
      RevalueFactory.init_revalueAttributeCheck_Y2();
    }
    return RevalueFactory.revalueAttributeCheck_Y2;
  }
  
}
