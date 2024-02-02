package OtherForms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class loggedin_Form extends JFrame {
   
	
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	private String loggedInUsername;
	private String insertingredient;
	

    public loggedin_Form() {
        this.loggedInUsername = loggedInUsername;
		
		this.insertingredient = insertingredient;

        setTitle("Logged In Form");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(null); 

        JLabel welcomeLabel = new JLabel("Welcome, " + loggedInUsername + "!");
        JLabel usernameLabel = new JLabel("Username:");
        JLabel insertingredientLabel = new JLabel("ingredients:");

        JTextField usernameField = new JTextField(loggedInUsername);
        usernameField.setEditable(false);
        JTextField insertingredientField = new JTextField(insertingredient);
        insertingredientField.setEditable(true);

        JButton logoutButton = new JButton("Logout");
        JButton viewrecipeButton = new JButton("View recipe");

        welcomeLabel.setBounds(50, 30, 300, 30);
        usernameLabel.setBounds(50, 80, 80, 30);
        insertingredientLabel.setBounds(50, 130, 130, 30);

        usernameField.setBounds(140, 80, 200, 30);
        insertingredientField.setBounds(140, 120, 200, 30);

        viewrecipeButton.setBounds(50, 180, 150, 30);
        

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                dispose();
                new user_loginForm().actionPerformed(e); 
            }
        });

        viewrecipeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	 dispose();
                 new recommendations().actionPerformed(e);
               
           
                JOptionPane.showMessageDialog(loggedin_Form.this, "Viewing recipe for " + loggedInUsername);
            }
        });

        panel.add(welcomeLabel);
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(insertingredientField);
        panel.add(viewrecipeButton);
        panel.add(logoutButton);

        getContentPane().add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new loggedin_Form().setVisible(true);
            }
        });
    }
}
