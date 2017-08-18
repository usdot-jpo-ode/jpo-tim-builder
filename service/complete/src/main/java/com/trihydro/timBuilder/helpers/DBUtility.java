package com.trihydro.timBuilder.helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.ibatis.io.Resources; 

@Component
public class DBUtility {
    
    @Autowired
    public Environment env;
    
    private Connection connection = null;
    
    // database connection, dependent on the application.properties variables                 
    public Connection getConnection() {
        // return the connection if its already created
        if (connection != null) {
            return connection;
        }
        // else create the connection
        else {
            try {
                // make connection
                Class.forName(env.getProperty("databaseclass"));
                connection = DriverManager.getConnection(env.getProperty("databaseurl"), "root", "mypassword"); 
                
                // connection successful
                if (connection != null) {
                    System.out.println("Connection Successful! Enjoy. Now it's time to push data");
                    
                    // if using an in memory database for unit tests                        
                    if(env.getProperty("databaseclass").equals("org.h2.Driver")) {               
                        // Initialize object for ScriptRunner to read in a script to create tables and insert data
                        ScriptRunner scriptRunner = new ScriptRunner(connection);
                        try {
                            // run script
                            scriptRunner.runScript(Resources.getResourceAsReader("db/unitTestSql.sql"));
                            connection.commit();
                        } 
                        catch (Exception e) {
                            throw new IllegalStateException("ScriptRunner failed", e);
                        }                               
                    }
                } 
                // else connection failed
                else {
                    System.out.println("Failed to make connection!");
                }
            } 
            catch (ClassNotFoundException e) {
                e.printStackTrace();
            } 
            catch (SQLException e) {
                e.printStackTrace();            
            } 
            return connection;
        }
    }
}