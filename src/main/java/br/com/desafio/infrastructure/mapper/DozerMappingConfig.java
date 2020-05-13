package br.com.desafio.infrastructure.mapper;

import org.dozer.DozerBeanMapper;
import org.dozer.loader.api.BeanMappingBuilder;

import br.com.desafio.domain.dto.internal.PlanetDto;
import br.com.desafio.infrastructure.model.Planet;

public class DozerMappingConfig {

    public DozerMappingConfig() {
        this.init();
    }

    private void init() {

        BeanMappingBuilder builder = new BeanMappingBuilder() {
            @Override
            protected void configure() {
                mapping(PlanetDto.class, Planet.class)
                    .exclude("qtdAparicaoEmFilmes");
            }
        };

        DozerBeanMapper mapper = new DozerBeanMapper();
        mapper.addMapping(builder);
        
    }
}