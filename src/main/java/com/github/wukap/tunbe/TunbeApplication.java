package com.github.wukap.tunbe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;

@SpringBootApplication
public class TunbeApplication {

	public static void main(String[] args) {
		var app = SpringApplication.run(TunbeApplication.class, args);
		DataSource dataSource = app.getBean(DataSource.class);
		System.out.println(dataSource);
	}

}
