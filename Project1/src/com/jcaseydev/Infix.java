package com.jcaseydev;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Pattern;

class Infix {

  private static final String LEFT_PAREN = "(";
  private static final String RIGHT_PAREN = ")";
  private Stack<String> operand = new Stack<>();
  private Stack<String> operators = new Stack<>();
  
  String evaluate(String equation) {
    List<String> tokens = new ArrayList<>();

    for (int i = 0; i < equation.length(); i++) {
      tokens.add(Character.toString(equation.charAt(i)));
    }
    
    for (String token : tokens) {
      Pattern operatorsPattern = Pattern.compile("[*/+\\-]");
      Pattern operandPat = Pattern.compile("[\\d.?]+");

      if (token.matches(String.valueOf(operandPat))) {
        operand.push(token);
      } else if (token.equals(LEFT_PAREN)) {
        operators.push(token);
      } else if (token.equals(RIGHT_PAREN)) {
        while (!operators.peek().equals(LEFT_PAREN)) {
          operand.push(calculation(operand.pop(), operators.pop(), operand.pop()));
        }
        operators.pop();
      } else if (token.matches(String.valueOf(operatorsPattern))) {
        while (!operators.empty() && precedence(operators.peek()) >= precedence(token)) {
          operand.push(calculation(operand.pop(), operators.pop(), operand.pop()));
        }
        operators.push(token);
      }
    }

    while (!operators.empty()) {
      operand.push(calculation(operand.pop(), operators.pop(), operand.pop()));
    }

    return operand.pop();
  }

  private String calculation(String num2, String operator, String num1) {
    int first = Integer.parseInt(num1);
    int second = Integer.parseInt(num2);
    int result = 0;

    switch (operator) {
      case "+":
        result = first + second;
        break;
      case "-":
        result = first - second;
        break;
      case "*":
        result = first * second;
        break;
      case "/":
        result = first / second;
        break;
    }
    return Integer.toString(result);
  }

  private int precedence(String operator) {
    int precedence = 0;

    switch (operator) {
      case "+":
      case "-":
        precedence = 1;
        break;
      case "*":
      case "/":
        precedence = 2;
    }
    return precedence;
  }
}
