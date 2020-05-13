package br.com.desafio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.desafio.infrastructure.mapper.DozerMappingConfig;

@SpringBootApplication
public class DesafioStarWarsApplication {

	public static void main(String[] args) {
		new DozerMappingConfig();
		SpringApplication.run(DesafioStarWarsApplication.class, args);
	}

}
