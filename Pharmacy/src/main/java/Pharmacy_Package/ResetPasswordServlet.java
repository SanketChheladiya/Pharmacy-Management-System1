/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pharmacy_Package;
 
import java.io.IOException;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
 
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 

 
/**
 * A Java Servlet to handle requests to reset password for customer
 *
 * @author www.codejava.net
 *
 */
@WebServlet("/reset_password")
public class ResetPasswordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    private String host;
    private String port;
    private String email;
    private String name;
    private String pass;
 
    public void init() {
        // reads SMTP server setting from web.xml file
        ServletContext context = getServletContext();
        host = context.getInitParameter("host");
        port = context.getInitParameter("port");
        email = context.getInitParameter("email");
        name = context.getInitParameter("name");
        pass = context.getInitParameter("pass");
    }
 
    public ResetPasswordServlet() {
    }
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        String page = "reset_password.jsp";
        request.getRequestDispatcher(page).forward(request, response);
 
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String recipient = request.getParameter("email");
        String subject = "Your Password has been reset";
         
        CustomerServices customerServices = new CustomerServices();
        String newPassword = customerServices.resetCustomerPassword(recipient);
        
        if(newPassword.equals(""))
        {
            RequestDispatcher rd = request.getRequestDispatcher("reset_password.jsp");
            rd.forward(request, response);
        }
        else
        {
            String content = "Hii User,\n this is your password: " + newPassword + " for Pharmacy System";
            content += "\nNote: for security reason, "
                    + "you must change your password after logging in.";

            String message = "";

            try {
                EmailUtility.sendEmail(host, port, email, name, pass,
                        recipient, subject, content);
                message = "Your password has been reset. Please check your e-mail.";
            } catch (Exception ex) {
                ex.printStackTrace();
                message = "There were an error: " + ex.getMessage();
            } finally {

                request.getRequestDispatcher("Login.html").forward(request, response);
            }         
            
        }
    }
 
}