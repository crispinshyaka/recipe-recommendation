package CRUDForms;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class recipesForm extends JFrame implements ActionListener {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable recipeTable;
    private DefaultTableModel tableModel;
    private JButton addButton, updateButton, deleteButton, refreshButton;

    private static final String JDBC_URL = "jdbc:mysql://localhost/crispin_shyaka_222004852";
    private static final String USERNAME = "shyaka";
    private static final String PASSWORD = "222004852";

    public recipesForm() {
        setTitle("Recipe Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Table
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Recipe ID");
        tableModel.addColumn("title");
        tableModel.addColumn("Ingredient_lst");
        tableModel.addColumn("Instructions");

        recipeTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(recipeTable);
        scrollPane.setBounds(20, 20, 500, 200);

        // Buttons
        addButton = new JButton("Add");
        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");
        refreshButton = new JButton("Refresh");

        addButton.setBounds(20, 240, 100, 30);
        updateButton.setBounds(130, 240, 100, 30);
        deleteButton.setBounds(240, 240, 100, 30);
        refreshButton.setBounds(350, 240, 100, 30);

        addButton.addActionListener(this);
        updateButton.addActionListener(this);
        deleteButton.addActionListener(this);
        refreshButton.addActionListener(this);

        // Layout
        setLayout(null);
        add(scrollPane);
        add(addButton);
        add(updateButton);
        add(deleteButton);
        add(refreshButton);

        setSize(550, 350);
        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);

        // Initial data load
        refreshTable();
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }

    private void refreshTable() {
        // Clear existing rows
        tableModel.setRowCount(0);

        try (Connection connection = getConnection()) {
            String query = "SELECT * FROM recipes";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int recipeId = resultSet.getInt("recipe_id");
                        String title = resultSet.getString("title");
                        String ingredient_lst = resultSet.getString("ingredient_lst");
                        String user_rating = resultSet.getString("user_rating");

                        // Add a row to the table
                        tableModel.addRow(new Object[]{recipeId, title, ingredient_lst, user_rating});
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading recipes.");
        }
    }

    private void addRecipe() {
        // Implement the logic to add a recipe to the database
        // You can use a dialog to get input from the user
        // After adding, refresh the table
        refreshTable();
    }

    private void updateRecipe() {
        // Implement the logic to update a recipe in the database
        // You can use a dialog to get input from the user
        // After updating, refresh the table
        refreshTable();
    }

    private void deleteRecipe() {
        // Implement the logic to delete a recipe from the database
        // You can prompt the user for confirmation
        // After deleting, refresh the table
        refreshTable();
    }

    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            addRecipe();
        } else if (e.getSource() == updateButton) {
            updateRecipe();
        } 
        else if (e.getSource() == deleteButton) {
            deleteRecipe();
        } else if (e.getSource() == refreshButton) {
            refreshTable();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new recipesForm());
    }
}

