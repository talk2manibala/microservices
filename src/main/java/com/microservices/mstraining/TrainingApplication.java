package com.microservices.mstraining;

import com.microservices.mstraining.model.Employee;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class TrainingApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(TrainingApplication.class, args);

		// Spring Servlet Container
		Employee empBean1 = run.getBean(Employee.class);
		Employee empBean2 = run.getBean(Employee.class);
	}

}
