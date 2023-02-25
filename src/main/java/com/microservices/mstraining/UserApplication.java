package com.microservices.mstraining;

import com.microservices.mstraining.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class UserApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(UserApplication.class, args);

		// Spring Servlet Container
		User userBean1 = run.getBean(User.class);
		User userBean2 = run.getBean(User.class);
	}

}
