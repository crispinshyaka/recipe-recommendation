package CRUDForms;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ratingsform extends JFrame implements ActionListener {
    private JLabel recipeLabel, ratingLabel;
    private JTextField ratingTextField;
    private JTextField recipenameTextField;
    private JButton submitButton;
   

    public ratingsform() {
        setTitle("Ratings Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        recipeLabel = new JLabel("Recipe:");
        recipeLabel.setBounds(20, 20, 200, 30);
        
        recipenameTextField = new JTextField();
        recipenameTextField.setBounds(70, 20, 200, 30);

        ratingLabel = new JLabel("Your Rating (1-5):");
        ratingLabel.setBounds(20, 60, 150, 30);

        ratingTextField = new JTextField();
        ratingTextField.setBounds(150, 60, 50, 30);

        submitButton = new JButton("Submit Rating");
        submitButton.setBounds(20, 100, 150, 30);
        submitButton.addActionListener(this);

        setLayout(null);
        add(recipeLabel);
        add(ratingLabel);
        add(ratingTextField);
        add(recipenameTextField);
        add(submitButton);

        setSize(300, 250);
        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
    }

    // Simulated method to store the rating
    private void submitRating(int rating) {
        // Replace this with your actual logic to store the rating
        JOptionPane.showMessageDialog(this, "Rating submitted successfully!");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            try {
                int rating = Integer.parseInt(ratingTextField.getText());
                if (rating >= 1 && rating <= 5) {
                    submitRating(rating);
                } else {
                    JOptionPane.showMessageDialog(this, "Please enter a valid rating between 1 and 5.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid numeric rating.");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ratingsform());
    }
}
