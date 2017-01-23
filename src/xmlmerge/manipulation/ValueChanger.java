/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlmerge.manipulation;

import java.util.Observable;
import java.util.Observer;

/**
 * Changes a parsed value (p) by a referenced value(v)
 * the parsed value is gotten by a ValueParser instance
 * there can be more than one parser, which values are changed,
 * but always with the same operation
 * @author Richard
 */
public class ValueChanger {
  
  /**
   * add value(v) to parsed value(p)
   */
  public static final int VALUE_CHANGER_ADD = 1;
  
  /**
   * substract value(v) from parsed value(p): p-v
   */
  public static final int VALUE_CHANGER_SUBSTRACT_FROM_VALUE = 21;
  
  /**
   * substract parsed value(p) from value(v): v-p
   */
  public static final int VALUE_CHANGER_SUBSTRACT_FROM_PARSEDVALUE = 22;
  
  /**
   * multiply value(v) and parsed value(p)
   */
  public static final int VALUE_CHANGER_MULTIPLY = 3;
  
  /**
   * divide value(v) by parsed value(p): v / p
   */
  public static final int VALUE_CHANGER_DIVIDE_BY_VALUE = 41;
  
  /**
   * divide parsed value(p) by value(v): p / v
   */
  public static final int VALUE_CHANGER_DIVIDE_BY_PARSEDVALUE = 42;
  
  /**
   * perform modulo operation: parsed value (p) modulo (v): p % v
   */
  public static final int VALUE_CHANGER_MODULO_BY_VALUE = 51;
  
  /**
   * perform modulo operation: value (v) modulo parsed value (p): v % p
   */
  public static final int VALUE_CHANGER_MODULO_BY_PARSEDVALUE = 52;
  
  private final int mode;
  private final double value;
  
  public ValueChanger(int mode, double value) {
    this.mode = mode;
    this.value = value;
  }
  
  public void addValueParser(ValueParser parser) {
    parser.addValueChanger(this);
  }
  
  public void removeValueParser(ValueParser parser) {
    parser.removeValueChanger(this);
  }
  
  public double changeValue(double valueToChange) {
    double retVal = 0;
    switch (mode) {
      case VALUE_CHANGER_ADD: {
        retVal = valueToChange + this.value;
        break;
      }
      case VALUE_CHANGER_SUBSTRACT_FROM_VALUE: {
        retVal = this.value - valueToChange;
        break;
      }
      case VALUE_CHANGER_SUBSTRACT_FROM_PARSEDVALUE: {
        retVal = valueToChange - this.value;
        break;
      }
      case VALUE_CHANGER_MULTIPLY: {
        retVal = this.value * valueToChange;
        break;
      }
      case VALUE_CHANGER_DIVIDE_BY_VALUE: {
        retVal = valueToChange / this.value;
        break;
      }
      case VALUE_CHANGER_DIVIDE_BY_PARSEDVALUE: {
        retVal = this.value / valueToChange;
        break;
      }
      case VALUE_CHANGER_MODULO_BY_VALUE: {
        retVal = valueToChange % this.value;
        break;
      }
      case VALUE_CHANGER_MODULO_BY_PARSEDVALUE: {
        retVal = this.value % valueToChange;
      }
      default: break;
    }
    return retVal;
  }
  
/*
  @Override
  public void update(Observable o, Object arg) {
    if (o instanceof ValueParser)
    {
      ValueParser p = (ValueParser) o;
      switch (mode) {
        case VALUE_CHANGER_ADD: {
          p.get
          break;
        }
        case VALUE_CHANGER_SUBSTRACT_FROM_VALUE: {
          break;
        }
        case VALUE_CHANGER_SUBSTRACT_FROM_PARSEDVALUE: {
          break;
        }
        case VALUE_CHANGER_MULTIPLY: {
          break;
        }
        case VALUE_CHANGER_DIVIDE_BY_VALUE: {
          break;
        }
        case VALUE_CHANGER_DIVIDE_BY_PARSEDVALUE: {
          break;
        }
        default: break;
      }
    }
  }
 */ 
  
}
