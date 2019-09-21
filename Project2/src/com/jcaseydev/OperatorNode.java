/////////////////////////////
// Justin Casey
// CMSC 350
// Sep 14, 2019
//
// operator node

package com.jcaseydev;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OperatorNode implements Node {

  private char op;
  private Node right, left;
  private static int i;
  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
  private File file = new File(format.format(new Date()) + "-addr_instruction.txt");

  // constructor
  OperatorNode(char op, Node right, Node left) {
    this.op = op;
    this.right = right;
    this.left = left;

    opEval(op);
  }

  @Override
  public String inOrder() {
    return "(" + left.inOrder() + " " + op + " " + right.inOrder() + ")";
  }

  @Override
  public String postOrder() throws IOException {
    String leftVal = left.postOrder();
    String rightVal = right.postOrder();
    String operator = opEval(this.op);

    String result = "R" + i++;

    String step = operator + " " + result + " " + leftVal + " " + rightVal;
    BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
    bw.write(step);
    bw.newLine();
    bw.close();

    return result;
  }

  @Override
  public void post() throws IOException {
    i = 0;
    postOrder();
  }

  private String opEval(char operator) {
    String instruction = "";
    switch (op) {
      case '*':
        instruction = "Mul";
        break;
      case '+':
        instruction = "Add";
        break;
      case '-':
        instruction = "Sub";
        break;
      case '/':
        instruction = "Div";
        break;
    }

    return instruction;
  }
}
