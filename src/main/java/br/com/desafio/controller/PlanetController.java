package br.com.desafio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.desafio.domain.dto.internal.PlanetDto;
import br.com.desafio.domain.dto.internal.ResponseDto;
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

    @GetMapping()
    public ResponseEntity<ResponseDto> getAll() {
        return new ResponseEntity<ResponseDto>((new ResponseDto(planetService.list())), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<ResponseDto> save(@RequestBody PlanetDto planet) {
        return new ResponseEntity<ResponseDto>((new ResponseDto(planetService.save(planet))), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ResponseDto> delete(@PathVariable("id") String id) {
        return new ResponseEntity<ResponseDto>((new ResponseDto(planetService.delete(id))), HttpStatus.OK);
    }
}