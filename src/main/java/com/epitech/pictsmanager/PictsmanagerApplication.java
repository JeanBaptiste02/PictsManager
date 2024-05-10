package com.epitech.pictsmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.PropertySource;

/**
 * Main class to run the Picts Manager application
 * @author Jean-Baptiste, Kamel, Victor, Mahdi
 */
@EnableCaching
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@ConfigurationProperties
@PropertySource("classpath:application.properties")
public class PictsmanagerApplication {

	/**
	 * Main method to start the Spring Boot application
	 * @param args Command-line arguments.
	 */
	public static void main(String[] args) {
		SpringApplication.run(PictsmanagerApplication.class, args);
	}

}
