package com.epitech.pictsmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.PropertySource;

@EnableCaching
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@ConfigurationProperties
@PropertySource("Classpath:maven.properties")
public class PictsmanagerApplication {

	public static void main(String[] args) {
		System.out.println("Hello world");
		SpringApplication.run(PictsmanagerApplication.class, args);
	}

}
