package com.trihydro.timBuilder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.*;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

@SpringBootApplication
public class Application {

	@Autowired
    DataSource dataSource;

    public static void main(String[] args) {    	
        SpringApplication.run(Application.class, args);
    }
}
