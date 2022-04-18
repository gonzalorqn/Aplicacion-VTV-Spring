package com.vtv.vtv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class VtvApplication {

	public static void main(String[] args) {
		SpringApplication.run(VtvApplication.class, args);
	}

}
