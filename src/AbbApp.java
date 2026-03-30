import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class AbbApp extends JFrame {
    private Connection con;
    private JTextField inputField, resultField;

    public AbbApp() {
        setTitle("Abbreviation Application");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Database connection
        try {
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "15-06-2005");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to connect to database.");
            System.exit(1);
        }

        JPanel mainPanel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                setBackground(new Color(100, 100, 100)); // Light blue background
            }
        };

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10); // Padding

        JLabel inputLabel = new JLabel("Enter Abbreviation or Full Form:");
        mainPanel.add(inputLabel, gbc);

        gbc.gridy++;
        inputField = new JTextField(20);
        inputField.setHorizontalAlignment(JTextField.CENTER);
        mainPanel.add(inputField, gbc);

        gbc.gridy++;
        JButton getAbbreviationButton = new JButton("Get Abbreviation");
        mainPanel.add(getAbbreviationButton, gbc);

        gbc.gridy++;
        JButton getFullFormButton = new JButton("Get Full Form");
        mainPanel.add(getFullFormButton, gbc);

        gbc.gridy++;
        resultField = new JTextField(20);
        resultField.setEditable(false);
        resultField.setHorizontalAlignment(JTextField.CENTER);
        mainPanel.add(resultField, gbc);

        add(mainPanel);

        getAbbreviationButton.addActionListener(e -> {
            String input = inputField.getText().trim();
            if (!input.isEmpty()) {
                String result = getAbbreviation(input);
                resultField.setText(result);
            } else {
                resultField.setText("Please enter a value.");
            }
        });

        getFullFormButton.addActionListener(e -> {
            String input = inputField.getText().trim();
            if (!input.isEmpty()) {
                String result = getFullForm(input);
                resultField.setText(result);
            } else {
                resultField.setText("Please enter a value.");
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private String getAbbreviation(String input) {
        String result = "Not found";
        try {
            PreparedStatement stmt = con.prepareStatement("SELECT abbreviation FROM abbreviations1 WHERE full_form = ?");
            stmt.setString(1, input);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                result =  rs.getString("abbreviation");
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to query database.");
        }
        return result;
    }

    private String getFullForm(String input) {
        String result = "Not found";
        try {
            PreparedStatement stmt = con.prepareStatement("SELECT full_form FROM abbreviations1 WHERE abbreviation = ?");
            stmt.setString(1, input);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                result = rs.getString("full_form");
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to query database.");
        }
        return result;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AbbApp::new);
    }
}
