package com.jcaseydev;

import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Main extends JFrame {

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

    ButtonGroup sortingGroup = new ButtonGroup();
    sortingGroup.add(ascendButton);
    sortingGroup.add(descendButton);

    ButtonGroup numGroup = new ButtonGroup();
    numGroup.add(intButton);
    numGroup.add(fracButton);

    optionsPanel.add(sortPanel);
    optionsPanel.add(numTypePanel);

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

    // Action lister to evaluate the expression

    // Action listener to clear the input text
  }

  public static void main(String[] args) {
    // write your code here
//        int[] arr = {1,4,33,2,4,56,34,1,12};
    List<Integer> arr = new ArrayList<>();
    arr.add(9);
    arr.add(88);
    arr.add(7);
    arr.add(6);
    arr.add(5);
    arr.add(4);
    arr.add(3);
    arr.add(2);
    arr.add(1);
    arr.add(1);
    arr.add(1);
    System.out.println("Original Array " + arr.toString());
    BSTSort<Integer> tree = new BSTSort<>(arr.get(0));

    for (int i = 1; i < arr.size(); i++) {
      tree.insert(tree.rootNode, arr.get(i));
    }

    System.out.println("Sorted Array");
    System.out.println(tree.inOrderDesc(tree.rootNode));
  }
}
