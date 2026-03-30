import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class LoginModule extends JFrame {
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private JTextField newUsernameField; // Added field for new username
    private JPasswordField newPasswordField; // Added field for new password
    private Connection con;

    public LoginModule() {
        setTitle("Login Form");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Database connection
        try {
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "15-06-2005");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to connect to database.");
            System.exit(1);
        }

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Login Page
        JPanel loginPanel = new JPanel(new BorderLayout());
        JLabel loginLabel = new JLabel("Login Form");
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loginPanel.add(loginLabel, BorderLayout.NORTH);
        JPanel loginFormPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        loginFormPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JTextField usernameField = new JTextField();
        loginFormPanel.add(new JLabel("Username:"));
        loginFormPanel.add(usernameField);
        JPasswordField passwordField = new JPasswordField();
        loginFormPanel.add(new JLabel("Password:"));
        loginFormPanel.add(passwordField);
        JButton signInButton = new JButton("Sign In");
        signInButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            if (isValidUser(username, password)) {
                JOptionPane.showMessageDialog(this, "Sign In successful.");
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password.");
            }
        });
        loginFormPanel.add(signInButton);
        JButton signUpSwitchButton = new JButton("Sign Up");
        signUpSwitchButton.addActionListener(e -> cardLayout.show(cardPanel, "signup"));
        loginFormPanel.add(signUpSwitchButton);
        loginPanel.add(loginFormPanel, BorderLayout.CENTER);
        cardPanel.add(loginPanel, "login");

        // Sign Up Page
        JPanel signUpPanel = new JPanel(new BorderLayout());
        JLabel signUpLabel = new JLabel("Sign Up Form");
        signUpLabel.setHorizontalAlignment(SwingConstants.CENTER);
        signUpPanel.add(signUpLabel, BorderLayout.NORTH);
        JPanel signUpFormPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        signUpFormPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        newUsernameField = new JTextField(); // Initializing newUsernameField
        signUpFormPanel.add(new JLabel("New Username:"));
        signUpFormPanel.add(newUsernameField);
        newPasswordField = new JPasswordField(); // Initializing newPasswordField
        signUpFormPanel.add(new JLabel("New Password:"));
        signUpFormPanel.add(newPasswordField);
        JButton signUpButton = new JButton("Sign Up");
        signUpButton.addActionListener(e -> {
            String newUsername = newUsernameField.getText();
            String newPassword = new String(newPasswordField.getPassword());
            if (newUsername.isEmpty() || newPassword.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter both username and password.");
            } else {
                try {
                    PreparedStatement insertStmt = con.prepareStatement("INSERT INTO admin (name, pass) VALUES (?, ?)");
                    insertStmt.setString(1, newUsername);
                    insertStmt.setString(2, newPassword);
                    int rowsAffected = insertStmt.executeUpdate();
                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(this, "User signed up successfully.");
                        cardLayout.show(cardPanel, "login");
                    } else {
                        JOptionPane.showMessageDialog(this, "Failed to sign up user.");
                    }
                    insertStmt.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error signing up user.");
                }
            }
        });
        signUpFormPanel.add(signUpButton);
        signUpPanel.add(signUpFormPanel, BorderLayout.CENTER);
        cardPanel.add(signUpPanel, "signup");

        add(cardPanel);

        cardLayout.show(cardPanel, "login");

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private boolean isValidUser(String username, String password) {
        try {
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM admin WHERE name = ? AND pass = ?");
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            return rs.next(); // Return true if a row is found (valid user), false otherwise
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error validating user.");
        }
        return false;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginModule::new);
    }
}
