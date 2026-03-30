

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Buttonclick extends JFrame {
    private int clickCount = 0;
    private JLabel countLabel;

    public Buttonclick() {
        setTitle("Swing Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLayout(new FlowLayout());

        JButton button1 = new JButton("I'm a swing button!");
        JButton button2 = new JButton("I'm a swing button too!");

        countLabel = new JLabel("Number of button clicks: 0");

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clickCount++;
                countLabel.setText("Number of button clicks: " + clickCount);
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clickCount++;
                countLabel.setText("Number of button clicks: " + clickCount);
            }
        });

        add(button1);
        add(button2);
        add(countLabel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
             new Buttonclick();
            new Buttonclick().setVisible(true);
        });
    }
}