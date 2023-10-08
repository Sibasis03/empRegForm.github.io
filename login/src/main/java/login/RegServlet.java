package login;

import jakarta.servlet.ServletConfig;
 
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;



public class RegServlet extends HttpServlet {
	
	Connection con;
	
	private static final long serialVersionUID = 1L;
    
    public RegServlet() {
        super();
        
    }

	
	public void init(ServletConfig config) throws ServletException {
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
		    con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","sanu1234");
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	
	public void destroy() {
		
		
			try {
				con.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String s1 = request.getParameter("name");
		String s2 = request.getParameter("email");
		String s3 = request.getParameter("password");
		
		try {
			PreparedStatement pst = con.prepareStatement("insert into info values(?,?,?)");
		
			pst.setString(1, s1);
			pst.setString(2, s2);
			pst.setString(3, s3);
			pst.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		
		PrintWriter pw = response.getWriter();
		pw.println("you have successfully registred.");
		
		
	
		
	}

}
