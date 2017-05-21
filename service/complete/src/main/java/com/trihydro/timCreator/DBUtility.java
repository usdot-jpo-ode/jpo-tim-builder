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
                 Class.forName("oracle.jdbc.driver.OracleDriver");
                
                 connection = DriverManager.getConnection("jdbc:oracle:thin:@10.145.9.22:1521/cvdev.gisits.local", "CVCOMMS", "C0nnV3h1cl3");
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