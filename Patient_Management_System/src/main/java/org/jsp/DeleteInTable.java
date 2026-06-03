package org.jsp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/deleteintable")
public class DeleteInTable extends HttpServlet {

	

	    protected void doPost(HttpServletRequest req, HttpServletResponse res)
	            throws IOException {

	        int id = Integer.parseInt(req.getParameter("id"));

	        try {
	            Connection con = DriverManager.getConnection(
	                    "jdbc:mysql://localhost:3306/hospital1", "root", "sasi05");

	            PreparedStatement ps = con.prepareStatement("DELETE FROM patient WHERE id=?");
	            ps.setInt(1, id);

	            ps.executeUpdate();

	          
	            res.sendRedirect("displayall");

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}

