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

@WebServlet("/updateInTable")
public class UpdateInTable extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException {

        PrintWriter out = res.getWriter();

        int id = Integer.parseInt(req.getParameter("id"));

        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/hospital1", "root", "sasi05");

            PreparedStatement ps = con.prepareStatement("SELECT * FROM patient WHERE id=?");
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                out.println("<html><body>");
                out.println("<h2>Update Patient</h2>");

                out.println("<form action='updateInTable' method='post'>");

                out.println("<input type='hidden' name='id' value='" + id + "'>");

                out.println("Name: <input name='name' value='" + rs.getString("name") + "'><br><br>");
                out.println("Age: <input name='age' value='" + rs.getInt("age") + "'><br><br>");
                out.println("Disease: <input name='disease' value='" + rs.getString("disease") + "'><br><br>");

                out.println("<button type='submit'>Update</button>");
                out.println("</form>");

                out.println("<br><a href='displayall'><button>Home</button></a>");

                out.println("</body></html>");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        protected void doPost(HttpServletRequest req, HttpServletResponse res)
                throws IOException {

            int id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("name");
            int age = Integer.parseInt(req.getParameter("age"));
            String disease = req.getParameter("disease");

            try {
                Connection con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/hospital1", "root", "sasi05");

                PreparedStatement ps = con.prepareStatement(
                        "UPDATE patient SET name=?, age=?, disease=? WHERE id=?");

                ps.setString(1, name);
                ps.setInt(2, age);
                ps.setString(3, disease);
                ps.setInt(4, id);

                ps.executeUpdate();

               
                res.sendRedirect("displayall");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    