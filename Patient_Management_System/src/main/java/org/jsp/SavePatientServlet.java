package org.jsp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/savePatient")
public class SavePatientServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	try {
    	    int id = Integer.parseInt(request.getParameter("id"));
    	    String name = request.getParameter("name");
    	    int age = Integer.parseInt(request.getParameter("age"));
    	    String disease = request.getParameter("disease");

    	    Class.forName("com.mysql.cj.jdbc.Driver");

    	    Connection con = DriverManager.getConnection(
    	            "jdbc:mysql://localhost:3306/hospital1", "root", "sasi05");

    	    String query = "INSERT INTO patient VALUES (?, ?, ?, ?)";

    	    PreparedStatement ps = con.prepareStatement(query);

    	    ps.setInt(1, id);
    	    ps.setString(2, name);
    	    ps.setInt(3, age);
    	    ps.setString(4, disease);

    	    ps.executeUpdate();

    	    con.close();

    	} catch (Exception e) {
    	    e.printStackTrace();
    	}

    	
    	response.sendRedirect("addPatient.html");
    }
}