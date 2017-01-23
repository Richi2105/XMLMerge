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
import xmlmerge.search.Namecheck;
import xmlmerge.search.NodeSkipper;
import xmlmerge.xmlReader.XMLReader;
import xmlmerge.xmlWriter.XMLWriter;

/**
 *
 * @author Richard
 */
public class XMLMerge {
  
//  private final File file;
  
  public XMLMerge() {
    
  }

  


  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    
    EagleSchematicDataManipulation esdm = new EagleSchematicDataManipulation(new File("I:\\eagle\\MergeTest\\Merge1.sch"));
    esdm.rename("Merge1");
    
    
    EagleSchematicDataManipulation esdm_2 = new EagleSchematicDataManipulation(new File("I:\\eagle\\MergeTest\\Merge5.sch"));
    esdm_2.rename("Merge2");
    esdm_2.move(200, 0);
    esdm.merge(esdm_2);
    
    EagleBoardDataManipulation ebdm = new EagleBoardDataManipulation(new File ("I:\\eagle\\MergeTest\\Merge1.brd"));
    ebdm.rename("Merge1");
    
    
    EagleBoardDataManipulation ebdm_2 = new EagleBoardDataManipulation(new File ("I:\\eagle\\MergeTest\\Merge2.brd"));
    ebdm_2.rename("Merge2");
    ebdm_2.move(40, 0);
    ebdm.merge(ebdm_2);
    
    esdm.read();
//    ebdm.read();
    
    esdm.write(new File("I:\\eagle\\MergeTest\\Merge1out.sch"));
//    ebdm.write(new File("I:\\eagle\\MergeTest\\Merge1out.brd"));
    
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
