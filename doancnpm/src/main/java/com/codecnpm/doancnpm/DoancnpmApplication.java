package com.codecnpm.doancnpm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.codecnpm.doancnpm")
public class DoancnpmApplication {

	public static void main(String[] args) {
		SpringApplication.run(DoancnpmApplication.class, args);
        System.out.println("Application started. You can now access data.");
        // You can add code here to access your data after the application starts
        // For example, if you have a UserService, you can autowire it and call its methods.
        // This is just for testing purposes and should be removed in production.
	}
}
