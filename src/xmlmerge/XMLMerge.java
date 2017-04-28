/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlmerge;

import xmlmerge.eagle.EagleSchematicDataManipulation;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import nu.xom.ParsingException;
import xmlmerge.data.XMLEntry;
import xmlmerge.data.XMLTree;
import xmlmerge.eagle.EagleBoardDataManipulation;
import xmlmerge.manipulation.NameChanger;
import xmlmerge.manipulation.StringValueParser;
import xmlmerge.manipulation.ValueAdder;
import xmlmerge.manipulation.ValueChanger;
import xmlmerge.manipulation.ValueParser;
import xmlmerge.manipulation.ValueSwapper;
import xmlmerge.search.ContentEquals;
import xmlmerge.search.NodeSkipper;
import xmlmerge.xmlReader.XMLReader;
import xmlmerge.xmlWriter.XMLWriter;

/**
 *
 * @author Richard
 */
public class XMLMerge {
  
//  private final File file;
  
  private static class Position {
    public double x;
    public double y;
    
    public Position(double x, double y) {
      this.x = x;
      this.y = y;
    }
  }
  
  public XMLMerge() {
    
  }
  
  private static String path_Mainboard_sch = "I:\\eagle\\Boards\\Simson_Mainboard.sch";
  private static String path_Mainboard_brd = "I:\\eagle\\Boards\\Simson_Mainboard.brd";
  
  private static String path_Speedo_sch = "I:\\eagle\\Boards\\Simson_Speedo.sch";
  private static String path_Speedo_brd = "I:\\eagle\\Boards\\Simson_Speedo.brd";
  
  private static String path_Regulator_sch = "I:\\eagle\\Boards\\Simson_Regulator12V.sch";
  private static String path_Regulator_brd = "I:\\eagle\\Boards\\Simson_Regulator12V.brd";
  
  private static String path_Rearlight_sch = "I:\\eagle\\Boards\\Simson_Rearlight.sch";
  private static String path_Rearlight_brd = "I:\\eagle\\Boards\\Simson_Rearlight.brd";
  
  private static String path_SpeedoLayer2_sch = "I:\\eagle\\Boards\\Simson_Speedo_Layer2.sch";
  private static String path_SpeedoLayer2_brd = "I:\\eagle\\Boards\\Simson_Speedo_Layer2.brd";
  
  private static String path_TempSensor_sch = "I:\\eagle\\Boards\\Simson_TempSensor.sch";
  private static String path_TempSensor_brd = "I:\\eagle\\Boards\\Simson_TempSensor.brd";
  
  private static String path_AudioAmp_sch = "I:\\eagle\\Boards\\AudioAmp.sch";
  private static String path_AudioAmp_brd = "I:\\eagle\\Boards\\AudioAmp.brd";
  
  private static String path_DisplayAdapter_sch = "I:\\eagle\\Boards\\DotMatrixInterface.sch";
  private static String path_DisplayAdapter_brd = "I:\\eagle\\Boards\\DotMatrixInterface.brd";

  


  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
  
  /*
    //move
    EagleBoardDataManipulation board = new EagleBoardDataManipulation(new File("I:\\eagle\\Boards\\Schematic.brd"));
    board.move(6.0, 6.0);
    board.read();
    board.write(new File("I:\\eagle\\Boards\\Schematic1.brd"));
  */
  /*  
    //Rotate stuff
    EagleBoardDataManipulation tempSens = new EagleBoardDataManipulation(new File(path_TempSensor_brd));
    tempSens.rotate(10.16, 30.0);
    tempSens.read();
    tempSens.write(new File("I:\\eagle\\Boards\\Simson_TempSensorrot.brd"));
  */
  
    //Mainboard
    
    Position mainboardBPos = new Position(6.0, 6.0);
    
    Position speedoSPos = new Position(400.0, 0.0);
    Position speedoBPos = new Position(112.0, 6.0);
    
    Position ampSPos = new Position(0, 274.0);
    Position ampBPos = new Position(6.0, 92.0);
    
    Position adaptSPos = new Position(0, 714.0);
    Position adaptBPos = new Position(112.0, 92.0);
    
    Position regulatorSPos = new Position(274.0, 714.0);
    Position regulatorBPos = new Position(6.0, 178.0);
    
    Position speedo2SPos = new Position(574.0, 274.0);
    Position speedo2BPos = new Position(169.0, 92.0);
    
    Position rearlightSPos = new Position(574.0, 423.0);
    Position rearlightBPos = new Position(169.0, 150.0);
    
    Position temp1SPos = new Position(574.0, 572.0);
    Position temp1BPos = new Position(112.0, 184.0);
    
    Position temp2SPos = new Position(574.0, 721.0);
    Position temp2BPos = new Position(128.0, 184.0);
    
    EagleSchematicDataManipulation mainboardSchematic = new EagleSchematicDataManipulation(new File(path_Mainboard_sch));
    mainboardSchematic.rename("Mainboard_");
    
    EagleBoardDataManipulation mainboardBoard = new EagleBoardDataManipulation(new File(path_Mainboard_brd));
    mainboardBoard.rename("Mainboard_");
    mainboardBoard.move(mainboardBPos.x, mainboardBPos.y);
    
    //Speedo    
    EagleSchematicDataManipulation speedoSchematic = new EagleSchematicDataManipulation(new File(path_Speedo_sch));
    speedoSchematic.rename("Speedo_");
    speedoSchematic.move(speedoSPos.x, speedoSPos.y);
    
    EagleBoardDataManipulation speedoBoard = new EagleBoardDataManipulation(new File (path_Speedo_brd));
    speedoBoard.rename("Speedo_");
    speedoBoard.move(speedoBPos.x, speedoBPos.y);
    
    //Audioamp
    EagleSchematicDataManipulation audioampSchematic = new EagleSchematicDataManipulation(new File(path_AudioAmp_sch));
    audioampSchematic.rename("Amp_");
    audioampSchematic.move(ampSPos.x, ampSPos.y);
    
    EagleBoardDataManipulation audioampBoard = new EagleBoardDataManipulation(new File(path_AudioAmp_brd));
    audioampBoard.rename("Amp_");
    audioampBoard.move(ampBPos.x, ampBPos.y);
    
    //12V Regulator
    EagleSchematicDataManipulation regulatorSchematic = new EagleSchematicDataManipulation(new File(path_Regulator_sch));
    regulatorSchematic.rename("Reg_");
    regulatorSchematic.move(regulatorSPos.x, regulatorSPos.y);
    
    EagleBoardDataManipulation regulatorBoard = new EagleBoardDataManipulation(new File(path_Regulator_brd));
    regulatorBoard.rename("Reg_");
    regulatorBoard.move(regulatorBPos.x, regulatorBPos.y);
    
    
    //Displayadapter
    EagleSchematicDataManipulation displaySchematic = new EagleSchematicDataManipulation(new File(path_DisplayAdapter_sch));
    displaySchematic.rename("Display_");
    displaySchematic.move(adaptSPos.x, adaptSPos.y);
    
    EagleBoardDataManipulation displayBoard = new EagleBoardDataManipulation(new File(path_DisplayAdapter_brd));
    displayBoard.rename("Display_");
    displayBoard.rotate(86.0, 51.0);
    displayBoard.move(adaptBPos.x, adaptBPos.y);
    
    //Speedo Layer 2
    EagleSchematicDataManipulation speedo2Schematic = new EagleSchematicDataManipulation(new File(path_SpeedoLayer2_sch));
    speedo2Schematic.rename("SpeedoL2_");
    speedo2Schematic.move(speedo2SPos.x, speedo2SPos.y);
    
    EagleBoardDataManipulation speedo2Board = new EagleBoardDataManipulation(new File(path_SpeedoLayer2_brd));
    speedo2Board.rename("SpeedoL2_");
    speedo2Board.rotate(52.0, 23.0);
    speedo2Board.move(speedo2BPos.x, speedo2BPos.y);
    
    //Rear Light
    EagleSchematicDataManipulation rearlightSchematic = new EagleSchematicDataManipulation(new File(path_Rearlight_sch));
    rearlightSchematic.rename("Rearlight_");
    rearlightSchematic.move(rearlightSPos.x, rearlightSPos.y);
    
    EagleBoardDataManipulation rearlightBoard = new EagleBoardDataManipulation(new File(path_Rearlight_brd));
    rearlightBoard.rename("Rearlight_");
    rearlightBoard.rotate(28.0, 23.0);
    rearlightBoard.move(rearlightBPos.x, rearlightBPos.y);
    
    //Temperature Sensor
    EagleSchematicDataManipulation tempSensor1Schematic = new EagleSchematicDataManipulation(new File(path_TempSensor_sch));
    tempSensor1Schematic.rename("Temp1_");
    tempSensor1Schematic.move(temp1SPos.x, temp1SPos.y);
    
    EagleBoardDataManipulation tempSensor1Board = new EagleBoardDataManipulation(new File(path_TempSensor_brd));
    tempSensor1Board.rename("Temp1_");
    tempSensor1Board.move(temp1BPos.x, temp1BPos.y);
    
    EagleSchematicDataManipulation tempSensor2Schematic = new EagleSchematicDataManipulation(new File(path_TempSensor_sch));
    tempSensor2Schematic.rename("Temp2_");
    tempSensor2Schematic.move(temp2SPos.x, temp2SPos.y);
    
    EagleBoardDataManipulation tempSensor2Board = new EagleBoardDataManipulation(new File(path_TempSensor_brd));
    tempSensor2Board.rename("Temp2_");
    tempSensor2Board.move(temp2BPos.x, temp2BPos.y);
    
    //Merge
    mainboardSchematic.merge(speedoSchematic);
    mainboardSchematic.merge(audioampSchematic);
    mainboardSchematic.merge(regulatorSchematic);
    mainboardSchematic.merge(displaySchematic);
    mainboardSchematic.merge(speedo2Schematic);
    mainboardSchematic.merge(rearlightSchematic);
    mainboardSchematic.merge(tempSensor1Schematic);
    mainboardSchematic.merge(tempSensor2Schematic);
    
    mainboardSchematic.read();
    mainboardSchematic.write(new File("I:\\eagle\\Boards\\Schematic.sch"));
    
    
    mainboardBoard.merge(speedoBoard);
    mainboardBoard.merge(audioampBoard);
    mainboardBoard.merge(regulatorBoard);
    mainboardBoard.merge(displayBoard);
    mainboardBoard.merge(speedo2Board);
    mainboardBoard.merge(rearlightBoard);
    mainboardBoard.merge(tempSensor1Board);
    mainboardBoard.merge(tempSensor2Board);
    
    mainboardBoard.read();    
    mainboardBoard.write(new File("I:\\eagle\\Boards\\Schematic.brd"));

/*    
    EagleSchematicDataManipulation esdm = new EagleSchematicDataManipulation(new File("I:\\eagle\\MergeTest\\Merge1.sch"));
    esdm.rename("Merge1");
    esdm.move(200, 0);
    
    
    EagleSchematicDataManipulation esdm_2 = new EagleSchematicDataManipulation(new File("I:\\eagle\\MergeTest\\Merge2.sch"));
    esdm_2.rename("Merge2");
//    esdm_2.move(200, 0);
    esdm.merge(esdm_2);

    
    EagleBoardDataManipulation ebdm = new EagleBoardDataManipulation(new File ("I:\\eagle\\MergeTest\\Merge1.brd"));
    ebdm.rename("Merge1");
    ebdm.rotate(39.04, 26.66);
    ebdm.move(18, 0);
    
    
    EagleBoardDataManipulation ebdm_2 = new EagleBoardDataManipulation(new File ("I:\\eagle\\MergeTest\\Merge2.brd"));
    ebdm_2.rename("Merge2");
    ebdm_2.rotate(21.59, 16.51);
//    ebdm_2.move(40, 0);
    ebdm.merge(ebdm_2);
    
    esdm.read();
    ebdm.read();
    
    esdm.write(new File("I:\\eagle\\MergeTest\\Merge1out.sch"));
    ebdm.write(new File("I:\\eagle\\MergeTest\\Merge1out.brd"));
*/
/*
    EagleBoardDataManipulation ebdm = new EagleBoardDataManipulation(new File("I:\\eagle\\Manipulate\\Simson_TempSensor.brd"));
    ebdm.rename("Temp1");
    ebdm.rotate(10.16, 30.0);
    ebdm.read();
    ebdm.write(new File("I:\\eagle\\Manipulate\\Simson_TempSensor_rot.brd"));
//    try {
      //Schematic Logic
/*      
      double xOffset = 187.96;
      double yOffset = 134.62;
      
      Namecheck renameTagCheck = new Namecheck(XMLEntry.BIT_NAME);
      renameTagCheck.addSearchString("part");
      renameTagCheck.addSearchString("instance");
      renameTagCheck.addSearchString("bus");
      renameTagCheck.addSearchString("net");
      renameTagCheck.addSearchString("pinref");
      
      Namecheck renameAttributeCheck = new Namecheck(XMLEntry.BIT_ATTRIBUTE_NAME);
      renameAttributeCheck.addSearchString("name");
      renameAttributeCheck.addSearchString("part");
      
      Namecheck skipNameCheck = new Namecheck(XMLEntry.BIT_NAME);
      skipNameCheck.addSearchString("settings");
      skipNameCheck.addSearchString("grid");
      
      Namecheck revalTagCheck1 = new Namecheck(XMLEntry.BIT_NAME);
      revalTagCheck1.addSearchString("instance");
      revalTagCheck1.addSearchString("wire");
      revalTagCheck1.addSearchString("label");
      
      Namecheck revalTagCheck2 = new Namecheck(XMLEntry.BIT_PARENT_NAME);
      revalTagCheck2.addSearchString("instances");
      revalTagCheck2.addSearchString("segment");
      
      
      Namecheck revalAttributeCheck1x = new Namecheck(XMLEntry.BIT_ATTRIBUTE_NAME);
      revalAttributeCheck1x.addSearchString("x");
      
      Namecheck revalAttributeCheck2x = new Namecheck(XMLEntry.BIT_ATTRIBUTE_NAME);
      revalAttributeCheck2x.addSearchString("x1");
      
      Namecheck revalAttributeCheck3x = new Namecheck(XMLEntry.BIT_ATTRIBUTE_NAME);
      revalAttributeCheck3x.addSearchString("x2");
      
      Namecheck revalAttributeCheck1y = new Namecheck(XMLEntry.BIT_ATTRIBUTE_NAME);
      revalAttributeCheck1y.addSearchString("y");
      
      Namecheck revalAttributeCheck2y = new Namecheck(XMLEntry.BIT_ATTRIBUTE_NAME);
      revalAttributeCheck2y.addSearchString("y1");
      
      Namecheck revalAttributeCheck3y = new Namecheck(XMLEntry.BIT_ATTRIBUTE_NAME);
      revalAttributeCheck3y.addSearchString("y2");
      
      NameChanger rename1 = new NameChanger(renameTagCheck, renameAttributeCheck, "Merge1");      
      NameChanger rename2 = new NameChanger(renameTagCheck, renameAttributeCheck, "Merge2");
      
      NodeSkipper skipper = new NodeSkipper(skipNameCheck);
      
      ValueChanger changeXVal = new ValueChanger(revalTagCheck1, revalAttributeCheck1x, xOffset);
      changeXVal.addNamecheck(revalTagCheck2);
      ValueChanger changeX1Val = new ValueChanger(revalTagCheck1, revalAttributeCheck2x, xOffset);
      changeX1Val.addNamecheck(revalTagCheck2);
      ValueChanger changeX2Val = new ValueChanger(revalTagCheck1, revalAttributeCheck3x, xOffset);
      changeX2Val.addNamecheck(revalTagCheck2);
      
      ValueChanger changeYVal = new ValueChanger(revalTagCheck1, revalAttributeCheck1y, yOffset);
      changeYVal.addNamecheck(revalTagCheck2);
      ValueChanger changeY1Val = new ValueChanger(revalTagCheck1, revalAttributeCheck2y, yOffset);
      changeY1Val.addNamecheck(revalTagCheck2);
      ValueChanger changeY2Val = new ValueChanger(revalTagCheck1, revalAttributeCheck3y, yOffset);
      changeY2Val.addNamecheck(revalTagCheck2);
      
      XMLReader reader_Merge1_Test = new XMLReader(new File("I:/eagle/MergeTest/Merge1_Test.sch"));
      reader_Merge1_Test.addSearcher(rename1);
      
      
      
      XMLReader reader_Merge2_Test = new XMLReader(new File("I:/eagle/MergeTest/Merge2_Test.sch"));
      reader_Merge2_Test.addSearcher(rename2);
      reader_Merge2_Test.addSearcher(skipper);
      reader_Merge2_Test.addSearcher(changeXVal);
      reader_Merge2_Test.addSearcher(changeX1Val);
      reader_Merge2_Test.addSearcher(changeX2Val);
      reader_Merge2_Test.addSearcher(changeYVal);
      reader_Merge2_Test.addSearcher(changeY1Val);
      reader_Merge2_Test.addSearcher(changeY2Val);
*/  
/*
      //Board Logic
      
      //Rename
      Namecheck rename_TagCheck = new Namecheck(XMLEntry.BIT_NAME);
      rename_TagCheck.addSearchString("element");
      rename_TagCheck.addSearchString("contactref");
      
      Namecheck rename_AttributeNameCheck = new Namecheck(XMLEntry.BIT_ATTRIBUTE_NAME);
      rename_AttributeNameCheck.addSearchString("name");
      rename_AttributeNameCheck.addSearchString("element");
      
      NameChanger changer1 = new NameChanger(rename_TagCheck, rename_AttributeNameCheck, "Merge1");
      NameChanger changer2 = new NameChanger(rename_TagCheck, rename_AttributeNameCheck, "Merge2");
      
      Namecheck reval_TagCheck = new Namecheck(XMLEntry.BIT_NAME);
      reval_TagCheck.addSearchString("element");
      reval_TagCheck.addSearchString("attribute");
      reval_TagCheck.addSearchString("wire");
      reval_TagCheck.addSearchString("vertex");
      
      Namecheck reval_ParentTagCheck = new Namecheck(XMLEntry.BIT_PARENT_NAME);
      reval_ParentTagCheck.addSearchString("elements");
      reval_ParentTagCheck.addSearchString("element");
      reval_ParentTagCheck.addSearchString("signal");
      reval_ParentTagCheck.addSearchString("polygon");
      
      //Revalue
      Namecheck reval_AttributeNameCheckx = new Namecheck(XMLEntry.BIT_ATTRIBUTE_NAME);
      reval_AttributeNameCheckx.addSearchString("x");
      
      Namecheck reval_AttributeNameCheckx1 = new Namecheck(XMLEntry.BIT_ATTRIBUTE_NAME);
      reval_AttributeNameCheckx1.addSearchString("x1");
      
      Namecheck reval_AttributeNameCheckx2 = new Namecheck(XMLEntry.BIT_ATTRIBUTE_NAME);
      reval_AttributeNameCheckx2.addSearchString("x2");
      
      Namecheck reval_AttributeNameChecky = new Namecheck(XMLEntry.BIT_ATTRIBUTE_NAME);
      reval_AttributeNameChecky.addSearchString("y");
      
      Namecheck reval_AttributeNameChecky1 = new Namecheck(XMLEntry.BIT_ATTRIBUTE_NAME);
      reval_AttributeNameChecky1.addSearchString("y1");
      
      Namecheck reval_AttributeNameChecky2 = new Namecheck(XMLEntry.BIT_ATTRIBUTE_NAME);
      reval_AttributeNameChecky2.addSearchString("y2");

*/
/*
      //Board Rotate
      
      //values
      double boardXSize = 100;
      double boardYSize = 80;
      
      //add attribute rot
      Namecheck addAttributeTagCheck = new Namecheck(XMLEntry.BIT_NAME);
      addAttributeTagCheck.addSearchString("element");
      
      Namecheck addAttributeAttributeCheck = new Namecheck(XMLEntry.BIT_ATTRIBUTE_NAME);
      addAttributeAttributeCheck.addSearchString("rot");
      
      ValueAdder addRotationAttribute = new ValueAdder(addAttributeTagCheck, addAttributeAttributeCheck, "rot", "R0");
      
//      Namecheck revalFlipTagCheck = new Namecheck(XMLEntry.BIT_NAME);
//      revalFlipTagCheck.addSearchString("element");
      
      //rotation attribute, add 90, modulo 360
      
      ValueChanger changeRotationAttribute = new ValueChanger(ValueChanger.VALUE_CHANGER_ADD, 90);
      ValueChanger moduloRotationAttribute = new ValueChanger(ValueChanger.VALUE_CHANGER_MODULO_BY_VALUE, 360);
      
      StringValueParser parseRotationAttribute = new StringValueParser(addAttributeTagCheck, addAttributeAttributeCheck);
      changeRotationAttribute.addValueParser(parseRotationAttribute);
      moduloRotationAttribute.addValueParser(parseRotationAttribute);
      
      //swap values: x y; x1 y1; x2 y2
      
      Namecheck reval_TagCheck = new Namecheck(XMLEntry.BIT_NAME);
      reval_TagCheck.addSearchString("element");
      reval_TagCheck.addSearchString("attribute");
      reval_TagCheck.addSearchString("wire");
      reval_TagCheck.addSearchString("vertex");
      
      Namecheck reval_ParentTagCheck = new Namecheck(XMLEntry.BIT_PARENT_NAME);
      reval_ParentTagCheck.addSearchString("elements");
      reval_ParentTagCheck.addSearchString("element");
      reval_ParentTagCheck.addSearchString("signal");
      reval_ParentTagCheck.addSearchString("polygon");
      reval_ParentTagCheck.addSearchString("plain");
      
      
      Namecheck reval_AttributeNameCheckx = new Namecheck(XMLEntry.BIT_ATTRIBUTE_NAME);
      reval_AttributeNameCheckx.addSearchString("x");
      
      Namecheck reval_AttributeNameCheckx1 = new Namecheck(XMLEntry.BIT_ATTRIBUTE_NAME);
      reval_AttributeNameCheckx1.addSearchString("x1");
      
      Namecheck reval_AttributeNameCheckx2 = new Namecheck(XMLEntry.BIT_ATTRIBUTE_NAME);
      reval_AttributeNameCheckx2.addSearchString("x2");
      
      Namecheck reval_AttributeNameChecky = new Namecheck(XMLEntry.BIT_ATTRIBUTE_NAME);
      reval_AttributeNameChecky.addSearchString("y");
      
      Namecheck reval_AttributeNameChecky1 = new Namecheck(XMLEntry.BIT_ATTRIBUTE_NAME);
      reval_AttributeNameChecky1.addSearchString("y1");
      
      Namecheck reval_AttributeNameChecky2 = new Namecheck(XMLEntry.BIT_ATTRIBUTE_NAME);
      reval_AttributeNameChecky2.addSearchString("y2");
      
      //Swap x, y value
      ValueSwapper swapperxy = new ValueSwapper(reval_TagCheck, reval_AttributeNameCheckx, reval_AttributeNameChecky);
      swapperxy.addNamecheck(reval_ParentTagCheck);
      
      ValueChanger xCalc = new ValueChanger(ValueChanger.VALUE_CHANGER_SUBSTRACT_FROM_VALUE, boardYSize);
      
      ValueParser parseXVal = new ValueParser(reval_TagCheck, reval_AttributeNameCheckx);
      parseXVal.addNamecheck(reval_ParentTagCheck);
      
      //Swap x1, y1 value
      ValueSwapper swapperx1y1 = new ValueSwapper(reval_TagCheck, reval_AttributeNameCheckx1, reval_AttributeNameChecky1);
      swapperx1y1.addNamecheck(reval_ParentTagCheck);
      
      ValueParser parseX1Val = new ValueParser(reval_TagCheck, reval_AttributeNameCheckx1);
      parseX1Val.addNamecheck(reval_ParentTagCheck);
      
      //Swap x2, y2 value
      ValueSwapper swapperx2y2 = new ValueSwapper(reval_TagCheck, reval_AttributeNameCheckx2, reval_AttributeNameChecky2);
      swapperx2y2.addNamecheck(reval_ParentTagCheck);
      
      ValueParser parseX2Val = new ValueParser(reval_TagCheck, reval_AttributeNameCheckx2);
      parseX2Val.addNamecheck(reval_ParentTagCheck);
      
      //Calculate new x, x1, x2 value
      xCalc.addValueParser(parseXVal);
      xCalc.addValueParser(parseX1Val);
      xCalc.addValueParser(parseX2Val);
      
      //put together
      
      XMLReader reader_Merge1_Test = new XMLReader(new File("I:/eagle/MergeTest/Simple_Test.brd"));
      reader_Merge1_Test.addSearcher(addRotationAttribute);
      reader_Merge1_Test.addSearcher(parseRotationAttribute);
      reader_Merge1_Test.addSearcher(swapperxy);
      reader_Merge1_Test.addSearcher(swapperx1y1);
      reader_Merge1_Test.addSearcher(swapperx2y2);
      reader_Merge1_Test.addSearcher(parseXVal);
      reader_Merge1_Test.addSearcher(parseX1Val);
      reader_Merge1_Test.addSearcher(parseX2Val);
      
*/      
/*      

    //Test file
    
    //Rename
      Namecheck tagCheck1 = new Namecheck(XMLEntry.BIT_PARENT_NAME);
      tagCheck1.addSearchString("name");
      
      NameChanger rename1 = new NameChanger(tagCheck1, null, "File1_");
      
    
    //ReValue
      Namecheck valueCheck1 = new Namecheck(XMLEntry.BIT_NAME);
      valueCheck1.addSearchString("place");
      valueCheck1.addSearchString("position");
      valueCheck1.addSearchString("value");
      
      Namecheck valueParentCheck = new Namecheck(XMLEntry.BIT_PARENT_NAME);
      valueParentCheck.addSearchString("person");
      valueParentCheck.addSearchString("data");
      
      Namecheck valueAttributeCheck1 = new Namecheck(XMLEntry.BIT_ATTRIBUTE_NAME);
      valueAttributeCheck1.addSearchString("x");
      valueAttributeCheck1.addSearchString("value");
      
      Namecheck valueAttributeCheck2 = new Namecheck(XMLEntry.BIT_ATTRIBUTE_NAME);
      valueAttributeCheck2.addSearchString("y");
      
      StringValueParser parser1 = new StringValueParser(valueCheck1, valueAttributeCheck1);
      parser1.addNamecheck(valueParentCheck);
      StringValueParser parser2 = new StringValueParser(valueCheck1, valueAttributeCheck2);
      parser2.addNamecheck(valueParentCheck);
      
      ValueChanger changer1 = new ValueChanger(ValueChanger.VALUE_CHANGER_ADD, 2);
      changer1.addValueParser(parser1);
      ValueChanger changer2 = new ValueChanger(ValueChanger.VALUE_CHANGER_ADD, 5);
      changer2.addValueParser(parser2);
      
      ValueSwapper swapper = new ValueSwapper(valueCheck1, valueAttributeCheck1, valueAttributeCheck2);
      
    //Skipper        
      Namecheck ignoreCheck1 = new Namecheck(XMLEntry.BIT_NAME);
      ignoreCheck1.addSearchString("place");    
      
      NodeSkipper skipper1 = new NodeSkipper(ignoreCheck1);
      
      Namecheck addValueNameCheck = new Namecheck(XMLEntry.BIT_NAME);
      addValueNameCheck.addSearchString("place");
      
      Namecheck addValueAttributeNameCheck = new Namecheck(XMLEntry.BIT_ATTRIBUTE_NAME);
      addValueNameCheck.addSearchString("y");
      
      ValueAdder adder = new ValueAdder(addValueNameCheck, addValueAttributeNameCheck, "y", "0");
      
      XMLReader reader_Merge1_Test = new XMLReader(new File("I:/eagle/MergeTest/simple.xml"));
      reader_Merge1_Test.addSearcher(rename1);
      reader_Merge1_Test.addSearcher(adder);
      reader_Merge1_Test.addSearcher(parser1);
      reader_Merge1_Test.addSearcher(parser2);
      reader_Merge1_Test.addSearcher(swapper);
      
      
      
      XMLReader reader_Merge2_Test = new XMLReader(new File("I:/eagle/MergeTest/simple2.xml"));
      reader_Merge2_Test.addSearcher(skipper1);
      reader_Merge2_Test.addSearcher(adder);
      reader_Merge2_Test.addSearcher(parser1);
      reader_Merge2_Test.addSearcher(parser2);
      reader_Merge2_Test.addSearcher(swapper);
*/      

/*
      
      //Main Execution

      reader_Merge1_Test.read();
//      reader_Merge1_Test.add(reader_Merge2_Test);
      XMLWriter w = new XMLWriter(reader_Merge1_Test.getTree());
      w.write(new File("I:/eagle/MergeTest/Merge1_Test1.brd"));
*/
/*
    } catch (IOException ex) {
      Logger.getLogger(XMLMerge.class.getName()).log(Level.SEVERE, null, ex);
    } catch (ParsingException ex) {
      Logger.getLogger(XMLMerge.class.getName()).log(Level.SEVERE, null, ex);
    } 
*/


  }

    

  
}
