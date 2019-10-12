package com.jcaseydev;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Main extends JFrame {

  private String fileName;
  private String className;
  private DirectedGraph<String> directedGraph;

  private Main() {
    super("Class Dependency Graph");

    // create gui panels
    JPanel mainPanel = new JPanel();
    JPanel labelPanel = new JPanel();
    JPanel textPanel = new JPanel();
    JPanel buttonPanel = new JPanel();
    JPanel inputPanel = new JPanel();
    JPanel outputPanel = new JPanel();

    // set panel layouts
    mainPanel.setLayout(new GridBagLayout());
    labelPanel.setLayout(new GridBagLayout());
    textPanel.setLayout(new GridBagLayout());
    buttonPanel.setLayout(new GridBagLayout());
    inputPanel.setLayout(new GridBagLayout());
    outputPanel.setLayout(new BorderLayout());

    // create all gui elements (buttons, fields, etc)
    JButton graphButton = new JButton("Build Directed Graph");
    JButton topologicalButton = new JButton("Topological Order");
    JButton clearTextButton = new JButton("Clear Text");
    JTextField fileTextField = new JTextField(null, 20);
    JTextField classTextField = new JTextField(null, 20);
    JLabel fileLabel = new JLabel("Input File Name:");
    JLabel classLabel = new JLabel("Class to recompile:");
    JTextArea outputText = new JTextArea();
    JScrollPane scrollPane = new JScrollPane(outputText);

    // add elements to panels
    GridBagConstraints constraints = new GridBagConstraints();
    constraints.insets = new Insets(5, 5, 5, 5);

    constraints.gridx = 0;
    constraints.gridy = 0;
    constraints.weightx = 1;
    constraints.weighty = .5;
    labelPanel.add(fileLabel, constraints);

    constraints.gridx = 0;
    constraints.gridy = 1;
    constraints.weightx = 1;
    constraints.weighty = .5;
    labelPanel.add(classLabel, constraints);

    constraints.gridx = 0;
    constraints.gridy = 0;
    constraints.weightx = 1;
    constraints.weighty = .5;
    textPanel.add(fileTextField, constraints);

    constraints.gridx = 0;
    constraints.gridy = 1;
    constraints.weightx = 1;
    constraints.weighty = .5;
    textPanel.add(classTextField, constraints);

    constraints.gridx = 0;
    constraints.gridy = 0;
    constraints.weightx = 1;
    constraints.weighty = .5;
    buttonPanel.add(graphButton, constraints);

    constraints.gridx = 0;
    constraints.gridy = 1;
    constraints.weightx = 1;
    constraints.weighty = .5;
    buttonPanel.add(topologicalButton, constraints);

    constraints.gridx = 0;
    constraints.gridy = 2;
    constraints.weightx = 1;
    constraints.weighty = 0.5;
    buttonPanel.add(clearTextButton, constraints);

    outputPanel.setBorder(BorderFactory.createTitledBorder("Recompilation Order"));
    outputText.setLineWrap(true);
    outputText.setWrapStyleWord(true);
    outputText.setEditable(false);
    outputPanel.add(scrollPane);

    constraints.fill = GridBagConstraints.BOTH;
    inputPanel.setBorder(BorderFactory.createTitledBorder(""));

    constraints.gridx = 0;
    constraints.gridy = 0;
    constraints.weightx = 1;
    constraints.weighty = 1;
    inputPanel.add(labelPanel, constraints);

    constraints.gridx = 1;
    constraints.gridy = 0;
    constraints.weightx = 1;
    constraints.weighty = 1;
    inputPanel.add(textPanel, constraints);

    constraints.gridx = 2;
    constraints.gridy = 0;
    constraints.weightx = 1;
    constraints.weighty = 1;
    inputPanel.add(buttonPanel, constraints);

    constraints.insets = new Insets(1, 2, 1, 2);
    constraints.gridx = 0;
    constraints.gridy = 0;
    constraints.weightx = 1;
    constraints.weighty = 0;
    mainPanel.add(inputPanel, constraints);

    constraints.gridx = 0;
    constraints.gridy = 1;
    constraints.weightx = 1;
    constraints.weighty = 1;
    mainPanel.add(outputPanel, constraints);

    add(mainPanel);

    // set jframe settings
    setSize(650, 400);
    setVisible(true);
    setResizable(false);

    // graph button listener
    graphButton.addActionListener((ActionEvent e) -> {
      directedGraph = new DirectedGraph<>();
      fileName = fileTextField.getText();

      try {
        if (fileName.isEmpty()) {
          throw new NullPointerException();
        }

        directedGraph.buildGraph(directedGraph.tokenize(fileName));
        JOptionPane.showMessageDialog(
            null,
            "Graph Build Successfully",
            "Message",
            JOptionPane.INFORMATION_MESSAGE
        );

        fileTextField.setEditable(false);
        graphButton.setEnabled(false);

      } catch (NullPointerException npe) {
        JOptionPane.showMessageDialog(
            null,
            "A file must be specified",
            "Error",
            JOptionPane.ERROR_MESSAGE
        );
      } catch (IOException ex) {
        JOptionPane.showMessageDialog(
            null,
            "File did not open",
            "Error",
            JOptionPane.ERROR_MESSAGE
        );
        fileTextField.setText("");
      }
    });

    // order button listener
    topologicalButton.addActionListener((
        ActionEvent e) ->

    {
      className = classTextField.getText();

      try {
        outputText.setText(directedGraph.orderGenerator(className));

      } catch (InvalidClassNameException ex) {
        JOptionPane.showMessageDialog(
            null,
            "Invalid Class Name: " + className,
            "Error",
            JOptionPane.ERROR_MESSAGE
        );
      } catch (ContainsCycleException ex) {
        JOptionPane.showMessageDialog(
            null,
            "Graph contains a cycle",
            "Message",
            JOptionPane.INFORMATION_MESSAGE
        );
      } catch (NullPointerException npe) {
          JOptionPane.showMessageDialog(
              null,
              "Build Graph First",
              "Message",
              JOptionPane.INFORMATION_MESSAGE
          );
          classTextField.setText("");
      }
    });

    // clear text button listener
    clearTextButton.addActionListener((
        ActionEvent e) ->

    {
      classTextField.setText("");
      fileTextField.setText("");
      outputText.setText("");
      fileTextField.setEditable(true);
      graphButton.setEnabled(true);
    });
  }

  public static void main(String[] args) {
    new Main();
  }
}
