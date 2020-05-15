package br.com.desafio.domain.service.internal.impl;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.MappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.desafio.domain.dto.internal.PlanetDto;
import br.com.desafio.domain.service.external.PlanetExternalService;
import br.com.desafio.domain.service.internal.PlanetInternalService;
import br.com.desafio.exceptions.ApiException;
import br.com.desafio.exceptions.DeleteException;
import br.com.desafio.exceptions.NoDataException;
import br.com.desafio.exceptions.SaveException;
import br.com.desafio.infrastructure.model.Planet;
import br.com.desafio.infrastructure.repository.PlanetRepository;
import br.com.desafio.utils.ApiMessage;

@Service
public class PlanetInteralServiceImpl implements PlanetInternalService {

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
        try {
            if(repository.existsById(id))
                return mapper.map(repository.findById(id).get(), PlanetDto.class);
            else 
                return null;
        } catch (Exception e) {
            throw new NoDataException(ApiMessage.SEARCH_ERROR_MESSAGE);
        }
    }
    
    public PlanetDto getByName(String name) {
        try {
            
            if(repository.existsByNome(name)) {
                PlanetDto planetDto = mapper.map(repository.findByNome(name), PlanetDto.class);
                return planetExternalService.getCountFilms(planetDto);
            }
            else 
                return null;
        } catch (Exception e) {
            throw new NoDataException(ApiMessage.SEARCH_ERROR_MESSAGE);
        }
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