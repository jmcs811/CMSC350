/////////////////////////////
// Filename: Fraction.java
// Author: Justin Casey
// Data: 26 Sep 2019
//
// class that defines fraction objects

package com.jcaseydev;

public class Fraction implements Comparable<Fraction> {

  // numerator, denominator, and decimal value
  private int top;
  private int bottom;
  private double val;

  // constructor
  Fraction(String fraction) throws MalformedFractionException {
    String[] parsedList = fraction.split("/");
    if (parsedList.length != 2) {
      throw new MalformedFractionException(fraction);
    }
    top = Integer.parseInt(parsedList[0]);
    bottom = Integer.parseInt(parsedList[1]);
    val = (double) top / bottom;
  }

  // getter for fraction value
  private double getVal() {
    return val;
  }

  // required method to compare two fraction objects
  @Override
  public int compareTo(Fraction o) {
    // equal 0, greater 1, lesser -1
    return Double.compare(val, o.getVal());
  }

  // method to convert fraction objects to "top/bottom" format
  @Override
  public String toString() {
    return top + "/" + bottom;
  }
}
