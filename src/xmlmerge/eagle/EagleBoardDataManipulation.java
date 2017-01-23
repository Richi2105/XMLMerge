/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlmerge.eagle;

import java.io.File;
import xmlmerge.manipulation.NameChanger;
import xmlmerge.manipulation.ValueChanger;
import xmlmerge.manipulation.ValueParser;
import xmlmerge.namesearchFactory.RenameFactory;
import xmlmerge.namesearchFactory.RevalueFactory;

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
    parseXVal.addNamecheck(RevalueFactory.get_board_revalueParentTagCheck());
    
    ValueParser parseYVal = new ValueParser(RevalueFactory.get_board_revalueTagCheck(),
                                            RevalueFactory.get_revalueAttributeCheck_Y());
    parseYVal.addNamecheck(RevalueFactory.get_board_revalueParentTagCheck());
    
    ValueParser parseX1Val = new ValueParser(RevalueFactory.get_board_revalueTagCheck(),
                                            RevalueFactory.get_revalueAttributeCheck_X1());
    parseX1Val.addNamecheck(RevalueFactory.get_board_revalueParentTagCheck());
    
    ValueParser parseY1Val = new ValueParser(RevalueFactory.get_board_revalueTagCheck(),
                                            RevalueFactory.get_revalueAttributeCheck_Y1());
    parseY1Val.addNamecheck(RevalueFactory.get_board_revalueParentTagCheck());
    
    ValueParser parseX2Val = new ValueParser(RevalueFactory.get_board_revalueTagCheck(),
                                            RevalueFactory.get_revalueAttributeCheck_X2());
    parseX2Val.addNamecheck(RevalueFactory.get_board_revalueParentTagCheck());
    
    ValueParser parseY2Val = new ValueParser(RevalueFactory.get_board_revalueTagCheck(),
                                            RevalueFactory.get_revalueAttributeCheck_Y2());
    parseY2Val.addNamecheck(RevalueFactory.get_board_revalueParentTagCheck());
    
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
  
}
