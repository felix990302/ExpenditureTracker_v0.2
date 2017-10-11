/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Controllers.Controller;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author chang
 */
@WebServlet("/UpdaterServlet")
public class UpdaterServlet extends HttpServlet {

    Controller controller = new Controller();

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        
        PrintWriter out = response.getWriter();
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<body>");

        int id = Integer.valueOf(request.getSession(false).getAttribute("ID").toString());
        String postAmount = request.getParameter("amount");
        

        if (postAmount.equals("")) {

            out.println("<h1>Error!</h1>");
            out.println("<h2>Please enter an amount</h2>");
            out.println(
                    "<form action=\"updateExp.jsp\" method=\"post\">\n"
                    + "<input type=\"submit\" value=\"OK\" />"
                    + "</form>");
            
            out.println("</body>");
            out.println("</html>");
        }

        double amount = Double.valueOf(postAmount);
        String type = request.getParameter("ExpType");

        

        

        out.println("<h1>Updating your information</h1>");

        updateExp(response, id, amount, type);

        out.println(
                "<form action=\"index.jsp\" method=\"post\">\n"
                + "<input type=\"submit\" value=\"log out\" />"
                + "</form>");

        out.println("</body>");
        out.println("</html>");

    }

    public void updateExp(HttpServletResponse response, int id, double amount, String type) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<h2>using updateExp method to record your expenditure</h2>");

        controller.updateExp(id, type, amount);

        out.println(
                "<form action=\"/ExpenditureTracker_v0.2/getSpending\" method=\"post\">\n"
                + "<input type=\"submit\" value=\"verify\" />"
                + "</form>");

        out.println("<p>&nbsp;</p>");

    }

}
