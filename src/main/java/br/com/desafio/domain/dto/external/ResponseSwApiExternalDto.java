package br.com.desafio.domain.dto.external;

import java.util.List;

import lombok.Data;

@Data
public class ResponseSwApiExternalDto {

    private int count;
    private String previous;
    private String next;
    private List<PlanetExternalDto> results;
}