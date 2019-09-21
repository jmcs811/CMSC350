/////////////////////////////
// Justin Casey
// CMSC 350
// Sep 14, 2019
//
// node interface for
// operator and operand
// node classes

package com.jcaseydev;

import java.io.IOException;

interface Node {
  String inOrder();
  String postOrder() throws IOException;
  void post() throws IOException;
}
