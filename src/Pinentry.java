

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Pinentry extends JFrame implements ActionListener {
    private JTextField pinField;
    private JLabel statusLabel;
    private String pin = "123";
    private String enteredPin = "";

    public Pinentry() {
        super("PIN Entry");
        setSize(350, 330);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        pinField = new JTextField();
        pinField.setEditable(false);
        pinField.setHorizontalAlignment(JTextField.CENTER);
        add(pinField, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(5, 2));
        add(buttonPanel, BorderLayout.CENTER);

        for (int i = 1; i <= 9; i++) {
            addButton(buttonPanel, String.valueOf(i));
        }
        addButton(buttonPanel, "0");
        addButton(buttonPanel, "CLEAR");
        addButton(buttonPanel, "EXIT");

        statusLabel = new JLabel();
        statusLabel.setHorizontalAlignment(JLabel.CENTER);
        add(statusLabel, BorderLayout.SOUTH);
    }

    private void addButton(Container container, String text) {
        JButton button = new JButton(text);
        button.addActionListener(this);
        container.add(button);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "CLEAR":
                enteredPin = "";
                pinField.setText("");
                break;
            case "EXIT":
                System.exit(0);
                break;
            default:
                enteredPin += command;
                pinField.setText(pinField.getText() + "*");
                if (enteredPin.length() == pin.length()) {
                    if (enteredPin.equals(pin)) {
                        statusLabel.setText("OPEN");
                    } else {
                        statusLabel.setText("WRONG PIN");
                    }
                    enteredPin = "";
                    pinField.setText("");
                }
                break;
        }
    }

    public static void main(String[] args) {
        Pinentry pinEntry = new Pinentry();
        pinEntry.setVisible(true);
    }
}