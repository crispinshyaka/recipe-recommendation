package menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class userlogsForm extends JFrame implements ActionListener {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea logTextArea;
    private JButton saveLogButton;

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/crispin_shyaka_222004852";
    private static final String USERNAME = "shyaka";
    private static final String PASSWORD = "222004852";

    private int log_Id;  // Assume you know the user ID
    private int recipe_Id;  // Assume you know the recipe ID

    public userlogsForm(int userId, int recipeId) {
        setTitle("user log form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.log_Id = log_Id;
        this.recipe_Id = recipe_Id;

        logTextArea = new JTextArea();
        logTextArea.setLineWrap(true);
        logTextArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(logTextArea);
        scrollPane.setBounds(20, 20, 400, 200);

        saveLogButton = new JButton("Save Log");
        saveLogButton.setBounds(20, 240, 100, 30);
        saveLogButton.addActionListener(this);

        setLayout(null);
        add(scrollPane);
        add(saveLogButton);

        setSize(450, 320);
        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }

    private void saveLog() {
        String logText = logTextArea.getText();

        try {
            try (Connection connection = getConnection()) {
                String insertQuery = "INSERT INTO userlog (log_id, user_id, action) VALUES (?, ?, ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                    preparedStatement.setInt(1, log_Id);
                    preparedStatement.setInt(2, recipe_Id);
                    preparedStatement.setString(3, logText);

                    preparedStatement.executeUpdate();
                }
            }

            JOptionPane.showMessageDialog(this, "Log saved successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving log.");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveLogButton) {
            saveLog();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
        	int log_Id = 1;  
            int recipe_Id = 1;
             
            new userlogsForm(log_Id,recipe_Id);
        });
    }
}
