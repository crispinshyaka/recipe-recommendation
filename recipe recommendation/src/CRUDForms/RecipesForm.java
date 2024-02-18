package CRUDForms;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecipesForm extends JFrame {
    private JTextField nameField, ingredientsField, instructionsField;
    private JTable recipeTable;
    private RecipeTableModel recipeTableModel;

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/crispin_shyaka_222004852";
    private static final String USERNAME = "shyaka";
    private static final String PASSWORD = "222004852";

    public RecipesForm() {
        setTitle("Recipe Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize the components
        nameField = new JTextField();
        ingredientsField = new JTextField();
        instructionsField = new JTextField();

        // Fetch data from the database and initialize the table model
        List<Recipe> Recipes = fetchRecipesFromDatabase();
        recipeTableModel = new RecipeTableModel(Recipes);

        // Initialize the table with the table model
        recipeTable = new JTable(recipeTableModel);

        // Initialize buttons for CRUD operations
        JButton addButton = new JButton("Add Recipe");
        JButton updateButton = new JButton("Update Recipe");
        JButton deleteButton = new JButton("Delete Recipe");

        // Add action listeners to the buttons
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addRecipe();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateRecipe();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteRecipe();
            }
        });

        // Set up the layout
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Ingredients:"));
        inputPanel.add(ingredientsField);
        inputPanel.add(new JLabel("Instructions:"));
        inputPanel.add(instructionsField);
        inputPanel.add(addButton);
        inputPanel.add(updateButton);

        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(recipeTable), BorderLayout.CENTER);
        add(deleteButton, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }

    private void addRecipe() {
        String name = nameField.getText();
        String ingredients = ingredientsField.getText();
        String instructions = instructionsField.getText();

        try {
            try (Connection connection = getConnection()) {
                String insertQuery = "INSERT INTO recipes (title, ingredient_lst, instructions) VALUES (?, ?, ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                    preparedStatement.setString(1, name);
                    preparedStatement.setString(2, ingredients);
                    preparedStatement.setString(3, instructions);

                    preparedStatement.executeUpdate();
                }
            }

            // Update the table model with the new data from the database
            updateTableModel();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error adding recipe to the database.");
        }
    }

    private void updateRecipe() {
        int selectedRow = recipeTable.getSelectedRow();
        if (selectedRow != -1) {
            String name = nameField.getText();
            String ingredients = ingredientsField.getText();
            String instructions = instructionsField.getText();

            try {
                try (Connection connection = getConnection()) {
                    String updateQuery = "UPDATE recipes SET title=?, ingredient_lst=?, instructions=? WHERE recipe_id=?";
                    try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                        preparedStatement.setString(1, name);
                        preparedStatement.setString(2, ingredients);
                        preparedStatement.setString(3, instructions);
                        preparedStatement.setInt(4, (int) recipeTableModel.getValueAt(selectedRow, 0)); // Assuming id is in column 0

                        preparedStatement.executeUpdate();
                    }
                }

                // Update the table model with the new data from the database
                updateTableModel();
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error updating recipe in the database.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Select a recipe to update.");
        }
    }

    private void deleteRecipe() {
        int selectedRow = recipeTable.getSelectedRow();
        if (selectedRow != -1) {
            int recipeId = (int) recipeTableModel.getValueAt(selectedRow, 0); // Assuming id is in column 0

            try {
                try (Connection connection = getConnection()) {
                    String deleteQuery = "DELETE FROM recipes WHERE recipe_id=?";
                    try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
                        preparedStatement.setInt(1, recipeId);

                        preparedStatement.executeUpdate();
                    }
                }

                // Update the table model with the new data from the database
                updateTableModel();
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error deleting recipe from the database.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Select a recipe to delete.");
        }
    }

    private void updateTableModel() {
        // Fetch the updated data from the database
        List<Recipe> updatedRecipes = fetchRecipesFromDatabase();

        // Update the table model with the new data
        recipeTableModel.updateData(updatedRecipes);
    }

    private List<Recipe> fetchRecipesFromDatabase() {
        List<Recipe> recipes = new ArrayList<>();

        try {
            try (Connection connection = getConnection()) {
                String selectQuery = "SELECT recipe_id, title, ingredient_lst, instructions FROM recipes";
                try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        while (resultSet.next()) {
                            int id = resultSet.getInt("recipe_id");
                            String name = resultSet.getString("title");
                            String ingredients = resultSet.getString("ingredient_lst");
                            String instructions = resultSet.getString("instructions");

                            recipes.add(new Recipe(id, name, ingredients, instructions));
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching recipes from the database.");
        }

        return recipes;
    }

    public static void main(String[] args) {
        // Example usage
        SwingUtilities.invokeLater(RecipesForm::new);
    }
}

class RecipeTableModel extends AbstractTableModel {
    private List<Recipe> recipes;
    private String[] columnNames = {"ID", "Name", "Ingredients", "Instructions"};

    public RecipeTableModel(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    @Override
    public int getRowCount() {
        return recipes.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Recipe recipe = recipes.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return recipe.getId();
            case 1:
                return recipe.getName();
            case 2:
                return recipe.getIngredients();
            case 3:
                return recipe.getInstructions();
            default:
                return null;
        }
    }

    public void updateData(List<Recipe> recipes) {
        this.recipes = recipes;
        fireTableDataChanged();
    }
}

class Recipe {
    private int recipe_id;
    private String title;
    private String ingredient_lst;
    private String instructions;

    public Recipe(int id, String name, String ingredients, String instructions) {
        this.recipe_id = id;
        this.title = name;
        this.ingredient_lst = ingredients;
        this.instructions = instructions;
    }

    public int getId() {
        return recipe_id;
    }

    public String getName() {
        return title;
    }

    public String getIngredients() {
        return ingredient_lst;
    }

    public String getInstructions() {
        return instructions;
    }
}
