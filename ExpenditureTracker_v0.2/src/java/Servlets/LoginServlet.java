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
import Objects.Consumer;
import Security.HashAndSalt;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author chang
 *
 *
 */
@WebServlet("/first-page")
public class LoginServlet extends HttpServlet {

    Controller controller = new Controller();

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<body>");

        String identifier = request.getServletPath();

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        if (username.equals("") || password.equals("")) {
            
            
            out.println("<h1>Error!</h1>");
            out.println("<h2>Please enter your credentials</h2>");
            out.println(
                    "<form action=\"index.jsp\" method=\"post\">\n"
                    
                    + "<input type=\"submit\" value=\"OK\" />"
                    + "</form>");

            
        }
        
        String hashed = HashAndSalt.getHashed(password, username);
        
        
        
        if (identifier.equals("/login")) {
            
            login(response, request, username, hashed);
            
            

        } else if (identifier.equals("/createUser")) {

            createUser(response, username, hashed);

        }

        out.println("</body>");
        out.println("</html>");

    }
    
    public void login (HttpServletResponse response, HttpServletRequest request, String username, String password) throws IOException {

        PrintWriter out = response.getWriter();
        
        Consumer curr = controller.login(username, password);

        if (curr!=null) {
            request.getSession(true).setAttribute("ID", curr.getConsID());
            
            out.println("<h1>Siu Solutions</h1>");
            out.println("<h2>Welcome Back</h2>");
            out.println(
                    "<form action=\"/ExpenditureTracker_v0.2/getSpending\" method=\"post\">\n"
                    
                    + "<input type=\"submit\" value=\"check my spending\" />"
                    + "</form>");

            
        }
        else{
            out.println("<h1>Error!</h1>");
            out.println("<p>username does not exist or password does not match</p>");
            out.println(
                    "<form action=\"index.jsp\" method=\"post\">\n"
                    
                    + "<input type=\"submit\" value=\"try again\" />"
                    + "</form>");
        }

    }

    public void createUser(HttpServletResponse response, String username, String password) throws IOException {

        PrintWriter out = response.getWriter();

        if (controller.newUser(username, password)) {
            out.println("<h1>Success</h1>");
            out.println("<p>Welcome to Siu Solutions</p>");
            out.println(
                    "<form action=\"index.jsp\" method=\"post\">\n"
                    
                    + "<input type=\"submit\" value=\"login\" />"
                    + "</form>");

            
        }
        else{
            out.println("<h1>Username Taken!</h1>");
            out.println(
                    "<form action=\"index.jsp\" method=\"post\">\n"
                    
                    + "<input type=\"submit\" value=\"try again\" />"
                    + "</form>");
        }

    }

}
