package OtherForms;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public   class Registrationform extends JFrame implements ActionListener {
    private JLabel titleLabel, usernameLabel, namesLabel, emailLabel, passwordLabel;
    private JTextField usernameTextField, namesTextField, emailTextField;
    private JPasswordField passwordField;
    private JButton registerButton, resetButton, goToLoginButton;

    private static final String JDBC_URL ="jdbc:mysql://localhost:3306/crispin_shyaka_222004852";
    private static final String USERNAME = "shyaka";
    private static final String PASSWORD = "222004852";

    public Registrationform() {
        setTitle("Registration Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        titleLabel = new JLabel("User Registration");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setBounds(100, 20, 200, 30);

        usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(20, 60, 80, 30);

        usernameTextField = new JTextField();
        usernameTextField.setBounds(120, 60, 200, 30);
        
        namesLabel = new JLabel("names:");
        namesLabel.setBounds(20, 100, 80, 30);

        namesTextField = new JTextField();
        namesTextField.setBounds(120, 100, 200, 30);
        
        emailLabel = new JLabel("Email:");
        emailLabel.setBounds(20, 140, 80, 30);

        emailTextField = new JTextField();
        emailTextField.setBounds(120, 140, 200, 30);

        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(20, 180, 80, 30);

        passwordField = new JPasswordField();
        passwordField.setBounds(120, 180, 200, 30);

        registerButton = new JButton("Register");
        registerButton.setBounds(20, 220, 100, 30);
        registerButton.addActionListener(this);

        resetButton = new JButton("Reset");
        resetButton.setBounds(130, 220, 80, 30);
        resetButton.addActionListener(this);

        goToLoginButton = new JButton("Go to Login");
        goToLoginButton.setBounds(220, 220, 120, 30);
        goToLoginButton.addActionListener(this);

        setLayout(null);
        add(titleLabel);
        add(usernameLabel);
        add(usernameTextField);
        add(namesLabel);
        add(namesTextField);
        add(emailLabel);
        add(emailTextField);
        add(passwordLabel);
        add(passwordField);
        add(registerButton);
        add(resetButton);
        add(goToLoginButton);

        setSize(400, 300);
        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }

    private void registerUser() {
        String username = usernameTextField.getText();
        String names = namesTextField.getText();
        String email = emailTextField.getText();
        String password = new String(passwordField.getPassword());

        try {
            try (Connection connection = getConnection()) {
                String insertQuery ="INSERT INTO users (first_name, last_name, emal, paword) VALUES (?, ?, ?, ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                    preparedStatement.setString(1, username);
                    
					preparedStatement.setString(2, names);
                    preparedStatement.setString(3, email);
                    preparedStatement.setString(4, password);
                    
                    preparedStatement.executeUpdate();
                }
            }

            JOptionPane.showMessageDialog(this, "Registration successful!");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error registering user.");
        }
    }
    private void registerUser1() {
        // Replace this with your actual logic to register the user
        String username = usernameTextField.getText();
        String email = emailTextField.getText();
        String password = new String(passwordField.getPassword());

        // Simulated registration (you would typically hash the password)
        System.out.println("User registered: " + username + ", " + email);
        JOptionPane.showMessageDialog(this, "Registration successful!");
    }

    private void resetForm() {
        // Reset the form fields
        usernameTextField.setText("");
        namesTextField.setText("");
        emailTextField.setText("");
        passwordField.setText("");
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == registerButton) {
            registerUser();
        } else if (e.getSource() == resetButton) {
            resetForm();
        } else if (e.getSource() == goToLoginButton) {
            // Close the registration form and open the login form
            dispose(); // Close the registration form
            new loggedin_Form(""); // Open the login form
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Registrationform());
    }

	
	

	
}
