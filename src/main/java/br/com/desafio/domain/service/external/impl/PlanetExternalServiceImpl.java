package br.com.desafio.domain.service.external.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import br.com.desafio.domain.dto.external.PlanetExternalDto;
import br.com.desafio.domain.dto.external.ResponseSwApiExternalDto;
import br.com.desafio.domain.dto.internal.PlanetDto;
import br.com.desafio.domain.service.external.PlanetExternalService;
import br.com.desafio.exceptions.SwApiException;
import br.com.desafio.infrastructure.integration.StarWarsApiIntegration;
import br.com.desafio.utils.ApiMessage;

@Service
public class PlanetExternalServiceImpl implements PlanetExternalService {

    @Autowired
    private StarWarsApiIntegration apiIntegration;

    private int getCountFilms(PlanetDto planetDto, List<PlanetExternalDto> listPlanetExternalDto) {
        Optional<PlanetExternalDto> peExternalDto = listPlanetExternalDto.stream()
                                    .filter(x -> x.getName().toLowerCase().equals(planetDto.getNome().toLowerCase()))
                                    .findFirst();
        if(peExternalDto.isPresent())
            return peExternalDto.get().getFilms().size();

        return 0;
    }

    public List<PlanetDto> getCountFilms(List<PlanetDto> listPlanetDto) {
        CompletableFuture<List<PlanetExternalDto>> future = getAllPlanets();
        future.thenAcceptAsync(response -> {
            for(PlanetDto planetDto: listPlanetDto)
                planetDto.setQtdAparicaoEmFilmes(getCountFilms(planetDto, response));
        });
        future.join();
        return listPlanetDto;
    }
    
    public PlanetDto getCountFilms(PlanetDto planetDto) {
        CompletableFuture<List<PlanetExternalDto>> future = getAllPlanets();
        future.thenAcceptAsync(response -> {
            planetDto.setQtdAparicaoEmFilmes(getCountFilms(planetDto, response));
        });
        future.join();
        return planetDto;
    }

    @Async
    public CompletableFuture<List<PlanetExternalDto>> getAllPlanets() {
        try {
            List<CompletableFuture<ResponseSwApiExternalDto>> futures = new ArrayList<CompletableFuture<ResponseSwApiExternalDto>>();
            ResponseSwApiExternalDto responseSwApiExternalDto = apiIntegration.getPlanets(1);
            List<PlanetExternalDto> listPlanetExternalDto = new ArrayList<PlanetExternalDto>();
            listPlanetExternalDto.addAll(responseSwApiExternalDto.getResults());
            int countPages = responseSwApiExternalDto.getCount() / listPlanetExternalDto.size();

            for(int i = 2; i <= countPages; i++) {
                final int page = i;
                futures.add(CompletableFuture
                    .supplyAsync(() -> apiIntegration.getPlanets(page)));
            }
    
            CompletableFuture<Void> allDoneFuture = CompletableFuture.allOf(futures.toArray (new CompletableFuture [futures.size()])); 
    
            CompletableFuture<List<ResponseSwApiExternalDto>> allCompleteFuture = allDoneFuture.thenApplyAsync(v -> 
                    futures.stream() 
                            .map(x -> x.join()) 
                            .collect(Collectors.<ResponseSwApiExternalDto>toList())
            );
    
            return allCompleteFuture.thenApplyAsync(response -> {
                List<PlanetExternalDto> list = new ArrayList<PlanetExternalDto>(); 
                list.addAll(listPlanetExternalDto);
                for(ResponseSwApiExternalDto dto : response) {
                    list.addAll(dto.getResults());
                }
                return list;
            });
        } catch (Exception e) {
            throw new SwApiException(ApiMessage.SW_API_ERROR_MESSAGE);
        }
    }
}