package com.codecnpm.doancnpm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.codecnpm.doancnpm")
public class DoancnpmApplication {

	public static void main(String[] args) {
		SpringApplication.run(DoancnpmApplication.class, args);
	}

}
