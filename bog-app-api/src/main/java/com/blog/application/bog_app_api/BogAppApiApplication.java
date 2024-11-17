package com.blog.application.bog_app_api;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BogAppApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BogAppApiApplication.class, args);
	}

	//to change one object to another object
	//UserDto to user user to userDto
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();

	}

}
