package CRUDForms;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ingredientsForm extends JFrame implements ActionListener {
    private JLabel titleLabel, nameLabel, categoryLabel;
    private JTextField nameTextField, categoryTextField;
    private JButton saveButton, updateButton, deleteButton, viewAllButton;

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/crispin_shyaka_222004852";
    private static final String USERNAME = "shyaka";
    private static final String PASSWORD = "222004852";

    public ingredientsForm() {
        setTitle("Ingredient Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        titleLabel = new JLabel("Ingredient Form");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setBounds(20, 20, 200, 30);

        nameLabel = new JLabel("Name:");
        nameLabel.setBounds(20, 60, 80, 30);

        nameTextField = new JTextField();
        nameTextField.setBounds(120, 60, 200, 30);

        categoryLabel = new JLabel("Quantity:");
        categoryLabel.setBounds(20, 100, 80, 30);

        categoryTextField = new JTextField();
        categoryTextField.setBounds(120, 100, 200, 30);

        saveButton = new JButton("Save");
        saveButton.setBounds(20, 150, 80, 30);
        saveButton.addActionListener(this);

        updateButton = new JButton("Update");
        updateButton.setBounds(120, 150, 80, 30);
        updateButton.addActionListener(this);

        deleteButton = new JButton("Delete");
        deleteButton.setBounds(220, 150, 80, 30);
        deleteButton.addActionListener(this);

        viewAllButton = new JButton("View All");
        viewAllButton.setBounds(320, 150, 80, 30);
        viewAllButton.addActionListener(this);

        setLayout(null);
        add(titleLabel);
        add(nameLabel);
        add(nameTextField);
        add(categoryLabel);
        add(categoryTextField);
        add(saveButton);
        add(updateButton);
        add(deleteButton);
        add(viewAllButton);

        setSize(450, 250);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }

    private void saveIngredient() {
        String name = nameTextField.getText();
        String category = categoryTextField.getText();

        try {
            try (Connection connection = getConnection()) {
                String insertQuery = "INSERT INTO ingredients (name, category) VALUES (?, ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                    preparedStatement.setString(1, name);
                    preparedStatement.setString(2, category);

                    preparedStatement.executeUpdate();
                    JOptionPane.showMessageDialog(this, "Ingredient saved successfully!");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving ingredient.");
        }
    }

    private void updateIngredient() {
        // Implement the logic to update an ingredient
    }

    private void deleteIngredient() {
        // Implement the logic to delete an ingredient
    }

    private void viewAllIngredients() {
        List<String> ingredients = getAllIngredients();
        StringBuilder ingredientList = new StringBuilder();
        for (String ingredient : ingredients) {
            ingredientList.append(ingredient).append("\n\n");
        }
        JOptionPane.showMessageDialog(this, ingredientList.toString(), "All Ingredients", JOptionPane.INFORMATION_MESSAGE);
    }

    private List<String> getAllIngredients() {
        List<String> ingredients = new ArrayList<>();

        try {
            try (Connection connection = getConnection()) {
                String selectQuery = "SELECT name, category FROM ingredients";
                try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        while (resultSet.next()) {
                            String name = resultSet.getString("name");
                            String category = resultSet.getString("category");

                            ingredients.add("Name: " + name + "\ncategory: " + category);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching ingredients.");
        }

        return ingredients;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveButton) {
            saveIngredient();
        } else if (e.getSource() == updateButton) {
            updateIngredient();
        } else if (e.getSource() == deleteButton) {
            deleteIngredient();
        } else if (e.getSource() == viewAllButton) {
            viewAllIngredients();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ingredientsForm::new);
    }
}
