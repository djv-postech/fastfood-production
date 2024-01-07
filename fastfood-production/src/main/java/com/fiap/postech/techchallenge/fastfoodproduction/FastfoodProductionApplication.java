package com.fiap.postech.techchallenge.fastfoodproduction;

import com.fiap.postech.techchallenge.fastfoodproduction.application.config.ControllerExceptionHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({ControllerExceptionHandler.class})
public class FastfoodProductionApplication {

	public static void main(String[] args) {
		SpringApplication.run(FastfoodProductionApplication.class, args);
	}

}
