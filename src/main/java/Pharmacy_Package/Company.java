package Pharmacy_Package;


import java.io.IOException;
import java.io.PrintWriter;
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

/**
 *
 * @author admin
 */
public class Company implements Servlet {

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
"              <li class=\"breadcrumb-item active\" aria-current=\"page\">Company</li>\n" +
"            </ol>\n" +
"          </nav>\n" +
"</body>\n" +
"</html>");
            String sub = req.getParameter("sum");
            //out.println(sub);
            HttpServletRequest request = (HttpServletRequest) req;
            HttpSession session=request.getSession(false);
            String MEmail=(String)session.getAttribute("Memail");
            //out.println(MEmail);
            if(sub.equals("Add_Company"))
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
                        "background-color: lightyellow;\n" +
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
                        out.println("<title>Add Company</title>");
                     
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<table id=\"customers\">");  
                        out.println("<tr><th>Company Name</th><th>Company Address</th><th>Company Mobile No.</th><th>Medical Email</th><tr>");  
                        while(rs.next())
                        {
          
                            String cname = rs.getString("Companyname");  
                            String cadd = rs.getString("Companyadd"); 
                            String cmob = rs.getString("Companyphno");  
                            String mem = rs.getString("Memail");
          
                            out.println("<tr><td>" + cname + "</td><td>" + cadd + "</td><td>" + cmob + "</td><td>" + mem + "</td></tr>");   
                        }
                          out.println("</table>");
                        out.println("<h2>Add Company Details</h2>\n" +
                        "<div class=\"container\">\n" +
                        "  <form action=\"add_company\">\n" +
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
                        "        <label for=\"lname\">Company Mobile No</label>\n" +
                        "      </div>\n" +
                        "      <div class=\"col-75\">\n" +
                        "        <input type=\"number\" id=\"lname\" name=\"Cmo\" placeholder=\"Company phone number\">\n" +
                        "      </div>\n" +
                        "    </div>\n" +
                        "    <div class=\"row\">\n" +
                        "      <div class=\"col-25\">\n" +
                        "        <label for=\"subject\">Address</label>\n" +
                        "      </div>\n" +
                        "      <div class=\"col-75\">\n" +
                        "        <textarea id=\"subject\" name=\"Cadd\" placeholder=\"Company Address\" style=\"height:200px\"></textarea>\n" +
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
            else if(sub.equals("Delete_Company"))
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
                        "background-color: lightyellow;\n" +
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
                        out.println("<title>Add Company</title>");
                     
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<table id=\"customers\">");  
                        out.println("<tr><th>Company Name</th><th>Company Address</th><th>Company Mobile No.</th><th>Medical Email</th><tr>");  
                        while(rs.next())
                        {
          
                            String cname = rs.getString("Companyname");  
                            String cadd = rs.getString("Companyadd"); 
                            String cmob = rs.getString("Companyphno");  
                            String mem = rs.getString("Memail");
          
                            out.println("<tr><td>" + cname + "</td><td>" + cadd + "</td><td>" + cmob + "</td><td>" + mem + "</td></tr>");   
                        }
                          out.println("</table>");
                        out.println("<h2>Delete Company</h2>\n" +
                        "<div class=\"container\">\n" +
                        "  <form action=\"add_company\">\n" +
                        "    <div class=\"row\">\n" +
                        "      <div class=\"col-25\">\n" +
                        "        <label for=\"fname\">Company Name</label>\n" +
                        "      </div>\n" +
                        "      <div class=\"col-75\">\n" +
                        "        <input type=\"text\" id=\"fname\" name=\"Cname\" placeholder=\"Company name\">\n" +
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
            else if(sub.equals("Update_Company"))
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
                        " background-color: lightyellow;\n" +
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
                        out.println("<title>Add Company</title>");
                     
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<table id=\"customers\">");  
                        out.println("<tr><th>Company Name</th><th>Company Address</th><th>Company Mobile No.</th><th>Medical Email</th><tr>");  
                        while(rs.next())
                        {
          
                            String cname = rs.getString("Companyname");  
                            String cadd = rs.getString("Companyadd"); 
                            String cmob = rs.getString("Companyphno");  
                            String mem = rs.getString("Memail");
          
                            out.println("<tr><td>" + cname + "</td><td>" + cadd + "</td><td>" + cmob + "</td><td>" + mem + "</td></tr>");   
                        }
                          out.println("</table>");
                        out.println("<h2>Update Company Details</h2>\n" +
                        "<div class=\"container\">\n" +
                        "  <form action=\"add_company\">\n" +
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
                        "        <label for=\"lname\">Company Mobile No</label>\n" +
                        "      </div>\n" +
                        "      <div class=\"col-75\">\n" +
                        "        <input type=\"number\" id=\"lname\" name=\"Cmo\" placeholder=\"Company phone number\">\n" +
                        "      </div>\n" +
                        "    </div>\n" +
                        "    <div class=\"row\">\n" +
                        "      <div class=\"col-25\">\n" +
                        "        <label for=\"subject\">Address</label>\n" +
                        "      </div>\n" +
                        "      <div class=\"col-75\">\n" +
                        "        <textarea id=\"subject\" name=\"Cadd\" placeholder=\"Company Address\" style=\"height:200px\"></textarea>\n" +
                        "      </div>\n" +
                        "    </div>\n" +
                        "    <div class=\"row\">\n" +
                        "      <input type=\"submit\" name=\"sub\" value=\"Update\">\n" +
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
