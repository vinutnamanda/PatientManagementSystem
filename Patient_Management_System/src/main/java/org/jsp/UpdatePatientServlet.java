package org.jsp;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/updatePatient")
public class UpdatePatientServlet extends HttpServlet {

    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
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
                out.println("<h1>Update Patient</h1>");

                out.println("<form action='updatePatient' method='post'>");

                out.println("ID: <input type='text' name='id' value='" + rs.getInt("id") + "' ><br><br>");
                out.println("Name: <input type='text' name='name' value='" + rs.getString("name") + "'><br><br>");
                out.println("Age: <input type='text' name='age' value='" + rs.getInt("age") + "'><br><br>");
                out.println("Disease: <input type='text' name='disease' value='" + rs.getString("disease") + "'><br><br>");

                out.println("<button type='submit'>Update</button>");

                out.println("</form>");

                out.println("<br><form action='home.html'><button>Home</button></form>");

                out.println("</body></html>");
            }
            
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


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

            String query = "UPDATE patient SET name=?, age=?, disease=? WHERE id=?";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setString(3, disease);
            ps.setInt(4, id);

            ps.executeUpdate();

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        
        response.sendRedirect("findPatient.html");
    }
}