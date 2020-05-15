package br.com.desafio.domain.service.external;

import java.util.List;
import br.com.desafio.domain.dto.external.PlanetExternalDto;
import br.com.desafio.domain.dto.internal.PlanetDto;

public interface PlanetExternalService {
    List<PlanetExternalDto> getFilmsByPlanet();
    PlanetDto getCountFilms(PlanetDto planetDto);
    List<PlanetDto> getCountFilms(List<PlanetDto> listPlanetDto);
}