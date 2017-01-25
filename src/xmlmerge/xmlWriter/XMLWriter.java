/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlmerge.xmlWriter;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import nu.xom.Document;
import nu.xom.Serializer;
import xmlmerge.XMLMerge;
import xmlmerge.data.XMLTree;
import org.vishia.xmlSimple.SimpleXmlOutputter;
import org.vishia.xmlSimple.XmlNode;

/**
 *
 * @author Richard
 */
public class XMLWriter {
  
  private final XMLTree tree;
  
  public XMLWriter (XMLTree tree) {
    this.tree = tree;
  }
  
  public void write (File file) {
    
    try {
      DataOutputStream outstream = new DataOutputStream(new FileOutputStream(file));
      Document doc = new Document(tree.write());
      Serializer xmlout = new Serializer(outstream, "UTF-8");
      xmlout.setIndent(0);
      xmlout.setLineSeparator("\n");
      xmlout.write(doc);
    } catch (IOException ex) {
      Logger.getLogger(XMLMerge.class.getName()).log(Level.SEVERE, null, ex);
    } 
  }
  
  public void writeSimpleXML(File file) {
    OutputStreamWriter osw = null;
    try {      
      SimpleXmlOutputter sxmlo = new SimpleXmlOutputter();
      osw = new OutputStreamWriter(new FileOutputStream(file));
      XmlNode node = this.tree.writeSimpleXML();
      sxmlo.write(osw, node);
    } catch (FileNotFoundException ex) {
      Logger.getLogger(XMLWriter.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
      Logger.getLogger(XMLWriter.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      try {
        osw.close();
      } catch (IOException ex) {
        Logger.getLogger(XMLWriter.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }
  
}
