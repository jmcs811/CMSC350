/////////////////////////////
// Justin Casey
// CMSC 350
// Sep 14, 2019
//
// Main class to draw the
// gui.

package com.jcaseydev;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.EmptyStackException;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Main extends JFrame {

  private String expression;

  private Main() {
    // Setting title of the JFrame
    super("Infix Expression Evaluator");

    // Creating the needed panels for the program
    JPanel main = new JPanel();
    JPanel inputPanel = new JPanel();
    JPanel evalPanel = new JPanel();
    JPanel resultPanel = new JPanel();

    main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));

    // All of the UI elements needed for the program
    JLabel inputLabel = new JLabel("Enter Postfix expression:");
    JTextField inputText = new JTextField(null, 20);
    JButton evaluateButton = new JButton("Construct Tree");
    JButton clearTextButton = new JButton("Clear Expression");
    JLabel resultLabel = new JLabel("Infix Expression");
    JTextField resultText = new JTextField(null, 20);

    // Adding the UI elements to their respective panels
    inputPanel.add(inputLabel);
    inputPanel.add(inputText);

    evalPanel.add(evaluateButton);
    evalPanel.add(clearTextButton);

    resultPanel.add(resultLabel);
    resultPanel.add(resultText);
    resultText.setEditable(false);

    main.add(inputPanel);
    main.add(evalPanel);
    main.add(resultPanel);

    add(main);

    // Setting window options
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(420, 170);
    setLocationRelativeTo(null);
    setResizable(false);
    setVisible(true);

    // Action lister to evaluate the expression
    evaluateButton.addActionListener((ActionEvent e) -> {
      expression = inputText.getText();

      try {
        if (expression.isEmpty()) {
          throw new NullPointerException();
        } else {
          Evaluator evaluator = new Evaluator();
          resultText.setText(evaluator.evaluate(expression));
        }
      } catch (InvalidTokenException e1) {
        JOptionPane.showMessageDialog(
            null,
            "Invalid Token: " + e1.getMessage(),
            "Error", JOptionPane.ERROR_MESSAGE
        );
      } catch (NullPointerException | IOException h) {
        JOptionPane.showMessageDialog(
            null,
            "Expression Must be in Postfix format",
            "Error",
            JOptionPane.ERROR_MESSAGE
        );
      } catch (EmptyStackException e1) {
        JOptionPane.showMessageDialog(
            null,
            "Invalid Expression: Check your expression",
            "Error", JOptionPane.ERROR_MESSAGE
        );
      }
    });

    // Action listener to clear the input text
    clearTextButton.addActionListener((
        ActionEvent e) ->

    {
      clearText(inputText);
    });
  }

  // Helper method to clear the text field
  private static void clearText(JTextField textField) {
    textField.setText("");
  }

  public static void main(String[] args) {
    new Main();
  }
}
