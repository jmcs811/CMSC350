package com.jcaseydev;

/////////////////////////////
// Justin Casey
// CMSC 350
// August 29, 2019
//
// The gui is defined in this
// class. It uses the Infix
// and DivideByZero classes

import java.awt.event.ActionEvent;
import java.util.EmptyStackException;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Main extends JFrame {

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
    JLabel inputLabel = new JLabel("Enter infix expression:");
    JTextField inputText = new JTextField(null, 20);
    JButton evaluateButton = new JButton("Evaluate");
    JButton clearTextButton = new JButton("Clear Expression");
    JLabel resultLabel = new JLabel("Result");
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
    setSize(400, 150);
    setLocationRelativeTo(null);
    setResizable(false);
    setVisible(true);

    // Action lister to evaluate the expression
    evaluateButton.addActionListener((ActionEvent e) -> {
      try {
        int answer = Infix.evaluate(inputText.getText());
        resultText.setText(Integer.toString(answer));
      } catch (DivideByZeroException d) {
        d.printStackTrace();
        JOptionPane.showMessageDialog(
            null,
            "Attempt to divide by zero",
            "Error",
            JOptionPane.ERROR_MESSAGE
        );
        clearText(inputText);
      } catch (EmptyStackException d) {
        d.printStackTrace();
        JOptionPane.showMessageDialog(
            null,
            "Invalid Expression",
            "Error",
            JOptionPane.ERROR_MESSAGE
        );
        clearText(inputText);
      }
    });

    // Action listener to clear the input text
    clearTextButton.addActionListener((ActionEvent e) -> {
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
