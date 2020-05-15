package br.com.desafio.infrastructure.integration;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.desafio.domain.dto.external.ResponseSwApiExternalDto;

@FeignClient(value = "desafioApi", url = "https://swapi.dev/api/")
public interface StarWarsApiIntegration {
    
    @RequestMapping(method = RequestMethod.GET, value = "/planets/?page={page}")
    ResponseSwApiExternalDto getPlanets(@PathVariable("page")int page);
}