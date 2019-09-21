/////////////////////////////
// Justin Casey
// CMSC 350
// Sep 14, 2019
//
// Evalutor class that will
// convert postfix to infix

package com.jcaseydev;

import java.io.IOException;
import java.util.EmptyStackException;
import java.util.Stack;

class Evaluator {

  private Stack<Node> stack = new Stack<>();
  private Node op;

  private boolean isOperator(char c) {
    return c == '+' || c == '*' || c == '-' || c == '/' || c == '^';
  }

  String evaluate(String expression)
      throws EmptyStackException, IOException, InvalidTokenException {

    char[] chars = expression.toCharArray();

    for (char c : chars) {
      if (c == ' ') {
        continue;
      }

      if (!Character.isDigit(c) && !isOperator(c)) {
        throw new InvalidTokenException(c);
      }

      if (!isOperator(c)) {
        stack.push(new OperandNode(c));
      } else {
        op = new OperatorNode(c, stack.pop(), stack.pop());
        stack.push(op);
      }
    }
    op.post();
    return stack.pop().inOrder();
  }
}
