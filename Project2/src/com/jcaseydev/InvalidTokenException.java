/////////////////////////////
// Justin Casey
// CMSC 350
// Sep 14, 2019
//
// Exception class for
// incorrect input

package com.jcaseydev;

public class InvalidTokenException extends Exception {

  public InvalidTokenException(char c) {
    super(String.valueOf(c));
  }
}
