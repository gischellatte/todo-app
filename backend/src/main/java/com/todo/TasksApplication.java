package com.todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TasksApplication {//looks for controllers

	public static void main(String[] args) {
		SpringApplication.run(TasksApplication.class, args);
	}

}
