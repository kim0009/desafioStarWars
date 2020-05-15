package br.com.desafio.domain.service.internal.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.desafio.domain.dto.external.PlanetExternalDto;
import br.com.desafio.domain.dto.external.ResponseSwApiExternalDto;
import br.com.desafio.domain.dto.internal.PlanetDto;
import br.com.desafio.domain.service.external.PlanetExternalService;
import br.com.desafio.domain.service.internal.PlanetService;
import br.com.desafio.infrastructure.model.Planet;
import br.com.desafio.infrastructure.repository.PlanetRepository;

@Service
public class PlanetServiceImpl implements PlanetService {

    private final Mapper mapper = new DozerBeanMapper();

    @Autowired
    private PlanetExternalService planetExternalService;

    @Autowired
    private PlanetRepository repository;

    @Override
    public String teste() {
        return "Teste de Api Ok";
    }

    public List<PlanetDto> list() {
        List<PlanetDto> listDtos = new ArrayList<PlanetDto>();
        List<Planet> list = new ArrayList<Planet>();
		repository.findAll().iterator()
                        .forEachRemaining(list::add);
        for (Planet planet : list) {
            listDtos.add(mapper.map(planet, PlanetDto.class));
        }
        
        planetExternalService.getCountFilms(listDtos);
		return listDtos;
	}

	public PlanetDto getById(String id) {
		if(repository.existsById(id))
			return mapper.map(repository.findById(id).get(), PlanetDto.class);
		else 
			return null;
    }
    
    public PlanetDto getByName(String name) {
		if(repository.existsByNome(name)) {
            PlanetDto planetDto = mapper.map(repository.findByNome(name), PlanetDto.class);
			return planetExternalService.getCountFilms(planetDto);
        }
		else 
			return null;
    }
    
    public Planet save(PlanetDto planetDto) {
		Planet planet = repository.save(mapper.map(planetDto, Planet.class));
		return planet;
    }
    
    public boolean delete(String id) {
		boolean exists = repository.existsById(id);
		if(exists) {
			repository.deleteById(id);
			return exists;
		}
        return false;
	}
}