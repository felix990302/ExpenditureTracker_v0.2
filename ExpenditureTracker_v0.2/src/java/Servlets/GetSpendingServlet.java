/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Controllers.Controller;
import Objects.Spending;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author chang
 */
@WebServlet(name = "GetSpendingServlet", urlPatterns = {"/getSpending"})
public class GetSpendingServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            response.setContentType("text/html");
            
            PrintWriter out = response.getWriter();
            
            out.println("<!DOCTYPE html>");
            out.println("<h1>Grabbing your information</h1>");

            int id = Integer.parseInt(request.getSession(false).getAttribute("ID").toString());

            getSpending(request, response, id);
        } catch (ServletException ex) {
            Logger.getLogger(GetSpendingServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(GetSpendingServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void getSpending(HttpServletRequest request, HttpServletResponse response, int id)
            throws ServletException, IOException, SQLException {
        Controller controller = new Controller();

        ArrayList<Spending> expenditure = controller.getExpenditure(id);
        
        DecimalFormat decim = new DecimalFormat("0.00");

        PrintWriter out = response.getWriter();

        out.println("<p>Using getSpending method to retrieve your information</p>");

        for (int k = expenditure.size()-1; k > -1; k--) {
            Spending temp = expenditure.get(k);

            out.println("<p>You spent $" + decim.format(temp.getAmount()) + " for " + temp.getType() + " on " + temp.getDate() + "</p>");
        }
        
        out.println(
                    "<form action=\"updateExp.jsp\" method=\"post\">\n"
                    
                    + "<input type=\"submit\" value=\"record a purchase\" />"
                    + "</form>");
        
        out.println("<p>&nbsp;</p>");
        
        out.println(
                    "<form action=\"index.jsp\" method=\"post\">\n"
                    
                    + "<input type=\"submit\" value=\"log out\" />"
                    + "</form>");

    }

}
