package org.jsp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/displayall")
public class DisplayAll extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        PrintWriter out = res.getWriter();

        out.println("<html><body>");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/hospital1",
                    "root",
                    "sasi05"
            );

            Statement statement = connection.createStatement();
            String query = "SELECT * FROM patient";
            ResultSet rs = statement.executeQuery(query);

            out.println("<h2>All Patients</h2>");

            out.println("<table border='1' style='border-collapse:collapse; width:80%; text-align:center;'>");

            out.println("<tr>");
            out.println("<th>ID</th>");
            out.println("<th>Name</th>");
            out.println("<th>Age</th>");
            out.println("<th>Disease</th>");
            out.println("<th>Operations</th>");
            out.println("</tr>");

            while (rs.next()) {

                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String disease = rs.getString("disease");

                out.println("<tr>");

                out.println("<td>" + id + "</td>");
                out.println("<td>" + name + "</td>");
                out.println("<td>" + age + "</td>");
                out.println("<td>" + disease + "</td>");

                out.println("<td>");

                out.println("<form action='updateInTable' method='get' style='display:inline;'>");
                out.println("<input type='hidden' name='id' value='" + id + "'>");
                out.println("<button type='submit'>Update</button>");
                out.println("</form>");

                out.println("<form action='deleteintable' method='post' style='display:inline;'>");
                out.println("<input type='hidden' name='id' value='" + id + "'>");
                out.println("<button type='submit'>Delete</button>");
                out.println("</form>");

                out.println("</td>");

                out.println("</tr>");
            }

            out.println("</table>");
        	out.println("<form action='home.html'>");
        	out.println("<button type='submit'>Home</button>");
        	out.println("</form>");
            out.println("</body></html>");

            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}