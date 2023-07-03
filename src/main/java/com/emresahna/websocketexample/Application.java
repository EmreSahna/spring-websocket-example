package com.emresahna.websocketexample;

import com.emresahna.websocketexample.dto.CreateUserRequest;
import com.emresahna.websocketexample.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@AllArgsConstructor
public class Application {

	private final UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner() {
		return args -> {
			userService.createUser(new CreateUserRequest("emresahna","123"));
			userService.createUser(new CreateUserRequest("johndoe","123"));
			userService.createUser(new CreateUserRequest("markmusk","123"));
		};
	}
}
