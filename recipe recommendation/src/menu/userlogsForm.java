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

    private static final String JDBC_URL = "jdbc:mysql:/localhost:3306/crispin_shyaka_222004852";
    private static final String USERNAME = "shyaka";
    private static final String PASSWORD = "222004852";

    private int userId;  // Assume you know the user ID
    private int recipeId;  // Assume you know the recipe ID

    public userlogsForm(int userId, int recipeId) {
        setTitle("user log form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.userId = userId;
        this.recipeId = recipeId;

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
                String insertQuery = "INSERT INTO recipe_logs (recipe_id, user_id, log_text) VALUES (?, ?, ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                    preparedStatement.setInt(1, recipeId);
                    preparedStatement.setInt(2, userId);
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
        	int userId = 1;  
            int recipeId = 1;
             
            new userlogsForm(userId,recipeId);
        });
    }
}
