package OtherForms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class user_profile extends JFrame implements ActionListener {
    private JLabel titleLabel, nameLabel, emailLabel, passwordLabel;
    private JTextField nameTextField, emailTextField;
    private JPasswordField passwordField;
    private JButton updateButton;

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/crispin_shyaka_222004852";
    private static final String USERNAME = "shyaka";
    private static final String PASSWORD = "222004852";

    private String userName; // Assume you have the user's name

    public user_profile(String userName) {
    	this.userName = JOptionPane.showInputDialog("Enter your username:");

        setTitle("User Profile Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        titleLabel = new JLabel("User Profile Form");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setBounds(20, 20, 200, 30);

        nameLabel = new JLabel("Name:");
        nameLabel.setBounds(20, 60, 80, 30);

        nameTextField = new JTextField();
        nameTextField.setBounds(120, 60, 200, 30);
        nameTextField.setEditable(true); // User can't edit their name

        emailLabel = new JLabel("Email:");
        emailLabel.setBounds(20, 100, 80, 30);

        emailTextField = new JTextField();
        emailTextField.setBounds(120, 100, 200, 30);

        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(20, 140, 80, 30);

        passwordField = new JPasswordField();
        passwordField.setBounds(120, 140, 200, 30);

        updateButton = new JButton("Update Profile");
        updateButton.setBounds(20, 180, 150, 30);
        updateButton.addActionListener(this);

        setLayout(null);
        add(titleLabel);
        add(nameLabel);
        add(nameTextField);
        add(emailLabel);
        add(emailTextField);
        add(passwordLabel);
        add(passwordField);
        add(updateButton);

       /* loadUserProfile(); // Load the user's profile information*/

        setSize(400, 250);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }

    /*private void loadUserProfile() {
        try {
            try (Connection connection = getConnection()) {
                String selectQuery = "SELECT first_name, emal FROM users WHERE first_name = ?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                    preparedStatement.setString(1, userName);

                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        if (resultSet.next()) {
                            nameTextField.setText(resultSet.getString("first_name"));
                            emailTextField.setText(resultSet.getString("emal"));
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading user profile.");
        }
    }*/

    private void updateProfile(String userEmai, String userPassword) {
        try {
            try (Connection connection = getConnection()) {
                String updateQuery = "UPDATE users SET emal = ?, paword = ? WHERE first_name = ?";
                System.out.println("Generated SQL: " + updateQuery);
                try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                    preparedStatement.setString(1, userEmai);
                    preparedStatement.setString(2, userPassword);
                    preparedStatement.setString(3, userName);

                    preparedStatement.executeUpdate();
                    System.out.println("Rows updated: " + updateQuery);
                    JOptionPane.showMessageDialog(this, "Profile updated successfully!");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error updating profile.");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == updateButton) {
            String userEmail = emailTextField.getText();
            String userName = nameTextField.getText();
            char[] passwordChars = passwordField.getPassword();
            String userPassword = new String(passwordChars); // Convert char array to string
            updateProfile(userEmail, userPassword);
        }
    }

   
	public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new user_profile("")); // Pass the actual username
    }
}
