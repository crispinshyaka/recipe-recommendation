package OtherForms;
import javax.swing.*;

import CRUDForms.RecommendedRecipesForm;
import CRUDForms.RecipesForm;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class loggedin_Form extends JFrame implements ActionListener {
    private JLabel welcomeLabel;
    private JTextArea ingredientsTextArea;
    private JButton viewRecipesButton;

    private String first_name;

    public loggedin_Form(String first_name) {
        this.first_name = first_name;

        setTitle("User Home Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        welcomeLabel = new JLabel("Welcome, " + first_name + "!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        welcomeLabel.setBounds(20, 20, 300, 30);
        ingredientsTextArea = new JTextArea();
        ingredientsTextArea.setLineWrap(true);
        ingredientsTextArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(ingredientsTextArea);
        scrollPane.setBounds(20, 60, 400, 100);

        viewRecipesButton = new JButton("View Recipes");
        viewRecipesButton.setBounds(20, 180, 150, 30);
        viewRecipesButton.addActionListener(this);


        setLayout(null);
        add(welcomeLabel);
        add(viewRecipesButton);
        add(scrollPane);
        setSize(450, 250);
        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
    }
    private void viewRecipes() {
        String ingredients = ingredientsTextArea.getText();
        new RecommendedRecipesForm(first_name, ingredients);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == viewRecipesButton) {
            viewRecipes();
        }
    }
public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> new loggedin_Form("first_name"));
}
}
