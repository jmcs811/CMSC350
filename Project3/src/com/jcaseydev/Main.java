/////////////////////////////
// Filename: Main.java
// Author: Justin Casey
// Data: 26 Sep 2019
//
// class the generates the GUI

package com.jcaseydev;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Main extends JFrame {

  private String list;
  private String result;

  private Main() {
    // Setting title of the JFrame
    super("Binary Search Tree Sort");

    // Creating the needed panels for the program
    JPanel main = new JPanel();
    JPanel inputPanel = new JPanel();
    JPanel buttonPanel = new JPanel();
    JPanel resultPanel = new JPanel();
    JPanel optionsPanel = new JPanel();
    JPanel sortPanel = new JPanel();
    JPanel numTypePanel = new JPanel();

    // set the layouts for each panel
    main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
    buttonPanel.setLayout(new GridBagLayout());
    optionsPanel.setLayout(new GridBagLayout());
    sortPanel.setLayout(new BoxLayout(sortPanel, BoxLayout.Y_AXIS));
    numTypePanel.setLayout(new BoxLayout(numTypePanel, BoxLayout.Y_AXIS));

    // All of the UI elements needed for the program
    JLabel inputLabel = new JLabel("Original List");
    JTextField inputText = new JTextField(null, 20);
    JButton sortButton = new JButton("Sort");
    JButton clearTextButton = new JButton("Clear List");
    JLabel resultLabel = new JLabel("Sorted List");
    JTextField resultText = new JTextField(null, 20);
    JRadioButton ascendButton = new JRadioButton("Ascending");
    JRadioButton descendButton = new JRadioButton("Descending");
    JRadioButton intButton = new JRadioButton("Integer");
    JRadioButton fracButton = new JRadioButton("Fraction");

    // Adding the UI elements to their respective panels
    inputPanel.add(inputLabel);
    inputPanel.add(inputText);

    resultPanel.add(resultLabel);
    resultPanel.add(resultText);
    resultText.setEditable(false);

    buttonPanel.add(sortButton);
    buttonPanel.add(clearTextButton);

    sortPanel.add(ascendButton);
    sortPanel.add(descendButton);
    sortPanel.setBorder(BorderFactory.createTitledBorder("Sort Order"));
    ascendButton.setSelected(true);

    numTypePanel.add(intButton);
    numTypePanel.add(fracButton);
    numTypePanel.setBorder(BorderFactory.createTitledBorder("Num Type"));
    intButton.setSelected(true);

    // options set up
    ButtonGroup sortingGroup = new ButtonGroup();
    sortingGroup.add(ascendButton);
    sortingGroup.add(descendButton);

    ButtonGroup numGroup = new ButtonGroup();
    numGroup.add(intButton);
    numGroup.add(fracButton);

    optionsPanel.add(sortPanel);
    optionsPanel.add(numTypePanel);

    // adding all UI sections to the main panel
    main.add(inputPanel);
    main.add(resultPanel);
    main.add(buttonPanel);
    main.add(optionsPanel);

    add(main);

    // Setting window options
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(470, 250);
    setLocationRelativeTo(null);
    setResizable(false);
    setVisible(true);

    // Action lister to perform sort
    sortButton.addActionListener((ActionEvent e) -> {
      list = inputText.getText();

      try {
        if (list.isEmpty()) {
          throw new NullPointerException();
        }

        String[] items = list.split(" ");

        // perform correct action based on options selected
        if (intButton.isSelected()) {
          // Integer sort
          BSTSort<Integer> tree = new BSTSort<>(Integer.valueOf(items[0]));

          // building tree
          for (int i = 1; i < items.length; i++) {
            tree.insert(tree.rootNode, Integer.valueOf(items[i]));
          }

          // ascending or descending sort
          if (ascendButton.isSelected()) {
            result = tree.inOrder(tree.rootNode);
          } else {
            result = tree.inOrderDesc(tree.rootNode);
          }
        } else {
          // Fraction sort
          BSTSort<Fraction> tree = new BSTSort<>(new Fraction(items[0]));

          // building tree
          for (int i = 1; i < items.length; i++) {
            Fraction fraction = new Fraction(items[i]);
            tree.insert(tree.rootNode, fraction);
          }

          // ascending or descending sort
          if (ascendButton.isSelected()) {
            result = tree.inOrder(tree.rootNode);
          } else {
            result = tree.inOrderDesc(tree.rootNode);
          }
        }
      } catch (NumberFormatException nfe) {
        JOptionPane.showMessageDialog(
            null,
            "Malformed Input",
            "Error",
            JOptionPane.ERROR_MESSAGE
        );
      } catch (NullPointerException npe) {
        JOptionPane.showMessageDialog(
            null,
            "Please Enter a list to sort",
            "Error",
            JOptionPane.ERROR_MESSAGE
        );
      } catch (MalformedFractionException mfe) {
        JOptionPane.showMessageDialog(
            null,
            "Illegal fraction: " + mfe.getMessage(),
            "Error",
            JOptionPane.ERROR_MESSAGE
        );
      }
      // display the sorted list
      resultText.setText(result);
    });

    // Action listener to clear the input text
    clearTextButton.addActionListener((ActionEvent e) -> {
      inputText.setText("");
    });
  }

  public static void main(String[] args) {
    // write your code here
    new Main();
  }
}
