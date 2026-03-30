
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame implements ActionListener {
    private JTextField num1Field, num2Field, resultField;
    private double num1, num2, result;

    public Calculator() {
        setTitle("Calculator Project");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 1, 5, 5));

        JPanel num1Panel = new JPanel(new BorderLayout());
        num1Panel.add(new JLabel("First Number"), BorderLayout.WEST);
        num1Field = new JTextField(5);
        num1Panel.add(num1Field, BorderLayout.CENTER);
        add(num1Panel);

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);

        JButton addButton = new JButton("ADD");
        addButton.addActionListener(this);
        buttonPanel.add(addButton, gbc);

        gbc.gridx++;
        JButton subButton = new JButton("SUB");
        subButton.addActionListener(this);
        buttonPanel.add(subButton, gbc);

        gbc.gridx++;
        JButton mulButton = new JButton("MUL");
        mulButton.addActionListener(this);
        buttonPanel.add(mulButton, gbc);

        gbc.gridx++;
        JButton divButton = new JButton("DIV");
        divButton.addActionListener(this);
        buttonPanel.add(divButton, gbc);

        add(buttonPanel);

        JPanel num2Panel = new JPanel(new BorderLayout());
        num2Panel.add(new JLabel("Second Number"), BorderLayout.WEST);
        num2Field = new JTextField(5);
        num2Panel.add(num2Field, BorderLayout.CENTER);
        add(num2Panel);

        JPanel resultPanel = new JPanel(new BorderLayout());
        resultPanel.add(new JLabel("Result"), BorderLayout.WEST);
        resultField = new JTextField(5);
        resultField.setEditable(false);
        resultPanel.add(resultField, BorderLayout.CENTER);
        add(resultPanel);

        JLabel label = new JLabel("Calculator Project", SwingConstants.CENTER);
    

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Calculator();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        try {
            num1 = Double.parseDouble(num1Field.getText());
            num2 = Double.parseDouble(num2Field.getText());

            switch (command) {
                case "ADD":
                    result = num1 + num2;
                    break;
                case "SUB":
                    result = num1 - num2;
                    break;
                case "MUL":
                    result = num1 * num2;
                    break;
                case "DIV":
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {
                        resultField.setText("Cannot divide by zero");
                        return;
                    }
                    break;
            }
            resultField.setText(String.valueOf(result));
        } catch (NumberFormatException ex) {
            resultField.setText("Invalid input");
        }
    }
}