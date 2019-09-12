package com.jcaseydev;

import java.io.IOException;
import java.util.EmptyStackException;
import java.util.Stack;

public class Evaluator {

  private Stack<Node> stack = new Stack<>();
  private Node op;

  private boolean isOperator(char c) {
    return c == '+' || c == '*' || c == '-' || c == '/' || c == '^';
  }

  String evaluate(String expression) throws EmptyStackException, IOException {
    char[] chars = expression.toCharArray();

    for (char c : chars) {
      if (!isOperator(c)) {
        // handle error
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
