package br.com.loadti.desafiostatic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"br.com.loadti"})
public class DesafiostaticApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafiostaticApplication.class, args);
	}

}
