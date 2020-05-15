package br.com.desafio.domain.service.internal;

import br.com.desafio.domain.dto.internal.PlanetDto;
import br.com.desafio.infrastructure.model.Planet;

import java.util.List;

public interface PlanetInternalService {
    String teste();
    PlanetDto getById(String id); 
    PlanetDto getByName(String nome);
    List<PlanetDto> list(); 
    Planet save(PlanetDto planetDto);
    boolean delete(String id);
}