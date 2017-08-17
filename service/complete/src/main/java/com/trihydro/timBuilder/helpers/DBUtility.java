package com.trihydro.timBuilder.helpers;

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
import java.sql.Statement;
import org.apache.ibatis.jdbc.ScriptRunner;
import java.sql.SQLException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.io.FileNotFoundException;
import org.apache.ibatis.io.Resources; 


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
                 Class.forName(env.getProperty("databaseclass"));
                 connection = DriverManager.getConnection(env.getProperty("databaseurl"), "root", "mypassword");     
                 if(env.getProperty("databaseclass").equals("org.h2.Driver")){
                    // TODO convert to script reader
                    // Initialize object for ScriptRunner
                    ScriptRunner scriptRunner = new ScriptRunner(connection);

                    try {
                        scriptRunner.runScript(Resources.getResourceAsReader("db/testSql.sql"));
                        connection.commit();
                    } catch (Exception e) {
                        throw new IllegalStateException("Fail to restore: ", e);
                    }                            
                 }
                
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