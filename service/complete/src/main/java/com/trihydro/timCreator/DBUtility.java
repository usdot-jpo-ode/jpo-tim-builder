package com.trihydro.timCreator;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

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
                
                 connection = DriverManager.getConnection("jdbc:oracle:thin:@ordb-p01-vip:1521/cvdev.gisits.local", "CVCOMMS", "C0nnV3h1cl3");
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