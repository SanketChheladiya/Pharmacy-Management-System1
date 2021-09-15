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

public class bill_info implements Servlet {

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
            String sub = req.getParameter("sub");
            if(sub.equals("GET"))
            {
                    out.println("<!DOCTYPE html>");
                        out.println("<html>");
                        out.println("<head>");
                        out.println("<style>\n" +
                        "* {\n" +
                        "  box-sizing: border-box;\n" +
                        "}\n" +
                        "\n" +
                        "#myInput {\n" +
                        "  background-image: url('/css/searchicon.png');\n" +
                        "  background-position: 10px 10px;\n" +
                        "  background-repeat: no-repeat;\n" +
                        "  width: 100%;\n" +
                        "  font-size: 16px;\n" +
                        "  padding: 12px 20px 12px 40px;\n" +
                        "  border: 1px solid #ddd;\n" +
                        "  margin-bottom: 12px;\n" +
                        "}\n" +
                        "\n" +
                        "#myInput1 {\n" +
                        "  background-image: url('/css/searchicon.png');\n" +
                        "  background-position: 10px 10px;\n" +
                        "  background-repeat: no-repeat;\n" +
                        "  width: 100%;\n" +
                        "  font-size: 16px;\n" +
                        "  padding: 12px 20px 12px 40px;\n" +
                        "  border: 1px solid #ddd;\n" +
                        "  margin-bottom: 12px;\n" +
                        "}\n" +
                        "\n" +

                                "#myTable {\n" +
                        "  border-collapse: collapse;\n" +
                        "  width: 100%;\n" +
                        "  border: 1px solid #ddd;\n" +
                        "  font-size: 18px;\n" +
                        "}\n" +
                        "\n" +
                        "#myTable th, #myTable td {\n" +
                        "  text-align: left;\n" +
                        "  padding: 12px;\n" +
                        "}\n" +
                        "\n" +
                        "#myTable tr {\n" +
                        "  border-bottom: 1px solid #ddd;\n" +
                        "}\n" +
                        "\n" +
                        "#myTable tr.header, #myTable tr:hover {\n" +
                        "  background-color: #f1f1f1;\n" +
                        "}#customers {\n" +
                        "  font-family: Arial, Helvetica, sans-serif;\n" +
                        "  border-collapse: collapse;\n" +
                        "  width: 100%;\n" +
                        "}\n" +
                        "\n" +
                        "#customers td, #customers th {\n" +
                        "  border: 1px solid black;\n" +
                        "  padding: 8px;\n" +
                        "}\n" +
                        "\n" +
                        "#customers tr:nth-child(even){background-color: #f2f2f2;}\n" +
                        "\n" +
                        "#customers tr:hover {background-color: #ddd;}\n" +
                        "\n" +
                        "#customers th {\n" +
                        "  padding-top: 12px;\n" +
                        "  padding-bottom: 12px;\n" +
                        "  text-align: left;\n" +
                        "  background-color: #4CAF50;\n" +
                        "  color: white;\n" +
                        "}\n" +
                        "body {\n" +
                        " background-image: url(\"capsule.pill.health.medicine (1).jpg\");\n" +
                        " background-color:none;\n" +
                        "background-size:auto;}\n" +
                        "* {\n" +
                        "  box-sizing: border-box;\n" +
                        "}\n" +
                        "\n" +
                        "input[type=text], select, textarea {\n" +
                        "  width: 100%;\n" +
                        "  padding: 12px;\n" +
                        "  border: 1px solid #ccc;\n" +
                        "  border-radius: 4px;\n" +
                        "  resize: vertical;\n" +
                        "}\n" +
                        "input[type=number], select, textarea {\n" +
                        "  width: 100%;\n" +
                        "  padding: 12px;\n" +
                        "  border: 1px solid #ccc;\n" +
                        "  border-radius: 4px;\n" +
                        "  resize: vertical;\n" +
                        "}\n" +

                                "\n" +
                        "label {\n" +
                        "  padding: 12px 12px 12px 0;\n" +
                        "  display: inline-block;\n" +
                        "}\n" +
                        "\n" +
                        "input[type=submit] {\n" +
                        "  background-color: #4CAF50;\n" +
                        "  color: white;\n" +
                        "  padding: 12px 20px;\n" +
                        "  border: none;\n" +
                        "  border-radius: 4px;\n" +
                        "  cursor: pointer;\n" +
                        "  float: right;\n" +
                        "}\n" +
                        "\n" +
                        "input[type=submit]:hover {\n" +
                        "  background-color: #45a049;\n" +
                        "}\n" +
                        "\n" +
                        ".container {\n" +
                        "  border-radius: 5px;\n" +
                        "background-image: url(\"paper1.jpg\");  background-color: #f2f2f2;\n" +
                        "  padding: 20px;\n" +
                        "}\n" +
                        "\n" +
                        ".col-25 {\n" +
                        "  float: left;\n" +
                        "  width: 25%;\n" +
                        "  margin-top: 6px;\n" +
                        "}\n" +
                        "\n" +
                        ".col-75 {\n" +
                        "  float: left;\n" +
                        "  width: 75%;\n" +
                        "  margin-top: 6px;\n" +
                        "}\n" +
                        "\n" +
                        "/* Clear floats after the columns */\n" +
                        ".row:after {\n" +
                        "  content: \"\";\n" +
                        "  display: table;\n" +
                        "  clear: both;\n" +
                        "}\n" +
                        "\n" +
                        "/* Responsive layout - when the screen is less than 600px wide, make the two columns stack on top of each other instead of next to each other */\n" +
                        "@media screen and (max-width: 600px) {\n" +
                        "  .col-25, .col-75, input[type=submit] {\n" +
                        "    width: 100%;\n" +
                        "    margin-top: 0;\n" +
                        "  }\n" +
                        "}\n" +
                        "#customers {\n" +
                        "  font-family: Arial, Helvetica, sans-serif;\n" +
                        "  border-collapse: collapse;\n" +
                        "  width: 100%;\n" +
                        "}\n" +
                        "\n" +
                        "#customers td, #customers th {\n" +
                        "  border: 1px solid black;\n" +
                        "  padding: 8px;\n" +
                        "}\n" +
                        "\n" +
                        "#customers tr:nth-child(even){background-color: #f2f2f2;}\n" +
                        "\n" +
                        "#customers tr:hover {background-color: #ddd;}\n" +
                        "\n" +
                        "#customers th {\n" +
                        "  padding-top: 12px;\n" +
                        "  padding-bottom: 12px;\n" +
                        "  text-align: left;\n" +
                        "  background-color: #4CAF50;\n" +
                        "  color: white;\n" +
                        "}\n" +
                        "body {\n" +
                        " background-image: url(\"capsule.pill.health.medicine (1).jpg\");\n" +
                        " background-color:none;\n" +
                        "background-size:auto;}\n" +
                        "* {\n" +
                        "  box-sizing: border-box;\n" +
                        "}\n" +
                        "\n" +
                        "input[type=text], select, textarea {\n" +
                        "  width: 100%;\n" +
                        "  padding: 12px;\n" +
                        "  border: 1px solid #ccc;\n" +
                        "  border-radius: 4px;\n" +
                        "  resize: vertical;\n" +
                        "}\n" +
                        "input[type=number], select, textarea {\n" +
                        "  width: 100%;\n" +
                        "  padding: 12px;\n" +
                        "  border: 1px solid #ccc;\n" +
                        "  border-radius: 4px;\n" +
                        "  resize: vertical;\n" +
                        "}\n" +

                                "\n" +
                        "label {\n" +
                        "  padding: 12px 12px 12px 0;\n" +
                        "  display: inline-block;\n" +
                        "}\n" +
                        "\n" +
                        "input[type=submit] {\n" +
                        "  background-color: #4CAF50;\n" +
                        "  color: white;\n" +
                        "  padding: 12px 20px;\n" +
                        "  border: none;\n" +
                        "  border-radius: 4px;\n" +
                        "  cursor: pointer;\n" +
                        "  float: right;\n" +
                        "}\n" +
                        "\n" +
                        "input[type=submit]:hover {\n" +
                        "  background-color: #45a049;\n" +
                        "}\n" +
                        "\n" +
                        ".container {\n" +
                        "  border-radius: 5px;\n" +
                        "background-image: url(\"paper1.jpg\");  background-color: #f2f2f2;\n" +
                        "  padding: 20px;\n" +
                        "}\n" +
                        "\n" +
                        ".col-25 {\n" +
                        "  float: left;\n" +
                        "  width: 25%;\n" +
                        "  margin-top: 6px;\n" +
                        "}\n" +
                        "\n" +
                        ".col-75 {\n" +
                        "  float: left;\n" +
                        "  width: 75%;\n" +
                        "  margin-top: 6px;\n" +
                        "}\n" +
                        "\n" +
                        "/* Clear floats after the columns */\n" +
                        ".row:after {\n" +
                        "  content: \"\";\n" +
                        "  display: table;\n" +
                        "  clear: both;\n" +
                        "}\n" +
                        "\n" +
                        "/* Responsive layout - when the screen is less than 600px wide, make the two columns stack on top of each other instead of next to each other */\n" +
                        "@media screen and (max-width: 600px) {\n" +
                        "  .col-25, .col-75, input[type=submit] {\n" +
                        "    width: 100%;\n" +
                        "    margin-top: 0;\n" +
                        "  }\n" +
                        "}\n" +
                        "</style>");
                    String no = req.getParameter("fno");
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
                     
                     System.out.println("Class found = " + cls.getName());
                     System.out.println("Package = " + cls.getPackage());
                    }
                    catch(ClassNotFoundException ex) {
                     System.out.println(ex.toString());
                    }
                    try {
                        Connection con = DriverManager.getConnection(jdbcurl,username,pword);
                        Statement st = con.createStatement();
                        String sql1 = "select * from salesinfo where invno='"+no+"'";
                        java.sql.ResultSet rs  =st.executeQuery(sql1);
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<table id=\"customers\">");  
                        out.println("<tr><th>Invoice No.</th><th>Petient name</th><th>Doctor Name</th><th>Drug name</th><th>Drug Quantity</th><th>Drug Price</th><th>Total Price</th><tr>");  
                        int sum =0;
                        while(rs.next())
                        {
                            String invno = rs.getString("invno");
                            String pname = rs.getString("petname");
                            String dname = rs.getString("drname");
                            String prodname = rs.getString("prodname");
                            String prodquantity = rs.getString("prodquantity");
                            String prodprice = rs.getString("prodprice");
                            String total_pp = rs.getString("total_pp");
                            int t = Integer.parseInt(total_pp);
                            sum = sum + t;
                             out.println("<tr><td>" + invno + "</td><td>" + pname + "</td><td>" + dname + "</td><td>" + prodname + "</td><td>" + prodquantity + "</td><td>" + prodprice + "</td><td>" + total_pp + "</td></tr>");
                            
                            //add here
                        }
                        out.println("</table>");
                        out.println("<h1>Total Purchase:&emsp;<input type=\"text\" value=\""+sum+"\" class=\"form-control\" readonly/></h1>");
                        out.println("</body>");
                        out.println("</html>");
               
                    } catch (SQLException ex) {
                        out.println(""+ex+"");
                        Logger.getLogger(bill_info.class.getName()).log(Level.SEVERE, null, ex);
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
