package com.jcaseydev;

/////////////////////////////
// Justin Casey
// CMSC 350
// August 29, 2019
//
// This class will evaluate
// infix expressions. This
// is accomplished by using
// two stacks.

import java.util.EmptyStackException;
import java.util.Stack;

class Infix {

  private static final char LEFT_PAREN = '(';
  private static final char RIGHT_PAREN = ')';

  static int evaluate(String expression) throws DivideByZeroException, EmptyStackException {
    char[] tokens = expression.toCharArray();

    // Stacks to hold values and ops
    Stack<Integer> values = new Stack<>();
    Stack<Character> ops = new Stack<>();

    for (int i = 0; i < tokens.length; i++) {
      // if current token is a space, ignore it
      if (tokens[i] == ' ') {
        continue;
      }

      // if current token is a number, push it onto values stack
      if (tokens[i] >= '0' && tokens[i] <= '9') {
        StringBuilder stringBuffer = new StringBuilder();
        while (i < tokens.length && tokens[i] >= '0' && tokens[i] <= '9') {
          stringBuffer.append(tokens[i++]);
        }
        i--;

        values.push(Integer.parseInt(stringBuffer.toString()));
      }

      // if opening parentheses push onto ops
      else if (tokens[i] == LEFT_PAREN) {
        ops.push(tokens[i]);
      }

      // if closing parentheses, solve the equation
      else if (tokens[i] == RIGHT_PAREN) {
        while (ops.peek() != LEFT_PAREN) {
          values.push(calculate(values.pop(), ops.pop(), values.pop()));
        }
        ops.pop();
      } else if (tokens[i] == '+' || tokens[i] == '-' || tokens[i] == '*' || tokens[i] == '/') {
        while (!ops.empty() && precedence(tokens[i], ops.peek())) {
          values.push(calculate(values.pop(), ops.pop(), values.pop()));
        }
        // remove the LEFT paren
        ops.push(tokens[i]);
      }
    }

    // finish what ever is left on the stacks
    while (!ops.empty()) {
      values.push(calculate(values.pop(), ops.pop(), values.pop()));
    }

    // pop off the answer
    return values.pop();
  }

  // method to perform the specified operation
  private static int calculate(int number2, char operation, int number1)
      throws DivideByZeroException {
    switch (operation) {
      case '+':
        return number1 + number2;
      case '-':
        return number1 - number2;
      case '*':
        return number1 * number2;
      case '/':
        if (number2 == 0) {
          throw new DivideByZeroException("Cant / by 0");
        }
        return number1 / number2;
    }
    return 0;
  }

  // method to determine precedence
  private static boolean precedence(char op1, char op2) {
    if (op2 == LEFT_PAREN || op2 == RIGHT_PAREN) {
      return false;
    }
    return (op1 != '*' && op1 != '/') || (op2 != '+' && op2 != '-');
  }
}
