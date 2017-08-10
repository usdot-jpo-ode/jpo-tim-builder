package com.trihydro.timCreator.helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import javax.sql.DataSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import javax.annotation.Resource;
import org.springframework.context.EnvironmentAware;

@Component
public class DBUtility {
    
    @Autowired
    public Environment env;
    
    private static Connection connection = null;
                     
    public Connection getConnection() {
        if (connection != null){
            return connection;
        }
        else {
            try {
                // connect to oracle database
                 Class.forName("com.mysql.jdbc.Driver");
                 System.out.println(env.getProperty("databaseurl"));  
                 //connection = dataSource.getConnection();          
                 //connection = DriverManager.getConnection(env.getProperty("my.property"), "root", "mypassword");
                 connection = DriverManager.getConnection(env.getProperty("databaseurl"), "root", "mypassword");
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