/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlmerge.eagle;

import java.io.File;
import xmlmerge.data.XMLEntry;
import xmlmerge.manipulation.NameChanger;
import xmlmerge.manipulation.StringValueParser;
import xmlmerge.manipulation.ValueAdder;
import xmlmerge.manipulation.ValueChanger;
import xmlmerge.manipulation.ValueParser;
import xmlmerge.manipulation.ValueSwapper;
import xmlmerge.namesearchFactory.RenameFactory;
import xmlmerge.namesearchFactory.RevalueFactory;
import xmlmerge.search.AttributeCheck;
import xmlmerge.search.ContentEquals;
import xmlmerge.search.ContentContains;

/**
 *
 * @author Richard
 */
public class EagleBoardDataManipulation extends EagleDataManipulation {
  
  public EagleBoardDataManipulation(File inputFile) {
    super(inputFile);
  }

  @Override
  public void rename(String prefix) {
    NameChanger rename = new NameChanger(RenameFactory.get_board_renameTagCheck(),
                                         RenameFactory.get_board_renameAttributeCheck(),
                                         prefix);
    this.reader.addSearcher(rename);
  }

  @Override
  public void move(double x, double y) {
    ValueParser parseXVal = new ValueParser(RevalueFactory.get_board_revalueTagCheck(),
                                            RevalueFactory.get_revalueAttributeCheck_X());
    parseXVal.addContentCheck(RevalueFactory.get_board_revalueParentTagCheck());
    
    ValueParser parseYVal = new ValueParser(RevalueFactory.get_board_revalueTagCheck(),
                                            RevalueFactory.get_revalueAttributeCheck_Y());
    parseYVal.addContentCheck(RevalueFactory.get_board_revalueParentTagCheck());
    
    ValueParser parseX1Val = new ValueParser(RevalueFactory.get_board_revalueTagCheck(),
                                            RevalueFactory.get_revalueAttributeCheck_X1());
    parseX1Val.addContentCheck(RevalueFactory.get_board_revalueParentTagCheck());
    
    ValueParser parseY1Val = new ValueParser(RevalueFactory.get_board_revalueTagCheck(),
                                            RevalueFactory.get_revalueAttributeCheck_Y1());
    parseY1Val.addContentCheck(RevalueFactory.get_board_revalueParentTagCheck());
    
    ValueParser parseX2Val = new ValueParser(RevalueFactory.get_board_revalueTagCheck(),
                                            RevalueFactory.get_revalueAttributeCheck_X2());
    parseX2Val.addContentCheck(RevalueFactory.get_board_revalueParentTagCheck());
    
    ValueParser parseY2Val = new ValueParser(RevalueFactory.get_board_revalueTagCheck(),
                                            RevalueFactory.get_revalueAttributeCheck_Y2());
    parseY2Val.addContentCheck(RevalueFactory.get_board_revalueParentTagCheck());
    
    ValueChanger changeXVal = new ValueChanger(ValueChanger.VALUE_CHANGER_ADD, x);
    changeXVal.addValueParser(parseXVal);
    changeXVal.addValueParser(parseX1Val);
    changeXVal.addValueParser(parseX2Val);
    ValueChanger changeYVal = new ValueChanger(ValueChanger.VALUE_CHANGER_ADD, y);
    changeYVal.addValueParser(parseYVal);
    changeYVal.addValueParser(parseY1Val);
    changeYVal.addValueParser(parseY2Val);
    
    this.reader.addSearcher(parseXVal);
    this.reader.addSearcher(parseYVal);
    this.reader.addSearcher(parseX1Val);
    this.reader.addSearcher(parseY1Val);
    this.reader.addSearcher(parseX2Val);
    this.reader.addSearcher(parseY2Val);
  }
  
  /**
   * rotates board by 90 degree
   * @param xBoardSize the board's x size before operation
   * @param yBoardSize the board's y size before operation
   */
  public void rotate(double xBoardSize, double yBoardSize) {
    //add attribute rot
    ContentEquals addAttributeTagCheck = new ContentEquals(XMLEntry.BIT_NAME);
    addAttributeTagCheck.addSearchString("element");
    addAttributeTagCheck.addSearchString("text");
    
    ContentEquals addAttributeParentTagCheck = new ContentEquals(XMLEntry.BIT_PARENT_NAME);
    addAttributeParentTagCheck.addSearchString("plain");
    addAttributeParentTagCheck.addSearchString("elements");

    ContentEquals addAttributeAttributeCheck = new ContentEquals(XMLEntry.BIT_ATTRIBUTE_NAME);
    addAttributeAttributeCheck.addSearchString("rot");
    
    ContentContains changeRotationMirrorAttributeCheck = new ContentContains(XMLEntry.BIT_ATTRIBUTE_VALUE);
    changeRotationMirrorAttributeCheck.addSearchString("M");
    
    AttributeCheck changeRotationMirrorAttributeNameValueCheck = new AttributeCheck(addAttributeAttributeCheck,
                                                                                    changeRotationMirrorAttributeCheck);
    
    NameChanger addSpinToText = new NameChanger(addAttributeTagCheck, 
                                                changeRotationMirrorAttributeNameValueCheck, "S");
    addSpinToText.addContentCheck(addAttributeParentTagCheck);

    ValueAdder addRotationAttribute = new ValueAdder(addAttributeTagCheck, addAttributeAttributeCheck, "rot", "R0");
    addRotationAttribute.addContentCheck(addAttributeParentTagCheck);

    //rotation attribute, add 90, modulo 360

    ValueChanger changeRotationAttribute = new ValueChanger(ValueChanger.VALUE_CHANGER_ADD, 90);
    ValueChanger changeRotationAttribute_mirror = new ValueChanger(ValueChanger.VALUE_CHANGER_ADD, 180);
    ValueChanger moduloRotationAttribute = new ValueChanger(ValueChanger.VALUE_CHANGER_MODULO_BY_VALUE, 360);
    
    

    StringValueParser parseRotationAttribute = new StringValueParser(addAttributeTagCheck, addAttributeAttributeCheck);
    parseRotationAttribute.addContentCheck(addAttributeParentTagCheck);
    changeRotationAttribute.addValueParser(parseRotationAttribute);
    moduloRotationAttribute.addValueParser(parseRotationAttribute);
    
    StringValueParser parseRotationAttributeMirror = new StringValueParser(addAttributeTagCheck, addAttributeAttributeCheck);
    parseRotationAttributeMirror.addContentCheck(addAttributeParentTagCheck);
    parseRotationAttributeMirror.addContentCheck(changeRotationMirrorAttributeNameValueCheck);
    changeRotationAttribute_mirror.addValueParser(parseRotationAttributeMirror);
    moduloRotationAttribute.addValueParser(parseRotationAttributeMirror);
    
    ValueChanger xCalc = new ValueChanger(ValueChanger.VALUE_CHANGER_SUBSTRACT_FROM_VALUE, yBoardSize);
    
    //Swap x, y value
    ValueSwapper swapperxy = new ValueSwapper(RevalueFactory.get_board_revalueTagCheck(),
                                              RevalueFactory.get_revalueAttributeCheck_X(),
                                              RevalueFactory.get_revalueAttributeCheck_Y());
    swapperxy.addContentCheck(RevalueFactory.get_board_revalueParentTagCheck());    

    ValueParser parseXVal = new ValueParser(RevalueFactory.get_board_revalueTagCheck(),
                                            RevalueFactory.get_revalueAttributeCheck_X());
    parseXVal.addContentCheck(RevalueFactory.get_board_revalueParentTagCheck());

    //Swap x1, y1 value
    ValueSwapper swapperx1y1 = new ValueSwapper(RevalueFactory.get_board_revalueTagCheck(),
                                              RevalueFactory.get_revalueAttributeCheck_X1(),
                                              RevalueFactory.get_revalueAttributeCheck_Y1());
    swapperx1y1.addContentCheck(RevalueFactory.get_board_revalueParentTagCheck());    

    ValueParser parseX1Val = new ValueParser(RevalueFactory.get_board_revalueTagCheck(),
                                            RevalueFactory.get_revalueAttributeCheck_X1());
    parseX1Val.addContentCheck(RevalueFactory.get_board_revalueParentTagCheck());

    //Swap x2, y2 value
    ValueSwapper swapperx2y2 = new ValueSwapper(RevalueFactory.get_board_revalueTagCheck(),
                                              RevalueFactory.get_revalueAttributeCheck_X2(),
                                              RevalueFactory.get_revalueAttributeCheck_Y2());
    swapperx2y2.addContentCheck(RevalueFactory.get_board_revalueParentTagCheck());    

    ValueParser parseX2Val = new ValueParser(RevalueFactory.get_board_revalueTagCheck(),
                                            RevalueFactory.get_revalueAttributeCheck_X2());
    parseX2Val.addContentCheck(RevalueFactory.get_board_revalueParentTagCheck());

    //Calculate new x, x1, x2 value
    xCalc.addValueParser(parseXVal);
    xCalc.addValueParser(parseX1Val);
    xCalc.addValueParser(parseX2Val);
    
    this.reader.addSearcher(addRotationAttribute);
    this.reader.addSearcher(parseRotationAttribute);
    this.reader.addSearcher(parseRotationAttributeMirror);
    this.reader.addSearcher(addSpinToText);
    this.reader.addSearcher(swapperxy);
    this.reader.addSearcher(swapperx1y1);
    this.reader.addSearcher(swapperx2y2);
    this.reader.addSearcher(parseXVal);
    this.reader.addSearcher(parseX1Val);
    this.reader.addSearcher(parseX2Val);
    
  }
  
}
