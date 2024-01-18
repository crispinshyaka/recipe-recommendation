package CRUDForms;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public class IngredientsForm extends JFrame implements ActionListener {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable ingredientTable;
    private DefaultTableModel tableModel;
    private JButton refreshButton;

    private static final String JDBC_URL = "jdbc:mysql:/localhost:3306/crispin_shyaka_222004852";
    private static final String USERNAME = "shyaka";
    private static final String PASSWORD = "222004852";

    public IngredientsForm() {
        setTitle("Ingredient Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Table
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Ingredient ID");
        tableModel.addColumn("Ingredient Name");

        ingredientTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(ingredientTable);
        scrollPane.setBounds(20, 20, 300, 200);

        // Refresh Button
        refreshButton = new JButton("Refresh");
        refreshButton.setBounds(20, 240, 100, 30);
        refreshButton.addActionListener(this);

        // Layout
        setLayout(null);
        add(scrollPane);
        add(refreshButton);

        setSize(350, 350);
        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }

    private void refreshTable() {
        // Clear existing rows
        tableModel.setRowCount(0);

        try (Connection connection = getConnection()) {
            String query = "SELECT * FROM ingredients";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int ingredientId = resultSet.getInt("ingredient_id");
                        String ingredientName = resultSet.getString("ingredient_name");

                        // Add a row to the table
                        tableModel.addRow(new Object[]{ingredientId, ingredientName});
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading ingredients.");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == refreshButton) {
            refreshTable();
        }
    }

    public static void main(String[] args) {
    	 SwingUtilities.invokeLater(() -> new IngredientsForm());
        	
    }
}
