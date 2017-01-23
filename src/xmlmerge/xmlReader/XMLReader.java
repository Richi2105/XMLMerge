/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlmerge.xmlReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.ParsingException;
import xmlmerge.data.XMLEntry;
import xmlmerge.data.XMLTree;
import xmlmerge.manipulation.NameChanger;
import xmlmerge.search.NodeSkipper;
import xmlmerge.search.Searcher;

/**
 *
 * @author Richard
 */
public class XMLReader {
  
  private final Document doc;
  private final XMLTree tree;
  private final ArrayList<Searcher> searcher;
  
  public XMLReader (File file) throws IOException, ParsingException {
    this.searcher = new ArrayList<>();
    FileInputStream fis = new FileInputStream(file);
    Builder b = new Builder();
    doc = b.build(fis);
    this.tree = new XMLTree();
  }
  
  public XMLReader (String file) throws ParsingException, IOException {
    this.searcher = new ArrayList<>();
    Builder b = new Builder();
    doc = b.build(file, null);
    this.tree = new XMLTree();
  }
  
  public void addSearcher(Searcher searcher) {
    this.searcher.add(searcher);
  }
  
  public void read() {
    this.tree.fill(doc, this);
  }
  
  public XMLEntry getNewEntry(Element e, XMLEntry parent, int depth) {
    XMLEntry newEntry = null;

    newEntry = new XMLEntry(e, parent, depth, this);
    
    if (this.validate(newEntry) == Searcher.SEARCHER_DELETE_ENTRY)
    {
      newEntry = null;
    }
    
    return newEntry;
  }
  
  public int validate(XMLEntry entry)
  {
    for (Searcher s : searcher)
    {
      if (s.search(entry) == Searcher.SEARCHER_DELETE_ENTRY)
      {
        return Searcher.SEARCHER_DELETE_ENTRY;
      }
    }
    return 0;
  }
  
  public void add(XMLReader anotherReader) {
    this.tree.add(anotherReader.getDocument(), anotherReader);
  } 
  
  public Document getDocument() {
    return doc;
  }
  
  public XMLTree getTree() {
    return tree;
  }
  
}
