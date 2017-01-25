/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlmerge.eagle;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.logging.Level;
import nu.xom.ParsingException;
import rslogger.RSLogger;
import xmlmerge.namesearchFactory.SkipNodeFactory;
import xmlmerge.search.NodeSkipper;
import xmlmerge.xmlReader.XMLReader;
import xmlmerge.xmlWriter.XMLWriter;

/**
 *
 * @author Richard
 */
public abstract class EagleDataManipulation {
  
  protected XMLReader reader;
  protected final ArrayList<EagleDataManipulation> merger;
  protected static final String BADLINE = "<!DOCTYPE eagle SYSTEM \"eagle.dtd\">";
  
  public EagleDataManipulation(File inputFile) {
    this.merger = new ArrayList<>();
    try {
      FileInputStream fis = new FileInputStream(inputFile);
      //DataInputStream dis = new DataInputStream(fis);
      InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
      System.out.println(isr.getEncoding());
      
//      BufferedInputStream bis = new BufferedInputStream(isr);
//      new FileReader(inputFile)
      BufferedReader buffer = new BufferedReader(isr);


      String file = "";
      String line;
      while (buffer.ready()) {
        line = buffer.readLine();
        if (line.contains(BADLINE)) {
          int remPos = line.indexOf(line);
          file = file.concat(line.subSequence(0, remPos).toString());
          file = file.concat(line.subSequence(remPos+BADLINE.length(), line.length()).toString());
          file = file.concat("\n");
        }
        else {
          file = file.concat(line);
          file = file.concat("\n");
        }
      }
      buffer.close();
      this.reader = new XMLReader(file);
/*      
      FileOutputStream fos = new FileOutputStream(new File(inputFile.getAbsolutePath().concat("_test")));
      DataOutputStream dos = new DataOutputStream(fos);
      dos.writeBytes(file);
      dos.flush();
      dos.close();
*/       
      
    } catch (FileNotFoundException ex) {
      RSLogger.getLogger().log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
      RSLogger.getLogger().log(Level.SEVERE, null, ex);
    } catch (ParsingException ex) {
      RSLogger.getLogger().log(Level.SEVERE, null, ex);
    }
  }
  
  abstract public void rename(String prefix);
  
  abstract public void move(double x, double y);
  
  public void merge(EagleDataManipulation merger) {
    this.merger.add(merger);
    NodeSkipper skipper = new NodeSkipper(SkipNodeFactory.get_skipTagCheck());
    merger.reader.addSearcher(skipper);
  }
  
  public void read() {
    this.reader.read();
    for (EagleDataManipulation edm : this.merger) {
      this.reader.add(edm.reader);
    }
  }
  
  public void write(File file) {
    XMLWriter w = new XMLWriter(this.reader.getTree());
    w.write(file);
  }
  
  public void writeSimpleXML(File file) {
    XMLWriter w = new XMLWriter(this.reader.getTree());
    w.writeSimpleXML(file);
  }
  
}
