package com.github.wukap.tunbe;

import com.github.wukap.tunbe.testDate.TestData;
import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import javax.sql.DataSource;

@SpringBootApplication
public class TunbeApplication {

	@SneakyThrows
	public static void main(String[] args) {
		var app = SpringApplication.run(TunbeApplication.class, args);
		//TestData testData = new TestData();
		//testData.generateDataInBd(app);
	}

}
