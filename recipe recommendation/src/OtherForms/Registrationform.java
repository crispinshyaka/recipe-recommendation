package OtherForms;

import javax.swing.*;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Registrationform extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField usernameField;
    private JPasswordField passwordField;

    public Registrationform() {
    	
        setTitle("User Registration");
        setBounds(600,140,500,350);
        getContentPane().setBackground(Color.gray);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(null);
        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");

        usernameLabel.setBounds(50, 50, 80, 30);
        passwordLabel.setBounds(50, 100, 80, 30);

        usernameField = new JTextField();
        passwordField = new JPasswordField();

        usernameField.setBounds(140, 50, 250, 30);
        passwordField.setBounds(140, 100, 250, 30);

        JButton insertButton = new JButton("register");
        JButton resetButton = new JButton("Reset");

        insertButton.setBounds(100, 180, 80, 30);
        resetButton.setBounds(200, 180, 80, 30);
        
        insertButton.addActionListener(new ActionListener() {
         
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }
        });

        resetButton.addActionListener(new ActionListener() {
        
            public void actionPerformed(ActionEvent e) {
                usernameField.setText("");
                passwordField.setText("");
            }
        });

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(insertButton);
        panel.add( resetButton);

        getContentPane().add(panel);
    }
	
	

	private void registerUser() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        try {
            Connection connection = database_connection.getConnection();
            String query = "INSERT INTO User (Username, Password) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(this, "User registered successfully.");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Registration failed.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Registrationform().setVisible(true);
               
            }
        });
    }
}
