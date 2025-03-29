package in.sp.main;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/regForm")
public class Register extends HttpServlet {
	
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException
	{
		PrintWriter out = res.getWriter();
		
		
		String myname = req.getParameter("name1");
		String myemail = req.getParameter("email1");
		String mypassword = req.getParameter("pass1");
		String mygender = req.getParameter("gender1");
		String mycity = req.getParameter("city1");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rg" ,"root","ganesh2002" );
			
			PreparedStatement ps = con.prepareStatement("insert into employees values(?,?,?,?,?)");
			ps.setString(1, myname);
			ps.setString(2, myemail);
			ps.setString(3, mypassword);
			ps.setString(4, mygender);
			ps.setString(5, mycity);
			
			int count = ps.executeUpdate();
			
			if(count>0)
			{
				res.setContentType("text/html");
				out.print("<h3 style='color:green'>User registered successfully! </h3>");
				
				RequestDispatcher rd = req.getRequestDispatcher("/register.jsp");
				rd.include(req, res);
				
			}
			else
			{
				res.setContentType("text/html");
				out.print("<h3 style='color:red'>User not registered due to some error  </h3>");
				
				RequestDispatcher rd = req.getRequestDispatcher("/register.jsp");
				rd.include(req, res);
			}
			
		}
		catch(Exception e)
		{
			res.setContentType("text/html");
			out.print("<h3 style='color:red'>Exception Occured : "+e.getMessage()+"</h3>");
			
			RequestDispatcher rd = req.getRequestDispatcher("/register.jsp");
			rd.include(req, res);
		}
	}

}
