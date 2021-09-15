/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pharmacy_Package;

import static java.lang.System.out;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author admin
 */
public class CustomerServices {

   
    public String resetCustomerPassword(String email) {
        
        String pp="";
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
            String sqlquery =  "SELECT * FROM users WHERE Email='"+email+"'";
            java.sql.ResultSet rs = (java.sql.ResultSet)st.executeQuery(sqlquery);
            int j=0;
            
            while(rs.next())
            {
                pp = rs.getString("PASSWORD");
                j++;
            }
        } catch (SQLException ex) {
            out.println(""+ex+"");
            Logger.getLogger(CustomerServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pp;
 }
}
