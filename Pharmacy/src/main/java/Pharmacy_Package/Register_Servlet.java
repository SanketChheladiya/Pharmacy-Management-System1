package Pharmacy_Package;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
import javax.servlet.http.Part;

public class Register_Servlet implements Servlet {

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

            String sub = req.getParameter("sum");
            if(sub.equals("Register"))
            {
                String Mname = req.getParameter("MName");
                String Pname = req.getParameter("PName");
                String Mno = req.getParameter("number");
                String Memail = req.getParameter("email");
                String pwd = req.getParameter("password");
                //String PL = req.getParameter("PLic");
                //String Mimage = req.getParameter("MImage");
                String address = req.getParameter("add");
                String PL="Default";
                String Mimage = "Default";
                
                InputStream inputStream = null;
                HttpServletRequest request = (HttpServletRequest)req;
                Part filePart = request.getPart("Plic");
                if (filePart != null) {
			// prints out some information for debugging
			System.out.println(filePart.getName());
			System.out.println(filePart.getSize());
			System.out.println(filePart.getContentType());

			// obtains input stream of the upload file
			inputStream = filePart.getInputStream();
		}

                if(Mname=="" || Pname=="" || Mno=="" || Memail=="" || pwd=="" || PL=="" || Mimage=="" || address=="")
                {
                    RequestDispatcher rd = req.getRequestDispatcher("Register.html");
                    out.println("<h1>Please fill all the fields!</h1>");
                    rd.include(req,res);
                }
                else
                {
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
                        //Statement st = con.createStatement();
                        
//                        String sqlquery =  "INSERT into PHARMACY_SYSTEM.USERS(MNAME,PNAME,MNO,EMAIL,ADDRESS,PASSWORD) values('"+Mname+"','"+Pname+"','"+Mno+"','"+Memail+"','"+address+"','"+pwd+"')";
                        String sqlquery =  "INSERT into aj.USERS(MNAME,PNAME,MNO,EMAIL,ADDRESS,PASSWORD,PLIC) values(?,?,?,?,?,?,?)";
                        //Integer rs = st.executeUpdate(sqlquery);
                        PreparedStatement st = con.prepareStatement(sqlquery);
                        st.setString(1,Mname);
                        st.setString(2,Pname);
                        st.setString(3,Mno);
                        st.setString(4,Memail);
                        st.setString(5,address);
                        st.setString(6,pwd);
                        st.setBlob(7, inputStream);
                        int row = st.executeUpdate();
                    } catch (SQLException ex) {
                        out.println(""+ex+"");
                        Logger.getLogger(Register_Servlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    RequestDispatcher rd1 = req.getRequestDispatcher("Login.html");
                    rd1.forward(req,res);

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
