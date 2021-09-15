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

public class Drugs implements Servlet {

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
            out.println("<!DOCTYPE html>\n" +
"<html lang=\"en\">\n" +
"<head>\n" +
"    <link href=\"bootstrap.min.css\" rel=\"stylesheet\">\n" +
"    </head>\n" +
" <body>\n" +
"          <nav aria-label=\"breadcrumb\" class=\"main-breadcrumb\">\n" +
"            <ol class=\"breadcrumb\">\n" +
"              <li class=\"breadcrumb-item\"><a href=\"WelcomePage.html\">Home</a></li>\n" +
"              <li class=\"breadcrumb-item active\" aria-current=\"page\">Drugs</li>\n" +
"            </ol>\n" +
"          </nav>\n" +
"</body>\n" +
"</html>");
            String sub = req.getParameter("d");
            HttpServletRequest request = (HttpServletRequest) req;
            HttpSession session=request.getSession(false);
            String MEmail=(String)session.getAttribute("Memail");

            if(sub.equals("Add Drug"))
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
                        String sqlquery = "Select * from AddCompany where Memail='"+MEmail+"'";
                        java.sql.ResultSet rs  =st.executeQuery(sqlquery);
                        out.println("<!DOCTYPE html>");
                        out.println("<html>");
                        out.println("<head>");
                        out.println("<style>\n" +
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
                        "background-color: #f2f2f2;\n" +
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
                        out.println("<title>Add Drug</title>");
                     
                        out.println("</head>");
                        out.println("<body>");
                        while(rs.next())
                        {
          
                            String cname = rs.getString("Companyname");  
                            String cadd = rs.getString("Companyadd"); 
                            String cmob = rs.getString("Companyphno");  
                            String mem = rs.getString("Memail");
          
                        }
                        out.println("<h2>Add Drug Details</h2>\n" +
                        "<div class=\"container\">\n" +
                        "  <form action=\"Add_Drug\">\n" +
                        "    <div class=\"row\">\n" +
                        "      <div class=\"col-25\">\n" +
                        "        <label for=\"fname\">Product Invoice No.</label>\n" +
                        "      </div>\n" +
                        "      <div class=\"col-75\">\n" +
                        "        <input type=\"text\" id=\"fname\" name=\"Invno\" placeholder=\"Invoice No\">\n" +
                        "      </div>\n" +
                        "    </div>\n" +  
                        "    <div class=\"row\">\n" +
                        "      <div class=\"col-25\">\n" +
                        "        <label for=\"fname\">Agency Name</label>\n" +
                        "      </div>\n" +
                        "      <div class=\"col-75\">\n" +
                        "        <input type=\"text\" id=\"fname\" name=\"Aname\" placeholder=\"Agency name\">\n" +
                        "      </div>\n" +
                        "    </div>\n" +
                        "    <div class=\"row\">\n" +
                        "      <div class=\"col-25\">\n" +
                        "        <label for=\"fname\">Company Name</label>\n" +
                        "      </div>\n" +
                        "      <div class=\"col-75\">\n" +
                        "        <input type=\"text\" id=\"fname\" name=\"Cname\" placeholder=\"Company name\">\n" +
                        "      </div>\n" +
                        "    </div>\n" +  
                        "    <div class=\"row\">\n" +
                        "      <div class=\"col-25\">\n" +
                        "        <label for=\"fname\">Product Name</label>\n" +
                        "      </div>\n" +
                        "      <div class=\"col-75\">\n" +
                        "        <input type=\"text\" id=\"fname\" name=\"Pname\" placeholder=\"Product name\">\n" +
                        "      </div>\n" +
                        "    </div>\n" +
                        "    <div class=\"row\">\n" +
                        "      <div class=\"col-25\">\n" +
                        "        <label for=\"fname\">Product MFG Date</label>\n" +
                        "      </div>\n" +
                        "      <div class=\"col-75\">\n" +
                        "        <input type=\"text\" id=\"fname\" name=\"MFGDate\" placeholder=\"DD/MM/YYYY\">\n" +
                        "      </div>\n" +
                        "    </div>\n" +  
                        "    <div class=\"row\">\n" +
                        "      <div class=\"col-25\">\n" +
                        "        <label for=\"fname\">Product Expiry Date</label>\n" +
                        "      </div>\n" +
                        "      <div class=\"col-75\">\n" +
                        "        <input type=\"text\" id=\"fname\" name=\"EXPDate\" placeholder=\"DD/MM/YYYY\">\n" +
                        "      </div>\n" +
                        "      </div>\n" +        
                        "    <div class=\"row\">\n" +
                        "      <div class=\"col-25\">\n" +
                        "        <label for=\"fname\">Product Price</label>\n" +
                        "      </div>\n" +
                        "      <div class=\"col-75\">\n" +
                        "        <input type=\"number\" id=\"fname\" name=\"Pprice\" placeholder=\"Product Price\">\n" +
                        "      </div>\n" +
                        "    </div>\n" + 
                        "    <div class=\"row\">\n" +
                        "      <div class=\"col-25\">\n" +
                        "        <label for=\"fname\">Product Quantity</label>\n" +
                        "      </div>\n" +
                        "      <div class=\"col-75\">\n" +
                        "        <input type=\"number\" id=\"fname\" name=\"Pquantity\" placeholder=\"Product Quantity\">\n" +
                        "      </div>\n" +
                        "    </div>\n" + 
                        "    <div class=\"row\">\n" +
                        "      <div class=\"col-25\">\n" +
                        "        <label for=\"fname\">Product Location</label>\n" +
                        "      </div>\n" +
                        "      <div class=\"col-75\">\n" +
                        "        <input type=\"text\" id=\"fname\" name=\"loc\" placeholder=\"Product Location\">\n" +
                        "      </div>\n" +
                        "    </div>\n" +
                        "    <div class=\"row\">\n" +
                        "      <input type=\"submit\" name=\"sub\" value=\"Add\">\n" +
                        "    </div>\n" +
                        "  </form>\n" +
                        "</div>");

                    } catch (SQLException ex) {
                        out.println(""+ex+"");
                        Logger.getLogger(Register_Servlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    out.println("</body>");
                    out.println("</html>");
            }
            if(sub.equals("Delete Drug"))
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
                        String sqlquery = "Select * from AddCompany where Memail='"+MEmail+"'";
                        java.sql.ResultSet rs  =st.executeQuery(sqlquery);
                        out.println("<!DOCTYPE html>");
                        out.println("<html>");
                        out.println("<head>");
                        out.println("<style>\n" +
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
                        "background-color: #f2f2f2;\n" +
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
                        out.println("<title>Delete Drug</title>");
                     
                        out.println("</head>");
                        out.println("<body>");
                        while(rs.next())
                        {
          
                            String cname = rs.getString("Companyname");  
                            String cadd = rs.getString("Companyadd"); 
                            String cmob = rs.getString("Companyphno");  
                            String mem = rs.getString("Memail");
          
                        }
                        out.println("<h2>Delete Drug Details</h2>\n" +
                        "<div class=\"container\">\n" +
                        "  <form action=\"Add_Drug\">\n" +
                        "    <div class=\"row\">\n" +
                        "      <div class=\"col-25\">\n" +
                        "        <label for=\"fname\">Product Id No.</label>\n" +
                        "      </div>\n" +
                        "      <div class=\"col-75\">\n" +
                        "        <input type=\"text\" id=\"fname\" name=\"Invno\" placeholder=\"Id No\">\n" +
                        "      </div>\n" +
                        "    </div>\n" +  
                        "    <div class=\"row\">\n" +
                        "      <input type=\"submit\" name=\"sub\" value=\"Delete\">\n" +
                        "    </div>\n" +
                        "  </form>\n" +
                        "</div>");

                    } catch (SQLException ex) {
                        out.println(""+ex+"");
                        Logger.getLogger(Register_Servlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    out.println("</body>");
                    out.println("</html>");
            }
            else if(sub.equals("Update Drug"))
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
                        String sqlquery = "Select * from drug_details";
                        java.sql.ResultSet rs  =st.executeQuery(sqlquery);
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
                        " background-color: #f2f2f2;\n" +
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
                        " background-color: #f2f2f2;\n" +
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
                        out.println("<title>Update Drug</title>");
                     
                        out.println("<input type=\"text\" id=\"myInput\" onkeyup=\"myFunction()\" placeholder=\"Search by product name.....\" title=\"Type in a name\">"
                                + "<input type=\"text\" id=\"myInput1\" onkeyup=\"myFunction1()\" placeholder=\"Search by Company name.....\" title=\"Type in a name\"></head>");
                        out.println("<body>");
                        out.println("<table id=\"myTable\">");  
                        out.println("<tr class=\"header\"><th>Product Id</th><th>Company Name</th><th>Product Name</th><th>Product MFG Date</th><th>Product EXP Date</th><th>Product Price</th><th>Product Quantity</th><th>Agency name</th><th>Drug Location name</th><th>Drug Instoke</th><tr>");  
                        while(rs.next())
                        {
          
                            String memo_no = rs.getString("memo_no");  
                            String company_name = rs.getString("Company_name"); 
                            String product_name = rs.getString("product_name");  
                             String product_mfg_date = rs.getString("product_mfg_date");  
                            String product_exp_date = rs.getString("product_exp_date"); 
                            String product_price = rs.getString("product_price");  
                             String product_quantity= rs.getString("product_quantity");  
                            String agency_name = rs.getString("agency_name"); 
                             String drug_location= rs.getString("drug_location");  
                            String drugs_instock = rs.getString("drugs_instock"); 
          
                            out.println("<tr><td>" + memo_no + "</td><td>" + company_name + "</td><td>" + product_name + "</td><td>" + product_mfg_date + "</td><td>" + product_exp_date + "</td><td>" + product_price + "</td><td>" + product_quantity + "</td><td>" + agency_name + "</td><td>" + drug_location + "</td><td>" + drugs_instock + "</td></tr>");   
                        }
                          out.println("</table>");
                        

                    } catch (SQLException ex) {
                        out.println(""+ex+"");
                        Logger.getLogger(Register_Servlet.class.getName()).log(Level.SEVERE, null, ex);
                    }

                        out.println("<h2>Update Drug Details</h2>\n" +
                        "<div class=\"container\">\n" +
                        "  <form action=\"Add_Drug\">\n" +
                        "    <div class=\"row\">\n" +
                        "      <div class=\"col-25\">\n" +
                        "        <label for=\"fname\">Product Invoice No.</label>\n" +
                        "      </div>\n" +
                        "      <div class=\"col-75\">\n" +
                        "        <input type=\"text\" id=\"fname\" name=\"Invno\" placeholder=\"Invoice No\">\n" +
                        "      </div>\n" +
                        "    </div>\n" +  
                        "    <div class=\"row\">\n" +
                        "      <div class=\"col-25\">\n" +
                        "        <label for=\"fname\">Agency Name</label>\n" +
                        "      </div>\n" +
                        "      <div class=\"col-75\">\n" +
                        "        <input type=\"text\" id=\"fname\" name=\"Aname\" placeholder=\"Agency name\">\n" +
                        "      </div>\n" +
                        "    </div>\n" +
                        "    <div class=\"row\">\n" +
                        "      <div class=\"col-25\">\n" +
                        "        <label for=\"fname\">Company Name</label>\n" +
                        "      </div>\n" +
                        "      <div class=\"col-75\">\n" +
                        "        <input type=\"text\" id=\"fname\" name=\"Cname\" placeholder=\"Company name\">\n" +
                        "      </div>\n" +
                        "    </div>\n" +  
                        "    <div class=\"row\">\n" +
                        "      <div class=\"col-25\">\n" +
                        "        <label for=\"fname\">Product Name</label>\n" +
                        "      </div>\n" +
                        "      <div class=\"col-75\">\n" +
                        "        <input type=\"text\" id=\"fname\" name=\"Pname\" placeholder=\"Product name\">\n" +
                        "      </div>\n" +
                        "    </div>\n" +
                        "    <div class=\"row\">\n" +
                        "      <div class=\"col-25\">\n" +
                        "        <label for=\"fname\">Product MFG Date</label>\n" +
                        "      </div>\n" +
                        "      <div class=\"col-75\">\n" +
                        "        <input type=\"text\" id=\"fname\" name=\"MFGDate\" placeholder=\"DD/MM/YYYY\">\n" +
                        "      </div>\n" +
                        "    </div>\n" +  
                        "    <div class=\"row\">\n" +
                        "      <div class=\"col-25\">\n" +
                        "        <label for=\"fname\">Product Expiry Date</label>\n" +
                        "      </div>\n" +
                        "      <div class=\"col-75\">\n" +
                        "        <input type=\"text\" id=\"fname\" name=\"EXPDate\" placeholder=\"DD/MM/YYYY\">\n" +
                        "      </div>\n" +
                        "      </div>\n" +        
                        "    <div class=\"row\">\n" +
                        "      <div class=\"col-25\">\n" +
                        "        <label for=\"fname\">Product Price</label>\n" +
                        "      </div>\n" +
                        "      <div class=\"col-75\">\n" +
                        "        <input type=\"number\" id=\"fname\" name=\"Pprice\" placeholder=\"Product Price\">\n" +
                        "      </div>\n" +
                        "    </div>\n" + 
                        "    <div class=\"row\">\n" +
                        "      <div class=\"col-25\">\n" +
                        "        <label for=\"fname\">Product Quantity</label>\n" +
                        "      </div>\n" +
                        "      <div class=\"col-75\">\n" +
                        "        <input type=\"number\" id=\"fname\" name=\"Pquantity\" placeholder=\"Product Quantity\">\n" +
                        "      </div>\n" +
                        "    </div>\n" + 
                        "    <div class=\"row\">\n" +
                        "      <div class=\"col-25\">\n" +
                        "        <label for=\"fname\">Product Location</label>\n" +
                        "      </div>\n" +
                        "      <div class=\"col-75\">\n" +
                        "        <input type=\"text\" id=\"fname\" name=\"loc\" placeholder=\"Product Location\">\n" +
                        "      </div>\n" +
                        "    </div>\n" + 
                        "    <div class=\"row\">\n" +
                        "      <input type=\"submit\" name=\"sub\" value=\"Update\">\n" +
                        "    </div>\n" +
                        "  </form>\n" +
                        "</div>");
                    out.println("</body>");
                                        out.println("<script>\n" +
                    "function myFunction() {\n" +
                    "  var input, filter, table, tr, td, i, txtValue;\n" +
                    "  input = document.getElementById(\"myInput\");\n" +
                    "  filter = input.value.toUpperCase();\n" +
                    "  table = document.getElementById(\"myTable\");\n" +
                    "  tr = table.getElementsByTagName(\"tr\");\n" +
                    "  for (i = 0; i < tr.length; i++) {\n" +
                    "    td = tr[i].getElementsByTagName(\"td\")[2];\n" +
                    "    if (td) {\n" +
                    "      txtValue = td.textContent || td.innerText;\n" +
                    "      if (txtValue.toUpperCase().indexOf(filter) > -1) {\n" +
                    "        tr[i].style.display = \"\";\n" +
                    "      } else {\n" +
                    "        tr[i].style.display = \"none\";\n" +
                    "      }\n" +
                    "    }       \n" +
                    "  }\n" +
                    "}\nfunction myFunction1() {\n" +
                    "  var input, filter, table, tr, td, i, txtValue;\n" +
                    "  input = document.getElementById(\"myInput1\");\n" +
                    "  filter = input.value.toUpperCase();\n" +
                    "  table = document.getElementById(\"myTable\");\n" +
                    "  tr = table.getElementsByTagName(\"tr\");\n" +
                    "  for (i = 0; i < tr.length; i++) {\n" +
                    "    td = tr[i].getElementsByTagName(\"td\")[1];\n" +
                    "    if (td) {\n" +
                    "      txtValue = td.textContent || td.innerText;\n" +
                    "      if (txtValue.toUpperCase().indexOf(filter) > -1) {\n" +
                    "        tr[i].style.display = \"\";\n" +
                    "      } else {\n" +
                    "        tr[i].style.display = \"none\";\n" +
                    "      }\n" +
                    "    }       \n" +
                    "  }\n" +
                    "}" +
                    "</script>");

                    out.println("</html>");
            }
            else if(sub.equals("Drug List"))
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
                        String sqlquery = "Select * from Drug_details";
                        java.sql.ResultSet rs  =st.executeQuery(sqlquery);
                        out.println("<!DOCTYPE html>");
                        out.println("<html>");
                        out.println("<head>");
                        out.println("<style>\n" +
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
                        " background-color: #f2f2f2;\n" +
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
                        out.println("<title>Drug List</title>");
                     
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<table id=\"customers\">");  
                        out.println("<tr><th>Product Id</th><th>Company Name</th><th>Product Name</th><th>Product MFG Date</th><th>Product EXP Date</th><th>Product Price</th><th>Product Quantity</th><th>Agency name</th><th>Drug Location name</th><th>Drug Instoke</th><tr>");  
                        while(rs.next())
                        {
          
                            String memo_no = rs.getString("memo_no");  
                            String company_name = rs.getString("Company_name"); 
                            String product_name = rs.getString("product_name");  
                             String product_mfg_date = rs.getString("product_mfg_date");  
                            String product_exp_date = rs.getString("product_exp_date"); 
                            String product_price = rs.getString("product_price");  
                             String product_quantity= rs.getString("product_quantity");  
                            String agency_name = rs.getString("agency_name"); 
                             String drug_location= rs.getString("drug_location");  
                            String drugs_instock = rs.getString("drugs_instock"); 
          
                            out.println("<tr><td>" + memo_no + "</td><td>" + company_name + "</td><td>" + product_name + "</td><td>" + product_mfg_date + "</td><td>" + product_exp_date + "</td><td>" + product_price + "</td><td>" + product_quantity + "</td><td>" + agency_name + "</td><td>" + drug_location + "</td><td>" + drugs_instock + "</td></tr>");   
                        }
                          out.println("</table>");
                        

                    } catch (SQLException ex) {
                        out.println(""+ex+"");
                        Logger.getLogger(Register_Servlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                  
                    out.println("</body>");
                    out.println("</html>");    
            }
            else if(sub.equals("Search Drug"))
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
                        String sqlquery = "Select * from Drug_details";
                        java.sql.ResultSet rs  =st.executeQuery(sqlquery);
                        
                        
                        out.println("<!DOCTYPE html>");
                        out.println("<html>");
                        out.println("<head><input type=\"text\" id=\"myInput\" onkeyup=\"myFunction()\" placeholder=\"Search by product name.....\" title=\"Type in a name\">"
                                + "<input type=\"text\" id=\"myInput1\" onkeyup=\"myFunction1()\" placeholder=\"Search by Company name.....\" title=\"Type in a name\">");
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
                        "background-color: #f2f2f2;\n" +
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
                        out.println("<title>Drug List</title>");
                     
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<table id=\"myTable\">");  
                        out.println("<tr class=\"header\"><th>Product Id</th><th>Company Name</th><th>Product Name</th><th>Product MFG Date</th><th>Product EXP Date</th><th>Product Price</th><th>Product Quantity</th><th>Agency name</th><th>Drug Location name</th><th>Drug Instoke</th><tr>");  
                        while(rs.next())
                        {
          
                            String memo_no = rs.getString("memo_no");  
                            String company_name = rs.getString("Company_name"); 
                            String product_name = rs.getString("product_name");  
                             String product_mfg_date = rs.getString("product_mfg_date");  
                            String product_exp_date = rs.getString("product_exp_date"); 
                            String product_price = rs.getString("product_price");  
                             String product_quantity= rs.getString("product_quantity");  
                            String agency_name = rs.getString("agency_name"); 
                             String drug_location= rs.getString("drug_location");  
                            String drugs_instock = rs.getString("drugs_instock"); 
          
                            out.println("<tr><td>" + memo_no + "</td><td>" + company_name + "</td><td>" + product_name + "</td><td>" + product_mfg_date + "</td><td>" + product_exp_date + "</td><td>" + product_price + "</td><td>" + product_quantity + "</td><td>" + agency_name + "</td><td>" + drug_location + "</td><td>" + drugs_instock + "</td></tr>");   
                        }
                          out.println("</table>");
                        

                    } catch (SQLException ex) {
                        out.println(""+ex+"");
                        Logger.getLogger(Register_Servlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                  
                    out.println("</body>");
                    out.println("<script>\n" +
                    "function myFunction() {\n" +
                    "  var input, filter, table, tr, td, i, txtValue;\n" +
                    "  input = document.getElementById(\"myInput\");\n" +
                    "  filter = input.value.toUpperCase();\n" +
                    "  table = document.getElementById(\"myTable\");\n" +
                    "  tr = table.getElementsByTagName(\"tr\");\n" +
                    "  for (i = 0; i < tr.length; i++) {\n" +
                    "    td = tr[i].getElementsByTagName(\"td\")[2];\n" +
                    "    if (td) {\n" +
                    "      txtValue = td.textContent || td.innerText;\n" +
                    "      if (txtValue.toUpperCase().indexOf(filter) > -1) {\n" +
                    "        tr[i].style.display = \"\";\n" +
                    "      } else {\n" +
                    "        tr[i].style.display = \"none\";\n" +
                    "      }\n" +
                    "    }       \n" +
                    "  }\n" +
                    "}\nfunction myFunction1() {\n" +
                    "  var input, filter, table, tr, td, i, txtValue;\n" +
                    "  input = document.getElementById(\"myInput1\");\n" +
                    "  filter = input.value.toUpperCase();\n" +
                    "  table = document.getElementById(\"myTable\");\n" +
                    "  tr = table.getElementsByTagName(\"tr\");\n" +
                    "  for (i = 0; i < tr.length; i++) {\n" +
                    "    td = tr[i].getElementsByTagName(\"td\")[1];\n" +
                    "    if (td) {\n" +
                    "      txtValue = td.textContent || td.innerText;\n" +
                    "      if (txtValue.toUpperCase().indexOf(filter) > -1) {\n" +
                    "        tr[i].style.display = \"\";\n" +
                    "      } else {\n" +
                    "        tr[i].style.display = \"none\";\n" +
                    "      }\n" +
                    "    }       \n" +
                    "  }\n" +
                    "}" +
                    "</script>");
                    out.println("</html>");    
            }
            else if(sub.equals("Expired Drug"))
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
                        String sqlquery = "Select * from Drug_details";
                        java.sql.ResultSet rs  =st.executeQuery(sqlquery);
                        
                        Calendar calendar = Calendar.getInstance();
                        java.sql.Date ourJavaDateObject = new java.sql.Date(calendar.getTime().getTime());
                        String Curr_Date=String.valueOf(ourJavaDateObject);
                        //out.println("<h1>"+Curr_Date+"</h1>");
                        String day="";
                        day+=Curr_Date.charAt(8);
                        day+=Curr_Date.charAt(9);
                        
                        String month="";
                        month+=Curr_Date.charAt(5);
                        month+=Curr_Date.charAt(6);
                        
                        String year="";
                        year+=Curr_Date.charAt(0);
                        year+=Curr_Date.charAt(1);
                        year+=Curr_Date.charAt(2);
                        year+=Curr_Date.charAt(3);
                        
                        
                        out.println("<!DOCTYPE html>");
                        out.println("<html>");
                        out.println("<head><input type=\"text\" id=\"myInput\" onkeyup=\"myFunction()\" placeholder=\"Search by product name.....\" title=\"Type in a name\">"
                                + "<input type=\"text\" id=\"myInput1\" onkeyup=\"myFunction1()\" placeholder=\"Search by Agency name.....\" title=\"Type in a name\">");
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
                        "background-color: #f2f2f2;\n" +
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
                        out.println("<title>Drug List</title>");
                     
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<table id=\"customers\">");  
                        out.println("<tr><th>Product Id</th><th>Company Name</th><th>Product Name</th><th>Product MFG Date</th><th>Product EXP Date</th><th>Product Price</th><th>Product Quantity</th><th>Agency name</th><th>Drug Location name</th><th>Drug Instoke</th><tr>");  
                        while(rs.next())
                        {
          
                            String memo_no = rs.getString("memo_no");  
                            String company_name = rs.getString("Company_name"); 
                            String product_name = rs.getString("product_name");  
                            String product_mfg_date = rs.getString("product_mfg_date");  
                            String product_exp_date = rs.getString("product_exp_date"); 
                            String product_price = rs.getString("product_price");  
                            String product_quantity= rs.getString("product_quantity");  
                            String agency_name = rs.getString("agency_name"); 
                            String drug_location= rs.getString("drug_location");  
                            String drugs_instock = rs.getString("drugs_instock"); 

                            String daye="";
                            daye+=product_exp_date.charAt(0);
                            daye+=product_exp_date.charAt(1);

                            String monthe="";
                            monthe+=product_exp_date.charAt(3);
                            monthe+=product_exp_date.charAt(4);

                            String yeare="";
                            yeare+=product_exp_date.charAt(6);
                            yeare+=product_exp_date.charAt(7);
                            yeare+=product_exp_date.charAt(8);
                            yeare+=product_exp_date.charAt(9);
                            
                            int yearc = Integer.parseInt(year);
                            int yearp = Integer.parseInt(yeare);
                            
                            int dayc = Integer.parseInt(day);
                            int dayp = Integer.parseInt(daye);
                            
                            int monthc = Integer.parseInt(month);
                            int monthp = Integer.parseInt(monthe);
                            
                          
                            
                            if(yearc > yearp)
                            {
                                out.println("<tr><td>" + memo_no + "</td><td>" + company_name + "</td><td>" + product_name + "</td><td>" + product_mfg_date + "</td><td>" + product_exp_date + "</td><td>" + product_price + "</td><td>" + product_quantity + "</td><td>" + agency_name + "</td><td>" + drug_location + "</td><td>" + drugs_instock + "</td></tr>");
                            }
                            else if(yearc == yearp)
                            {
                                if(monthc > monthp)
                                {
                                    out.println("<tr><td>" + memo_no + "</td><td>" + company_name + "</td><td>" + product_name + "</td><td>" + product_mfg_date + "</td><td>" + product_exp_date + "</td><td>" + product_price + "</td><td>" + product_quantity + "</td><td>" + agency_name + "</td><td>" + drug_location + "</td><td>" + drugs_instock + "</td></tr>");
                                }
                                else if(monthc == monthp)
                                {
                                    if(dayc > dayp)
                                    {
                                        out.println("<tr><td>" + memo_no + "</td><td>" + company_name + "</td><td>" + product_name + "</td><td>" + product_mfg_date + "</td><td>" + product_exp_date + "</td><td>" + product_price + "</td><td>" + product_quantity + "</td><td>" + agency_name + "</td><td>" + drug_location + "</td><td>" + drugs_instock + "</td></tr>");
                                    }
                                }
                            }  
                        }
                          out.println("</table>");
                        

                    } catch (SQLException ex) {
                        out.println(""+ex+"");
                        Logger.getLogger(Register_Servlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                  
                    out.println("</body>");
                    out.println("<script>\n" +
                    "function myFunction() {\n" +
                    "  var input, filter, table, tr, td, i, txtValue;\n" +
                    "  input = document.getElementById(\"myInput\");\n" +
                    "  filter = input.value.toUpperCase();\n" +
                    "  table = document.getElementById(\"customers\");\n" +
                    "  tr = table.getElementsByTagName(\"tr\");\n" +
                    "  for (i = 0; i < tr.length; i++) {\n" +
                    "    td = tr[i].getElementsByTagName(\"td\")[2];\n" +
                    "    if (td) {\n" +
                    "      txtValue = td.textContent || td.innerText;\n" +
                    "      if (txtValue.toUpperCase().indexOf(filter) > -1) {\n" +
                    "        tr[i].style.display = \"\";\n" +
                    "      } else {\n" +
                    "        tr[i].style.display = \"none\";\n" +
                    "      }\n" +
                    "    }       \n" +
                    "  }\n" +
                    "}\nfunction myFunction1() {\n" +
                    "  var input, filter, table, tr, td, i, txtValue;\n" +
                    "  input = document.getElementById(\"myInput1\");\n" +
                    "  filter = input.value.toUpperCase();\n" +
                    "  table = document.getElementById(\"customers\");\n" +
                    "  tr = table.getElementsByTagName(\"tr\");\n" +
                    "  for (i = 0; i < tr.length; i++) {\n" +
                    "    td = tr[i].getElementsByTagName(\"td\")[7];\n" +
                    "    if (td) {\n" +
                    "      txtValue = td.textContent || td.innerText;\n" +
                    "      if (txtValue.toUpperCase().indexOf(filter) > -1) {\n" +
                    "        tr[i].style.display = \"\";\n" +
                    "      } else {\n" +
                    "        tr[i].style.display = \"none\";\n" +
                    "      }\n" +
                    "    }       \n" +
                    "  }\n" +
                    "}" +
                    "</script>");
                    out.println("</html>"); 
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
