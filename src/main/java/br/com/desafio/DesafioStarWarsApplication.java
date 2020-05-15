package br.com.desafio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

import br.com.desafio.infrastructure.mapper.DozerMappingConfig;

@EnableFeignClients
@EnableAsync
@SpringBootApplication
public class DesafioStarWarsApplication {

	public static void main(String[] args) {
		new DozerMappingConfig();
		SpringApplication.run(DesafioStarWarsApplication.class, args);
	}

}
