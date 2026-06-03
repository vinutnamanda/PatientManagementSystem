package org.jsp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/findPatient")
public class FindPatientServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();

        try {
            int id = Integer.parseInt(request.getParameter("id"));

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/hospital1", "root", "sasi05");

            String query = "SELECT * FROM patient WHERE id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

            	out.println("<html><body>");
            	out.println("<h1>Patient Details</h1>");

            	out.println("<p>ID: " + rs.getInt("id") + "</p>");
            	out.println("<p>Name: " + rs.getString("name") + "</p>");
            	out.println("<p>Age: " + rs.getInt("age") + "</p>");
            	out.println("<p>Disease: " + rs.getString("disease") + "</p>");

            	out.println("<br>");

            	// UPDATE 
            	out.println("<form action='updatePatient' method='get'>");
            	out.println("<input type='hidden' name='id' value='" + rs.getInt("id") + "'>");
            	out.println("<button type='submit'>Update</button>");
            	out.println("</form>");

            	// DELETE 
            	out.println("<form action='deletePatient' method='post'>");
            	out.println("<input type='hidden' name='id' value='" + rs.getInt("id") + "'>");
            	out.println("<button type='submit'>Delete</button>");
            	out.println("</form>");

            	out.println("<form action='home.html'>");
            	out.println("<button type='submit'>Home</button>");
            	out.println("</form>");

            	out.println("</body></html>");

            } else {
                out.println("<html><body>");
                out.println("<h2>No Patient Found</h2>");

                out.println("<form action='findPatient.html'>");
                out.println("<button>Back</button>");
                out.println("</form>");

                out.println("</body></html>");
            }
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}