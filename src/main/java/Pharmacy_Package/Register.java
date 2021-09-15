/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pharmacy_Package;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author admin
 */
@MultipartConfig(maxFileSize = 16177215)
public class Register extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
        String sub = request.getParameter("sum");
            if(sub.equals("Register"))
            {
                String Mname = request.getParameter("MName");
                String Pname = request.getParameter("PName");
                String Mno = request.getParameter("number");
                String Memail = request.getParameter("email");
                String pwd = request.getParameter("password");
                
                String address = request.getParameter("add");
                
                InputStream inputStream = null,inputStream1 = null;
                
                Part filePart = request.getPart("Plic");
                Part filePart1 = request.getPart("Mimage");
                if (filePart != null) {
			// prints out some information for debugging
                        
			System.out.println(filePart.getName());
			System.out.println(filePart.getSize());
			System.out.println(filePart.getContentType());

			// obtains input stream of the upload file
			inputStream = filePart.getInputStream();
		}
                if (filePart1 != null) {
			// prints out some information for debugging
                        
			System.out.println(filePart1.getName());
			System.out.println(filePart1.getSize());
			System.out.println(filePart1.getContentType());

			// obtains input stream of the upload file
			inputStream1 = filePart1.getInputStream();
		}
                if(Mname=="" || Pname=="" || Mno=="" || Memail=="" || pwd=="" || address=="")
                {
                    RequestDispatcher rd = request.getRequestDispatcher("Register.html");
                    out.println("<h1>Please fill all the fields!</h1>");
                    rd.include(request,response);
                }
                else
                {
                    String jdbcurl = "jdbc:derby://localhost:1527/Pharmacy_System"; 
                    String driverclassname = "org.apache.derby.jdbc.ClientDriver";
                    String username = "Pharmacy_System";
                    String pword = "Pharmacy_System";

                    try{
                        Class cls = Class.forName(driverclassname);
                     // returns the name and package of the class
                     System.out.println("Class found = " + cls.getName());
                     System.out.println("Package = " + cls.getPackage());
                    }
                    catch(ClassNotFoundException ex) {
                     System.out.println(ex.toString());
                    }
                    try {
                        Connection con = DriverManager.getConnection(jdbcurl,username,pword);
                        //Statement st = con.createStatement();
                        
//                        String sqlquery =  "INSERT into PHARMACY_SYSTEM.USERS(MNAME,PNAME,MNO,EMAIL,ADDRESS,PASSWORD) values('"+Mname+"','"+Pname+"','"+Mno+"','"+Memail+"','"+address+"','"+pwd+"')";
                        String sqlquery =  "INSERT into PHARMACY_SYSTEM.USERS(MNAME,PNAME,MNO,EMAIL,ADDRESS,PASSWORD,PLIC,MIMAGE) values(?,?,?,?,?,?,?,?)";
                        //Integer rs = st.executeUpdate(sqlquery);
                        PreparedStatement st = con.prepareStatement(sqlquery);
                        st.setString(1,Mname);
                        st.setString(2,Pname);
                        st.setString(3,Mno);
                        st.setString(4,Memail);
                        st.setString(5,address);
                        st.setString(6,pwd);
                        st.setBlob(7, inputStream);
                        st.setBlob(8, inputStream1);
                        
                        int row = st.executeUpdate();
                    } catch (SQLException ex) {
                        out.println(""+ex+"");
                        Logger.getLogger(Register_Servlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    RequestDispatcher rd1 = request.getRequestDispatcher("Login.html");
                    rd1.forward(request,response);

                }
        }   
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
