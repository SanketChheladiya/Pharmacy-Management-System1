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

public class Sales implements Servlet {

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
            out.println("<!DOCTYPE html>\n" +
"<html lang=\"en\">\n" +
"<head>\n" +
"    <link href=\"bootstrap.min.css\" rel=\"stylesheet\">\n" +
"    </head>\n" +
" <body>\n" +
"          <nav aria-label=\"breadcrumb\" class=\"main-breadcrumb\">\n" +
"            <ol class=\"breadcrumb\">\n" +
"              <li class=\"breadcrumb-item\"><a href=\"WelcomePage.html\">Home</a></li>\n" +
"              <li class=\"breadcrumb-item active\" aria-current=\"page\">Sales</li>\n" +
"            </ol>\n" +
"          </nav>\n" +
"</body>\n" +
"</html>");
            String sub = req.getParameter("S");
            HttpServletRequest request = (HttpServletRequest) req;
            HttpSession session=request.getSession(false);
            String MEmail=(String)session.getAttribute("Memail");
             String ffinv="";
            if(sub.equals("Sales Bill"))
            {
//                String jdbcurl = "jdbc:derby://localhost:1527/Pharmacy_System"; 
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
                        
                             
                                
                                String sqlquery1 = "Select * from salesinfo";
                                
                                java.sql.ResultSet rs2  =st.executeQuery(sqlquery1);
                               
                                int inv=0;
                                while(rs2.next())
                                {
                                    String string1 = rs2.getString("invno");
                                    inv = Integer.parseInt(string1);
                                }
                                
                                int finv = inv+1;
                                ffinv+= String.valueOf(finv);
                               
                               
                   
                        
                        } catch (SQLException ex) {
                        out.println(""+ex+"");
                        Logger.getLogger(saleinvoice.class.getName()).log(Level.SEVERE, null, ex);
                    }
                out.println("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                        "<style>\n" +
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
                        "</style>"+
                "	<title>Sales Invoice</title>\n" +
                "	<link rel=\"stylesheet\" href=\"bootstrap.min.css\">\n" +
                "\n" +
                "	<!-- jQuery library -->\n" +
                "	<script src=\"jquery.min.js\"></script>\n" +
                
                "	<script type=\"text/javascript\">\n" +
                "		$(document).ready(function(){\n" +
                "    //group add limit\n" +
                "    var maxGroup = 10;\n" +
                "    \n" +
                "    //add more fields group \n" +
                "    $(\".addMore\").click(function(){\n" +
                "        if($('body').find('.fieldGroup').length < maxGroup){\n" +
                "            var fieldHTML = '<div class=\"form-group fieldGroup\">'+$(\".fieldGroupCopy\").html()+'</div>';\n" +
                "            $('body').find('.fieldGroup:last').after(fieldHTML);\n" +
                "        }else{\n" +
                "            alert('Maximum '+maxGroup+' groups are allowed.');\n" +
                "        }\n" +
                "    });\n" +
                "    \n" +
                "    //remove fields group\n" +
                "    $(\"body\").on(\"click\",\".remove\",function(){ \n" +
                "        $(this).parents(\".fieldGroup\").remove();\n" +
                "    });\n" +
                "});\n" +
                "	</script>\n" +
                //add here
                "<script>\n" +
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
                    "}" +
                    "</script>" +
                "</head>\n" +
                "<body>\n" +
                "<div class=\"container\">\n" +
                "<form method=\"post\" action=\"saleinvoice\">\n" +
                "            \n" +
                "    <div class=\"form-group fieldGroup\">\n" +
                "			<h4>Invoice No:</h4><input type=\"text\" name=\"invno\" value=\""+ffinv+"\" class=\"form-control\" placeholder=\"Enter Invoice No\"readonly/> \n" +
                "			       	\n" +
                "			<h4>Patient Name:</h4><input type=\"text\" name=\"Pname\" class=\"form-control\" placeholder=\"Enter Patient Name\"/> \n" +
                "			\n" +
                "			<h4>Patient Address:</h4><input type=\"text\" name=\"Padd\" class=\"form-control\" placeholder=\"Enter Patient Address\"/> \n" +
                "			\n" +
                "			<h4>Doctor Name:</h4><input type=\"text\" name=\"Dname\" class=\"form-control\" placeholder=\"Enter Doctor Name\"/> \n" +
                "			" +
                "			<h4>Search Here:</h4><input type=\"text\" id=\"myInput\" onkeyup=\"myFunction()\" name=\"search\" class=\"form-control\" placeholder=\"Search Product Name Here....\"/> \n" +
                "			<br> <br>\n" +
                "        <div class=\"input-group\">\n" +
                "            <h4>Product Name:</h4><input type=\"text\" name=\"proname[]\" class=\"form-control\" placeholder=\"Enter Product Quantity\"/><br>\n" +
                "            <h4>Product Quantity:</h4><input type=\"text\" name=\"proqu[]\" class=\"form-control\" placeholder=\"Enter Product Quantity\"/>\n" +
                "            <div class=\"input-group-addon\"> \n" +
                "                <a href=\"javascript:void(0)\" class=\"btn btn-success addMore\"><span class=\"glyphicon glyphicon glyphicon-plus\" aria-hidden=\"true\"></span> Add</a>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "    \n" +
                
                "   <input type=\"submit\" name=\"submit\" class=\"btn btn-primary\" value=\"Ganerate Bill\"/>\n" +
                "    \n" +
                "</form>\n" +
                
                "<div class=\"form-group fieldGroupCopy\" style=\"display: none;\">\n" +
                "    <div class=\"input-group\">\n" +
                "            <h4>Product Name:</h4><input type=\"text\" name=\"proname[]\" class=\"form-control\" placeholder=\"Enter Product Quantity\"/><br>\n" +
                "            <h4>Product Quantity:</h4><input type=\"text\" name=\"proqu[]\" class=\"form-control\" placeholder=\"Enter Product Quantity\"/>\n" +
                "        <div class=\"input-group-addon\"> \n" +
                "            <a href=\"javascript:void(0)\" class=\"btn btn-danger remove\"><span class=\"glyphicon glyphicon glyphicon-remove\" aria-hidden=\"true\"></span> Remove</a>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</div>\n" +
                "\n");
               
                out.println("<table id=\"myTable\">");  
                out.println("<tr class=\"header\"><th>Product Id</th><th>Company Name</th><th>Product Name</th><th>Product MFG Date</th><th>Product EXP Date</th><th>Product Price</th><th>Product Quantity</th><th>Agency name</th><th>Drug Location name</th><th>Drug Instoke</th><tr>");  
                    
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
                        out.println("</table></div>");
                        } catch (SQLException ex) {
                        out.println(""+ex+"");
                        Logger.getLogger(Register_Servlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                        out.println("</body>");
                        out.println("</html>");
            }
            else if(sub.equals("Sales Register"))
            {
                //from date
                //to date
                //submit
                //create html form
                //it will ganrate report of all the invoce from fromdate to todate
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
                        "background-color:lightyellow;\n" +
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
                        out.println("<title>Sales Register</title>");
                     
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<h2>Sales Register</h2>\n" +
                        "<div class=\"container\">\n" +
                        "  <form action=\"Sales_register\">\n" +
                        "    <h4><div class=\"row\">\n" +
                        "      <div class=\"col-25\">\n" +
                        "        <label for=\"fname\">Date</label>\n" +
                        "      </div>\n" +
                        "      <div class=\"col-75\">\n" +
                        "        <input type=\"text\" id=\"fname\" name=\"fdate\" placeholder=\"Year-Mm-Dd\">\n" +
                        "      </div>\n" +
                        "    </div></h4>\n" +
                        "    <div class=\"row\">\n" +
                        "      <input type=\"submit\" name=\"sub\" value=\"GET\">\n" +
                        "    </div>\n" +
                        "  </form>\n" +
                        "</div>");
                    out.println("</body>");
                    out.println("</html>");
                
            }
            else if(sub.equals("Bill info"))
            {
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
                        out.println("<title>Bill info</title>");
                     
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<h2>Bill Info</h2>\n" +
                        "<div class=\"container\">\n" +
                        "  <form action=\"bill_info\">\n" +
                        "    <h4><div class=\"row\">\n" +
                        "      <div class=\"col-25\">\n" +
                        "        <label for=\"fname\">Invoice no</label>\n" +
                        "      </div>\n" +
                        "      <div class=\"col-75\">\n" +
                        "        <input type=\"text\" id=\"fname\" name=\"fno\" placeholder=\"Enter Invoice No\">\n" +
                        "      </div>\n" +
                        "    </div></h4>\n" +
                        "    <div class=\"row\">\n" +
                        "      <input type=\"submit\" name=\"sub\" value=\"GET\">\n" +
                        "    </div>\n" +
                        "  </form>\n" +
                        "</div>");
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
