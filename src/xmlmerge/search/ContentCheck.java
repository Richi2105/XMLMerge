/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlmerge.search;

import xmlmerge.data.XMLEntry;

/**
 *
 * @author Richard
 */
public abstract class ContentCheck {
  
  protected int attributeMatchPosition;
  protected final int searchtype;
  
  protected ContentCheck(int searchtype) {
    this.searchtype = searchtype;
    this.attributeMatchPosition = -1;
  }
  
  public abstract boolean search(XMLEntry entry);
  protected abstract boolean search(String s);
  
  /**
   * if this ContentCheck searches Attributes (either names or values)
   * one might want to know the position of a match. This function returns
   * the match position of the elements attribute array
   * @return a position inside the last searched element. -1 if no string was
   * found or this Namecheck does not search inside attributes.
   */
  public int getAttributeMatchPosition() {
    return this.attributeMatchPosition;
  }
  
  
  
}
