/////////////////////////////
// Filename: MalformedFractionException.java
// Author: Justin Casey
// Data: 26 Sep 2019
//
// exception class for malformed fraction input

package com.jcaseydev;

class MalformedFractionException extends Exception {

  MalformedFractionException(String item) {
    super(item);
  }
}
