package com.SDP;

import com.SDP.Models.Employees;
import com.SDP.Repositories.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.sql.Date;
import java.time.Instant;
import java.util.Calendar;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class VibeApplication {

	public static void main(String[] args) {
		SpringApplication.run(VibeApplication.class, args);
	}
}
