/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aptech_soft.DBTemplates;


import alerts.AlertTemplates;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author EVELYN
 */
public class DatabaseTemp {
    String host = "jdbc:derby://localhost/BellDB";
    String Username ="SecureBell";
    String pass = "bell123" ;
     AlertTemplates at = new AlertTemplates();
     
    public boolean writeToDB(String query) throws SQLException{
        try{
        Connection c = DriverManager.getConnection(host,Username, pass);
        Statement s = c.createStatement();
        s.execute(query);
        return true;
        }
        catch(SQLException sql){return(false);}}
        
    public ResultSet readFromDB(String query){
        ResultSet set = null;
        try {
            Connection c = DriverManager.getConnection(host, Username, pass);
            Statement s = c.createStatement();
//        Statement s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            set = s.executeQuery(query);
            
        } catch (SQLException ex) {
           at.alertError(" cannot connect"+ ex.getMessage());
        }
         return set;
    }
    
    
    public boolean updates(String query) throws SQLException{
        try{
        Connection c = DriverManager.getConnection(host,Username, pass);
        Statement s = c.createStatement();
        s.executeUpdate(query);
        return(true);}
        catch(SQLException sql){return(false);}}
    
}
   
   
