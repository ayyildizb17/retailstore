package com.retailstore.retailstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;



@SpringBootApplication
@ComponentScan("com.retailstore.retailstore")
public class RetailstoreApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(RetailstoreApplication.class, args);
	}




}