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

public class Add_Drug implements Servlet {

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
        try (PrintWriter out = res.getWriter()) 
        {

            String sub1 = req.getParameter("sub");
            
            if(sub1.equals("Add"))
            {
                String memo_no= req.getParameter("Invno");
                String agency_name = req.getParameter("Aname");
                String company_name = req.getParameter("Cname");
                String product_name = req.getParameter("Pname");
                String product_mfg_date = req.getParameter("MFGDate");
                String product_expiry_date = req.getParameter("EXPDate");
                String product_price = req.getParameter("Pprice");
                String product_quantity = req.getParameter("Pquantity");
                String Drug_Location = req.getParameter("loc");
             
                
                HttpServletRequest request = (HttpServletRequest) req;
                HttpSession session=request.getSession(false);
                String em=(String)session.getAttribute("Memail");
                
                if(Drug_Location=="" || memo_no=="" || agency_name=="" || company_name=="" || product_name=="" || product_mfg_date=="" || product_expiry_date=="" || product_price=="" || product_quantity=="")
                {
                    RequestDispatcher rd3 = req.getRequestDispatcher("WelcomePage.html");
                    out.println("<h1>Please fill all the fields!</h1>");
                    rd3.include(req, res);
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
                        Statement st = con.createStatement();
                        String sqlquery1 = "SELECT * FROM drug_details WHERE product_name='"+product_name+"'";
                        java.sql.ResultSet rs = (java.sql.ResultSet)st.executeQuery(sqlquery1);
                        int sum=0;
                        int j=0;
                        while(rs.next())
                        {
                            String qun = rs.getString("product_quantity");
                            int val = Integer.parseInt(qun);
                            sum = sum + val;
                            j++;
                        }
                        sum = sum + Integer.parseInt(product_quantity);
                        String sum1 = String.valueOf(sum);
                        if(j==0)
                        {
                            String sqlquery =  "insert into Drug_Details(memo_no,company_name,product_name,product_mfg_date,product_exp_date,product_price,product_quantity,agency_name,drug_location,drugs_instock) values('"+memo_no+"','"+company_name+"','"+product_name+"','"+product_mfg_date+"','"+product_expiry_date+"','"+product_price+"','"+product_quantity+"','"+agency_name+"','"+Drug_Location+"','"+product_quantity+"')";
                            Integer rs1 = st.executeUpdate(sqlquery);
                            
                        }
                        else
                        {
                            String sqlquery =  "insert into Drug_Details(memo_no,company_name,product_name,product_mfg_date,product_exp_date,product_price,product_quantity,agency_name,drug_location,drugs_instock) values('"+memo_no+"','"+company_name+"','"+product_name+"','"+product_mfg_date+"','"+product_expiry_date+"','"+product_price+"','"+product_quantity+"','"+agency_name+"','"+Drug_Location+"','"+sum1+"')";
                            Integer rs1 = st.executeUpdate(sqlquery);
                            String sqlquery2 =  "update Drug_Details set drugs_instock='"+sum1+"' where product_name='"+product_name+"'";
                            Integer rs2 = st.executeUpdate(sqlquery2);
                        }                        
                    } catch (SQLException ex) {
                        out.println(""+ex+"");
                        Logger.getLogger(Register_Servlet.class.getName()).log(Level.SEVERE, null, ex);
                    }

                   RequestDispatcher rd = req.getRequestDispatcher("WelcomePage.html");
                   rd.forward(req, res); 
                }
                
            }
            else if(sub1.equals("Delete"))
            {
                String invno = req.getParameter("Invno");
                HttpServletRequest request = (HttpServletRequest) req;
                HttpSession session=request.getSession(false);
                String em=(String)session.getAttribute("Memail");
                if(invno=="")
                {
                    RequestDispatcher rd3 = req.getRequestDispatcher("WelcomePage.html");
                    out.println("<h1>Please fill all the fields!</h1>");
                    rd3.include(req, res);
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
                        Statement st = con.createStatement();
                        String sqlquery = "delete from Drug_Details where memo_no  = '"+invno+"'";
                        Integer rs = st.executeUpdate(sqlquery);
                    } catch (SQLException ex) {
                        out.println(""+ex+"");
                        Logger.getLogger(Register_Servlet.class.getName()).log(Level.SEVERE, null, ex);
                    }

                   RequestDispatcher rd = req.getRequestDispatcher("WelcomePage.html");
                   rd.forward(req, res); 
                }
                
            }
            else if(sub1.equals("Update"))
            {
                String memo_no= req.getParameter("Invno");
                String agency_name = req.getParameter("Aname");
                String company_name = req.getParameter("Cname");
                String product_name = req.getParameter("Pname");
                String product_mfg_date = req.getParameter("MFGDate");
                String product_expiry_date = req.getParameter("EXPDate");
                String product_price = req.getParameter("Pprice");
                String product_quantity = req.getParameter("Pquantity");
                String drug_location = req.getParameter("loc");

                HttpServletRequest request = (HttpServletRequest) req;
                HttpSession session=request.getSession(false);
                String em=(String)session.getAttribute("Memail");
                
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
                    Statement st = con.createStatement();
                    if(!product_name.equals("") && !agency_name.equals(""))
                    {
                        String sqlquery =  "update Drug_Details set agency_name='"+agency_name+"' where product_name='"+product_name+"'";
                        Integer rs = st.executeUpdate(sqlquery);                        
                    }
                    if(!product_name.equals("") && !company_name.equals(""))
                    {
                        String sqlquery =  "update Drug_Details set Company_name='"+company_name+"' where product_name='"+product_name+"'";
                        Integer rs = st.executeUpdate(sqlquery);                        
                    }
                    if(!product_name.equals("") && !product_name.equals(""))
                    {
                        String sqlquery =  "update Drug_Details set product_name='"+product_name+"' where product_name='"+product_name+"'";
                        Integer rs = st.executeUpdate(sqlquery);                        
                    }
                    if(!product_name.equals("") && !product_mfg_date.equals(""))
                    {
                        String sqlquery =  "update Drug_Details set product_mfg_date='"+product_mfg_date+"' where product_name='"+product_name+"'";
                        Integer rs = st.executeUpdate(sqlquery);                        
                    }
                    if(!product_name.equals("") && !product_expiry_date.equals(""))
                    {
                        String sqlquery =  "update Drug_Details set product_exp_date='"+product_expiry_date+"' where product_name='"+product_name+"'";
                        Integer rs = st.executeUpdate(sqlquery);                        
                    }
                    if(!product_name.equals("") && !product_price.equals(""))
                    {
                        String sqlquery =  "update Drug_Details set product_price='"+product_price+"' where product_name='"+product_name+"'";
                        Integer rs = st.executeUpdate(sqlquery);                        
                    }
                    if(!product_name.equals("") && !product_quantity.equals(""))
                    {
                        String sqlquery =  "update Drug_Details set product_quantity='"+product_quantity+"' where product_name='"+product_name+"'";
                        Integer rs = st.executeUpdate(sqlquery);                        
                    }
                    if(!product_name.equals("") && !drug_location.equals(""))
                    {
                        String sqlquery =  "update Drug_Details set drug_location='"+drug_location+"' where product_name='"+product_name+"'";
                        Integer rs = st.executeUpdate(sqlquery);                        
                    }

                } catch (SQLException ex) {
                    out.println(""+ex+"");
                    Logger.getLogger(Register_Servlet.class.getName()).log(Level.SEVERE, null, ex);
                }

               RequestDispatcher rd = req.getRequestDispatcher("WelcomePage.html");
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
                            HttpSession session=request.getSession();
                            session.setAttribute("Memail",em);
                            //session.setAttribute("Mpass",pwd);                            
                            RequestDispatcher rd1 = req.getRequestDispatcher("WelcomePage.html");
                            rd1.forward(req,res);
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
                        Logger.getLogger(add_company.class.getName()).log(Level.SEVERE, null, ex);
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
