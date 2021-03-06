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
public class RenameFactory {
  
  private static ContentEquals schematic_renameTagCheck;
  private static ContentEquals schematic_renameAttributeCheck;
  
  private static ContentEquals board_renameTagCheck;
  private static ContentEquals board_renameAttributeCheck;
  
  private static void init_schematic_renameTagCheck() {
    RenameFactory.schematic_renameTagCheck = new ContentEquals(XMLEntry.BIT_NAME);
    RenameFactory.schematic_renameTagCheck.addSearchString("part");
    RenameFactory.schematic_renameTagCheck.addSearchString("instance");
    RenameFactory.schematic_renameTagCheck.addSearchString("bus");
    RenameFactory.schematic_renameTagCheck.addSearchString("net");
    RenameFactory.schematic_renameTagCheck.addSearchString("pinref");
  }
  
  public static ContentEquals get_schematic_renameTagCheck() {
    if (RenameFactory.schematic_renameTagCheck == null) {
      RenameFactory.init_schematic_renameTagCheck();
    }
    return RenameFactory.schematic_renameTagCheck;
  }
  
  private static void init_board_renameTagCheck() {
    RenameFactory.board_renameTagCheck = new ContentEquals(XMLEntry.BIT_NAME);
    RenameFactory.board_renameTagCheck.addSearchString("element");
    RenameFactory.board_renameTagCheck.addSearchString("signal");
    RenameFactory.board_renameTagCheck.addSearchString("contactref");
  }
  
  public static ContentEquals get_board_renameTagCheck() {
    if (RenameFactory.board_renameTagCheck == null) {
      RenameFactory.init_board_renameTagCheck();
    }
    return RenameFactory.board_renameTagCheck;
  }
  
  private static void init_schematic_renameAttributeCheck() {
    RenameFactory.schematic_renameAttributeCheck = new ContentEquals(XMLEntry.BIT_ATTRIBUTE_NAME);
    RenameFactory.schematic_renameAttributeCheck.addSearchString("name");
    RenameFactory.schematic_renameAttributeCheck.addSearchString("part");
  }
  
  public static ContentEquals get_schematic_renameAttributeCheck() {
    if (RenameFactory.schematic_renameAttributeCheck == null) {
      RenameFactory.init_schematic_renameAttributeCheck();
    }
    return RenameFactory.schematic_renameAttributeCheck;
  }
  
  private static void init_board_renameAttributeCheck() {
    RenameFactory.board_renameAttributeCheck = new ContentEquals(XMLEntry.BIT_ATTRIBUTE_NAME);
    RenameFactory.board_renameAttributeCheck.addSearchString("name");
    RenameFactory.board_renameAttributeCheck.addSearchString("element");
  }
  
  public static ContentEquals get_board_renameAttributeCheck() {
    if (RenameFactory.board_renameAttributeCheck == null) {
      RenameFactory.init_board_renameAttributeCheck();
    }
    return RenameFactory.board_renameAttributeCheck;
  }
  
  
  
}
