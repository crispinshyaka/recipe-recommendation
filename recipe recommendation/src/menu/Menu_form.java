
package menu;



import javax.swing.*;
import OtherForms.Registrationform;
import CRUDForms.IngredientsForm;
import CRUDForms.ratingsform;
import CRUDForms.recipelogsform;
import CRUDForms.recipesForm;
import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;

	public class Menu_form extends JFrame {
	    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;


		public Menu_form() {
	        setTitle("Menu");
	        setSize(400, 300);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        // Create buttons for each module
	        JButton RegistrationformButton = new JButton("Registrationform");
	        JButton ingredientModuleButton = new JButton("Ingredient Module");
	        JButton recipeButton = new JButton("Recipe Module");
	        JButton ratingsButton = new JButton("ratings");
	        JButton userlogsFormButton = new JButton("userlogs");
	        JButton recipelogButton = new JButton("Recipelog");

	        // Add action listeners for each button
	        RegistrationformButton.addActionListener(new ActionListener() {
	           
	            public void actionPerformed(ActionEvent e) {
	               
	                dispose();
	                new Registrationform().setVisible(true);
	            }
	        });

	        ingredientModuleButton.addActionListener(new ActionListener() {
	  
	            public void actionPerformed(ActionEvent e) {
	                
	                dispose(); 
	                new IngredientsForm().setVisible(true); 
	            }
	        });

	        recipeButton.addActionListener(new ActionListener() {
	      	  
	            public void actionPerformed(ActionEvent e) {
	                
	                dispose(); 
	                new recipesForm().setVisible(true); 
	            }
	        });
	        ratingsButton.addActionListener(new ActionListener() {
	      	  
	            public void actionPerformed(ActionEvent e) {
	                
	                dispose(); 
	                new ratingsform().setVisible(true); 
	            }
	        });
	        userlogsFormButton.addActionListener(new ActionListener() {
	      	  
	            public void actionPerformed(ActionEvent e) {
	                
	                dispose(); 
	               new userlogsForm(1,1).setVisible(true); 
	            }
	        });
	        recipelogButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	               dispose();
	               new recipelogsform(1,1).setVisible(true);
	            }
	        });

	        // Create layout
	        JPanel panel = new JPanel();
	        panel.add(RegistrationformButton);
	        panel.add(ingredientModuleButton);
	        panel.add(recipeButton);
	        panel.add(ratingsButton);
	        panel.add(recipelogButton);
	        panel.add(userlogsFormButton);

	        getContentPane().add(panel);

	        setLocationRelativeTo(null); // Center the form
	    }

	    private void RegistrationformModule() {
	        // Logic to open User Module form
	        // You can instantiate and show your User Module form here
	        // For example: new UserModuleForm().setVisible(true);
	        JOptionPane.showMessageDialog(this, "Opening Registration Module");
	    }

	    private void openIngredientModule() {
	        // Logic to open Ingredient Module form
	        // You can instantiate and show your Ingredient Module form here
	        // For example: new IngredientModuleForm().setVisible(true);
	        JOptionPane.showMessageDialog(this, "Opening Ingredient Module");
	    }

	    private void openRecipelog() {
	        // Logic to open Recipe Module form
	        // You can instantiate and show your Recipe Module form here
	        // For example: new RecipeModuleForm().setVisible(true);
	        JOptionPane.showMessageDialog(this, "recipelogModule");
	    }
	    private void openuserlog() {
	        // Logic to open Recipe Module form
	        // You can instantiate and show your Recipe Module form here
	        // For example: new RecipeModuleForm().setVisible(true);
	        JOptionPane.showMessageDialog(this, "userlogModule");
	    }

	    private void openratings() {
	        // Logic to open Recipe Module form
	        // You can instantiate and show your Recipe Module form here
	        // For example: new RecipeModuleForm().setVisible(true);
	        JOptionPane.showMessageDialog(this, "ratings Module");
	    }

	    private void openrecipe() {
	        // Logic to open Recipe Module form
	        // You can instantiate and show your Recipe Module form here
	        // For example: new RecipeModuleForm().setVisible(true);
	        JOptionPane.showMessageDialog(this, "Recipe Module");
	    }


	    public static void main(String[] args) {
	        SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	                new Menu_form().setVisible(true);
	            }
	        });
	    }
	}


