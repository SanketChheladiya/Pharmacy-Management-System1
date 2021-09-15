package Pharmacy_Package;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
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

public class saleinvoice implements Servlet {

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
            String sub = req.getParameter("submit");
            if(sub.equals("Ganerate Bill"))
            {
                String jdbcurl = "jdbc:derby://localhost:1527/Pharmacy_System"; 
                    String driverclassname = "org.apache.derby.jdbc.ClientDriver";
                    String username = "Pharmacy_System";
                    String pword = "Pharmacy_System";

                    try{
                        Class cls = Class.forName(driverclassname);
                     
                     System.out.println("Class found = " + cls.getName());
                     System.out.println("Package = " + cls.getPackage());
                    }
                    catch(ClassNotFoundException ex) {
                     System.out.println(ex.toString());
                    }
                    try {
                        Connection con = DriverManager.getConnection(jdbcurl,username,pword);
                        Statement st = con.createStatement();
                        
                             String invoiceno = req.getParameter("invno");
                            String petadd = req.getParameter("Padd");
                            String petname = req.getParameter("Pname");
                             Calendar calendar = Calendar.getInstance();
                             java.sql.Date ourJavaDateObject = new java.sql.Date(calendar.getTime().getTime());
                             String purdate=String.valueOf(ourJavaDateObject);
                            String drname = req.getParameter("Dname");
                            String[] prodname = req.getParameterValues("proname[]");
                            String[] prodquantity = req.getParameterValues("proqu[]");
                            
                           
                            int i=0;

                            for(i=0;i<prodname.length;i++)
                            {
                                
                                String sqlquery1 = "Select * from Drug_details where product_name = '"+prodname[i]+"'";
                                java.sql.ResultSet rs2  =st.executeQuery(sqlquery1);
                                
                                int pr=0;
                                
                                while(rs2.next())
                                {
                                    String string1= rs2.getString("product_price");
                                    pr = Integer.parseInt(string1);
                                    
                                }
                                int qun = Integer.parseInt(prodquantity[i]);
                                String ppd = String.valueOf(pr);
                                int ppr = pr * qun;
                                String pprice = String.valueOf(ppr);
                                
                                String sqlquery = "insert into salesinfo(invno,purdate,petname,drname,prodname,prodquantity,prodprice,total_pp,petadd) values('"+invoiceno+"','"+purdate+"','"+petname+"','"+drname+"','"+prodname[i]+"',"+ "'"+prodquantity[i]+"','"+ppd+"','"+pprice+"','"+petadd+"')";
                                
                                Integer rs  =st.executeUpdate(sqlquery);
                                
                            }
                   
                String qu1 = "select * from salesInfo where invno='"+invoiceno+"'";         
                java.sql.ResultSet rs3  =st.executeQuery(qu1);
                String string1="",string2="",string3="",string4="";
                while(rs3.next())
                {
                    string1="";
                    string2="";
                    string3="";
                    string4="";
                    
                     string1= rs3.getString("invno");
                     string2= rs3.getString("purdate");
                     string3= rs3.getString("petname");
                     string4= rs3.getString("drname");
                    //String string5= rs3.getString("prodname");
                    //String string6= rs3.getString("prodquantity");
                    //String string7= rs3.getString("total_pp");                  
                }
               //from here
               out.println("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "  <head>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "    <title>Example 2</title>\n" +
                "    <link rel=\"stylesheet\" href=\"Bill_style.css\" media=\"all\" />\n" +
                "  </head>\n" +
                "  <body>\n" +
                "    <header class=\"clearfix\">\n" +
                "      <div id=\"logo\">\n" +
                "        <img src=\"logo.png\">\n" +
                "      </div>\n" +
                "      <div id=\"company\">\n" +
                "        <h2 class=\"name\">Shree Medicines</h2>\n" +
                "        <div>Bhaktinagar Circle,Rajkot,360002</div>\n" +
                "        <div>+91 9725945312</div>\n" +
                "        <div><a href=\"mailto:company@example.com\">shreemedicines@gmail.com</a></div>\n" +
                "      </div>\n" +
                "      </div>\n" +
                "    </header>\n" +
                       //done
                "    <main>\n" +
                "      <div id=\"details\" class=\"clearfix\">\n" +
                "        <div id=\"client\">\n" +
                "          <div class=\"to\">PATIENT INFO:</div>\n" +
                "          <h2 class=\"name\">"+string3+"</h2>\n" +
                "          <div class=\"address\">"+petadd+"</div>\n" +
                "          <div class=\"to\">DOCTOR NAME:</div>\n" +
                "          <h2 class=\"name\">"+string4+"</h2>\n" +
                "        </div>\n" +
                "        <div id=\"invoice\">\n" +
                "          <h1>INVOICE - "+string1+"</h1>\n" +
                "          <div class=\"date\">Date of Invoice: "+purdate+"</div>\n" +
                "        </div>\n" +
                "      </div>\n" +
                "      <table border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "        <thead>\n" +
                "          <tr>\n" +
                "            <th class=\"no\">SR NO.</th>\n" +
                "            <th class=\"desc\">PRODUCT NAME</th>\n" +
                "            <th class=\"unit\">PRICE PER ITEM</th>\n" +
                "            <th class=\"qty\">QUANTITY</th>\n" +
                "            <th class=\"total\">TOTAL</th>\n" +
                "          </tr>\n" +
                "        </thead>\n" +
                "        <tbody>");
                        //from here
                int x=0;
                String qu2 = "select * from salesInfo where invno='"+invoiceno+"'";         
                java.sql.ResultSet rs4  =st.executeQuery(qu2);
                int sum=0;
                while(rs4.next())
                {
                   
                    String string5= rs4.getString("prodname");
                    String string6= rs4.getString("prodquantity");
                    String string7= rs4.getString("total_pp");
                    String string8= rs4.getString("prodprice");
                    int tp = Integer.parseInt(string7);
                    sum = sum + tp;
                    x=x+1;
                
                out.println("<tr>\n" +
                "            <td class=\"no\">"+x+"</td>\n" +
                "            <td class=\"desc\"><h3>"+string5+"</h3></td>\n" +
                "            <td class=\"unit\">"+string8+"</td>\n" +
                "            <td class=\"qty\">"+string6+"</td>\n" +
                "            <td class=\"total\">"+string7+"</td>\n" +
                "          </tr>\n");
                 }
                int per = sum*5/100;
                int t = per + sum;
                out.println("</tbody>\n<tfoot>\n" +
                "          <tr>\n" +
                "            <td colspan=\"2\"></td>\n" + 
                "            <td colspan=\"2\">SUBTOTAL</td>\n" +
                "            <td>"+sum+"</td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td colspan=\"2\"></td>\n" +
                "            <td colspan=\"2\">TAX 5%</td>\n" +
                "            <td>"+per+"</td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td colspan=\"2\"></td>\n" +
                "            <td colspan=\"2\">GRAND TOTAL</td>\n" +
                "            <td>"+t+"</td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td colspan=\"2\"></td>\n" +
                "            <td colspan=\"2\"><button onclick=\"window.print()\">Print</button></td>\n" +

                "          </tr>\n" +
                "        </tfoot>\n" +
                "      </table>\n" +
                "      <div id=\"thanks\">Thank you!</div>\n" +
                "      <div id=\"notices\">\n" +
                "        <div>NOTICE:</div>\n" +
                "        <div class=\"notice\">A finance charge of 1.5% will be made on unpaid balances after 30 days.</div>\n" +
                "      </div>\n" +
                "    </main>\n" +
                "  </body>\n" +
                "</html>");
               
               } catch (SQLException ex) {
                        out.println(""+ex+"");
                        Logger.getLogger(saleinvoice.class.getName()).log(Level.SEVERE, null, ex);
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
