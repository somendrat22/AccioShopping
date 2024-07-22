package com.accioshoppingbackend.Accio.Shopping.Website;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// Hey this project is not normal java project this is a Springboot project
public class AccioShoppingWebsiteApplication {

	public static void main(String[] args) {
		// Earlier jvm use to run our code
		// We are asking springboot to run our code.
		// Tomcat helps springboot application to create the server
		// Tomcat provides port number to your application
		SpringApplication.run(AccioShoppingWebsiteApplication.class, args);
	}

}
