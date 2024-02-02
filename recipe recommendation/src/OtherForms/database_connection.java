package OtherForms;



	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.SQLException;

	public class database_connection {
	    private static Connection connection;

	    public static Connection getConnection() {
	        if (connection == null) {
	            try {
	                Class.forName("com.mysql.cj.jdbc.Driver");
	                String url = "jdbc:mysql:/localhost:3306/crispin_shyaka_222004852";
	                String username = "shyaka";
	                String password = "222004852";
	                connection = DriverManager.getConnection(url, username, password);
	            } catch (ClassNotFoundException | SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        return connection;
	    }
	}


