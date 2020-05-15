package br.com.desafio.domain.service.external;

import java.util.List;

import br.com.desafio.domain.dto.internal.PlanetDto;

public interface PlanetExternalService {
    PlanetDto getCountFilms(PlanetDto planetDto);
    List<PlanetDto> getCountFilms(List<PlanetDto> listPlanetDto);
}