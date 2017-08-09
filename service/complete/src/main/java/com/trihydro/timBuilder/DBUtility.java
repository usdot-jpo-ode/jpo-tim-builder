package com.trihydro.timCreator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtility {
 private static Connection connection = null;
    public static Connection getConnection() {
        if (connection != null){
            return connection;
        }
        else {
            try {
                // connect to oracle database
                 Class.forName("com.mysql.jdbc.Driver");
                
                 connection = DriverManager.getConnection("jdbc:mysql://172.17.0.3:3306/timBuilder", "root", "mypassword");
                 if (connection != null) {
                    System.out.println("Connection Successful! Enjoy. Now it's time to push data");
                 } else {
                    System.out.println("Failed to make connection!");
                 }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();            
            } 
            return connection;
        }
    }
}