package login;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class LoginServlet extends HttpServlet {
	
	Connection con;
	
	
	private static final long serialVersionUID = 1L;
       
    
    public LoginServlet() {
        super();
        
    }
    
    public void init(ServletConfig config) throws ServletException {
    	
    	try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","sanu1234");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		 
			String s1 = request.getParameter("email");
			String s2 = request.getParameter("password");
			
			try {
				PreparedStatement pst = con.prepareStatement("Select * from info where email=? and password=?");
				pst.setString(1, s1);
				pst.setString(2,s2);
				ResultSet rs = pst.executeQuery();
				
				PrintWriter pw=response.getWriter();
				
				if (rs.next() ) {
					
		
				    response.sendRedirect("welcome?email="+s1+"&password="+s2);
				} else {
					
					pw.println("invalid user name or password");
					RequestDispatcher rd = request.getRequestDispatcher("homepage.html");
					rd.include(request, response);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
			
			
	}

}
