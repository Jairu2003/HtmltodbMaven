package DbJava;



	import java.io.IOException;

	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.SQLException;

	import jakarta.servlet.ServletException;
	import jakarta.servlet.annotation.WebServlet;
	import jakarta.servlet.http.HttpServlet;
	import jakarta.servlet.http.HttpServletRequest;
	import jakarta.servlet.http.HttpServletResponse;

	@WebServlet("/InsertDataServlet")
	public class Main extends HttpServlet {
	   

	    protected void doPost(HttpServletRequest req, HttpServletResponse res)
	            throws ServletException, IOException {
	       
	        String Sid = req.getParameter("id");
	        String SName = req.getParameter("names");
	        String Address= req.getParameter("adds");
	        try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	        try {
	          
	        Connection connection = DriverManager.getConnection( "jdbc:mysql://localhost:3306/hiberdbjr","root","jairu");

	            String sql = "INSERT INTO `hiberdbjr`.`bcastudent`  (`S-Age`, `Student-name`, `Student-Address`)  VALUES (?,?, ?)";
	         PreparedStatement preparedStatement = connection.prepareStatement(sql);
	            preparedStatement.setString(1, Sid);
	            preparedStatement.setString(2, SName);
	            
	            preparedStatement.setString(3, Address);

	          
	            int rowsInserted = preparedStatement.executeUpdate();
	            if (rowsInserted > 0) {
	                res.getWriter().write("Data inserted successfully!");
	            } else {
	                res.getWriter().write("Failed to insert data.");
	            }

	            // Close the database connection
	            preparedStatement.close();
	            connection.close();
	        } catch (SQLException  e) {
	          
				e.printStackTrace();
	            res.getWriter().write("Database error: " + e.getMessage());
	        }
	    }
	}


