package com.example.direction;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.direction.mapper")
public class DirectionApplication {

	public static void main(String[] args) {
		SpringApplication.run(DirectionApplication.class, args);
	}

}
