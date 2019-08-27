package com.jcaseydev;

import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Main extends JFrame {

    private String equation;
    private String result;

    private Main() {
        super("Infix Expression Evaluator");
        JPanel main = new JPanel();
        JPanel inputPanel = new JPanel();
        JPanel evalPanel = new JPanel();
        JPanel resultPanel = new JPanel();

        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));

        JLabel inputLabel = new JLabel("Enter infix expression:");
        JTextField inputText = new JTextField(null, 20);
        JButton evaluateButton = new JButton("Evaluate");
        JLabel resultLabel = new JLabel("Result");
        JTextField resultText = new JTextField(null, 20);

        inputPanel.add(inputLabel);
        inputPanel.add(inputText);

        evalPanel.add(evaluateButton);

        resultPanel.add(resultLabel);
        resultPanel.add(resultText);
        resultText.setEditable(false);

        main.add(inputPanel);
        main.add(evalPanel);
        main.add(resultPanel);

        add(main);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 150);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        evaluateButton.addActionListener((ActionEvent e) -> {
            Infix evaluate = new Infix();
            equation = inputText.getText();

            result = (evaluate.evaluate(equation));
            resultText.setText(result);
        });
    }

    public static void main(String[] args) {
        new Main();
    }
}
