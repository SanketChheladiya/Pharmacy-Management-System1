package Pharmacy_Package;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Inter1 implements Servlet {

    ServletConfig config=null;  


    @Override
    public void init(ServletConfig config) throws ServletException {
        this.config=config;
        System.out.println("servlet is initialized"); 
    }

    @Override
    public ServletConfig getServletConfig() {
        return config;
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = res.getWriter()) {

            String sub1 = req.getParameter("submit");
            if(sub1.equals("Register"))
            {
                RequestDispatcher rd = req.getRequestDispatcher("Register.html");
                rd.forward(req, res);
            }
            else
            {
                String em = req.getParameter("email");
                String pwd = req.getParameter("password"); 
                if(em=="" || pwd=="")
                {
                    RequestDispatcher rd3 = req.getRequestDispatcher("Login.html");
                    out.println("<h1>Please fill all the fields!</h1>");
                    rd3.include(req, res);
                }
                else
                {
                    //C:/xampp/htdocs/phpacademy/background.jpg
//                    String jdbcurl = "jdbc:derby://localhost:1527/Pharmacy_System"; 
//                    String driverclassname = "org.apache.derby.jdbc.ClientDriver";
//                    String username = "Pharmacy_System";
//                    String pword = "Pharmacy_System";
                     String jdbcurl = "jdbc:mysql://localhost:3306/aj?zeroDateTimeBehavior=CONVERT_TO_NULL"; 
                String driverclassname = "com.mysql.cj.jdbc.Driver";
                String username = "root";
                String pword = "";

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
                        Statement st = con.createStatement();
                        String sqlquery =  "SELECT * FROM users WHERE Email='"+em+"' and Password='"+pwd+"'";
                        java.sql.ResultSet rs = (java.sql.ResultSet)st.executeQuery(sqlquery);
                        int j=0;
                        while(rs.next())
                        {
                            j++;
                        }
                        
                        if(j!=0)
                        {
                            HttpServletRequest request = (HttpServletRequest) req;
                            HttpSession hs;
                            hs=request.getSession();
                            hs.setAttribute("Memail",em);
                            RequestDispatcher rd1 = request.getRequestDispatcher("WelcomePage.html");
                            rd1.forward(request,res);
                        }
                        else
                        {
                            RequestDispatcher rd2 = req.getRequestDispatcher("Login.html");
                            out.println("<h2>Sorry, try again!</h2>");
                            out.println("<h2>Error: Invalid Credential </h2>");
                            rd2.include(req, res);
                        }
                    } catch (SQLException ex) {
                        out.println(""+ex+"");
                        Logger.getLogger(Inter1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
            }
    }

    }

    @Override
    public void destroy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
    }

    @Override
    public String getServletInfo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
