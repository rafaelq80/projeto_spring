package br.com.rafaelq80.projeto01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Projeto01Application {

	public static void main(String[] args) {
		SpringApplication.run(Projeto01Application.class, args);
	}

}
