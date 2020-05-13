package br.com.desafio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.desafio.domain.dto.internal.PlanetDto;
import br.com.desafio.domain.service.internal.PlanetService;

@RestController
@RequestMapping("/api/planet/")
public class PlanetController {
    
	@Autowired
	private PlanetService planetService;
    
    @GetMapping("teste")
    public ResponseEntity<String> test() {
        return new ResponseEntity<String>(planetService.teste(), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<List<PlanetDto>>(planetService.list(), HttpStatus.OK);
    }
}