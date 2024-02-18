package CRUDForms;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecommendedRecipesForm extends JFrame {
    private JTextArea recipesTextArea;

    public RecommendedRecipesForm(String userName, String ingredients) {
        setTitle("Recommended Recipes");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Dispose this form on close

        JLabel titleLabel = new JLabel("Recommended Recipes for " + userName);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setBounds(20, 20, 400, 30);

        recipesTextArea = new JTextArea();
        recipesTextArea.setLineWrap(true);
        recipesTextArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(recipesTextArea);
        scrollPane.setBounds(20, 60, 400, 100);

        // Query the database for recommended recipes
        List<String> recommendedRecipes = recommendRecipesFromDatabase(ingredients);
        for (String recipe : recommendedRecipes) {
            recipesTextArea.append(recipe + "\n");
        }

        setLayout(null);
        add(titleLabel);
        add(scrollPane);

        setSize(450, 200);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private List<String> recommendRecipesFromDatabase(String ingredients) {
        List<String> recommendedRecipes = new ArrayList<>();

        // Replace these with your actual database connection details
        String jdbcUrl = "jdbc:mysql://localhost:3306/crispin_shyaka_222004852";
        String username = "shyaka";
        String password = "222004852";

        try {
            try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
                String query = "SELECT title FROM recipes WHERE ingredient_lst LIKE ?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setString(1, "%" + ingredients + "%");

                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        while (resultSet.next()) {
                            recommendedRecipes.add(resultSet.getString("title"));
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RecommendedRecipesForm("", ""));
    }
}
