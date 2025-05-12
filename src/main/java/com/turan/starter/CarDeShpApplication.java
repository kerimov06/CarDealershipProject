package com.turan.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = {"com.turan"})
@ComponentScan(basePackages = {"com.turan"})
@EntityScan(basePackages = {"com.turan"})
@SpringBootApplication
public class CarDeShpApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarDeShpApplication.class, args);
	}

}
