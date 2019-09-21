/////////////////////////////
// Justin Casey
// CMSC 350
// Sep 14, 2019
//
// operand node

package com.jcaseydev;

public class OperandNode implements Node {

  char val;

  // constructor
  OperandNode(char val) {
    this.val = val;
  }

  @Override
  public String inOrder() {
    return String.valueOf(val);
  }

  @Override
  public String postOrder() {
    return String.valueOf(val);
  }

  public void post() {
  }
}
