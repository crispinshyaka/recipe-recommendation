package CRUDForms;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ratingsform extends JFrame implements ActionListener {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable ratingsTable;
    private DefaultTableModel tableModel;
    private JButton addRatingButton, refreshButton;

    private static final String JDBC_URL = "jdbc:mysql:/localhost:3306/crispin_shyaka_222004852";
    private static final String USERNAME = "shyaka";
    private static final String PASSWORD = "2220004852";

    public ratingsform() {
        setTitle("Ratings Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

       
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Rating ID");
        tableModel.addColumn("Recipe ID");
        tableModel.addColumn("User ID");
        tableModel.addColumn("Rating Value");

        ratingsTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(ratingsTable);
        scrollPane.setBounds(20, 20, 500, 200);

        
        addRatingButton = new JButton("Add Rating");
        refreshButton = new JButton("Refresh");

        addRatingButton.setBounds(20, 240, 120, 30);
        refreshButton.setBounds(150, 240, 100, 30);

        addRatingButton.addActionListener(this);
        refreshButton.addActionListener(this);

        
        setLayout(null);
        add(scrollPane);
        add(addRatingButton);
        add(refreshButton);

        setSize(550, 350);
        setLocationRelativeTo(null); 
        setVisible(true);

        
        refreshTable();
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }

    private void refreshTable() {
        
        tableModel.setRowCount(0);

        try (Connection connection = getConnection()) {
            String query = "SELECT * FROM ratings";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int ratingId = resultSet.getInt("rating_id");
                        int recipeId = resultSet.getInt("recipe_id");
                        int userId = resultSet.getInt("user_id");
                        int ratingValue = resultSet.getInt("rating_value");

                        
                        tableModel.addRow(new Object[]{ratingId, recipeId, userId, ratingValue});
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading ratings.");
        }
    }

    private void addRating() {
        
        
        
        refreshTable();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addRatingButton) {
            addRating();
        } else if (e.getSource() == refreshButton) {
            refreshTable();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ratingsform());
    }
}
