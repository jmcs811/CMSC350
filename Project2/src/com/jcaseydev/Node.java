package com.jcaseydev;

import java.io.IOException;

interface Node {
  String inOrder();
  String postOrder() throws IOException;
  void post();
}
