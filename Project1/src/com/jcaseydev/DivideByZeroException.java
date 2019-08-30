package com.jcaseydev;

/////////////////////////////
// Justin Casey
// CMSC 350
// August 29, 2019
//
// This is an exception for
// catching divde by zero
// errors

class DivideByZeroException extends Exception {

  DivideByZeroException(String message) {
    super(message);
  }
}
