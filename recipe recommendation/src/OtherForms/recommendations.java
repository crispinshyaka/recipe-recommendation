package OtherForms;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class recommendations extends JFrame implements ActionListener {
    private JLabel titleLabel, ingredientsLabel;
    private JTextArea ingredientsTextArea;
    private JButton recommendButton;

    private static final String JDBC_URL = "jdbc:mysql://your_database_url";
    private static final String USERNAME = "your_username";
    private static final String PASSWORD = "your_password";

    public recommendations() {
        setTitle("Recommendations Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        titleLabel = new JLabel("Recommendations Form");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setBounds(20, 20, 300, 30);

        ingredientsLabel = new JLabel("Your Ingredients:");
        ingredientsLabel.setBounds(20, 60, 150, 30);

        ingredientsTextArea = new JTextArea();
        ingredientsTextArea.setLineWrap(true);
        ingredientsTextArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(ingredientsTextArea);
        scrollPane.setBounds(180, 60, 200, 80);

        recommendButton = new JButton("Recommend Recipes");
        recommendButton.setBounds(20, 160, 150, 30);
        recommendButton.addActionListener(this);

        setLayout(null);
        add(titleLabel);
        add(ingredientsLabel);
        add(scrollPane);
        add(recommendButton);

        setSize(400, 250);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }

    private List<String> recommendRecipes(String ingredients) {
        List<String> recommendedRecipes = new ArrayList<>();

        try {
            try (Connection connection = getConnection()) {
                // Adjust this query based on your recommendation logic
                String query = "SELECT name FROM recipes WHERE ingredients LIKE ?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setString(1, "%" + ingredients + "%");

                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        while (resultSet.next()) {
                            recommendedRecipes.add(resultSet.getString("name"));
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching recommended recipes.");
        }

        return recommendedRecipes;
    }

    private void displayRecommendations(List<String> recommendations) {
        StringBuilder recommendedList = new StringBuilder("Recommended Recipes:\n\n");
        for (String recipe : recommendations) {
            recommendedList.append(recipe).append("\n");
        }
        JOptionPane.showMessageDialog(this, recommendedList.toString(), "Recommendations", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == recommendButton) {
            String userIngredients = ingredientsTextArea.getText();
            List<String> recommendations = recommendRecipes(userIngredients);
            displayRecommendations(recommendations);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(recommendations::new);
    }
}



