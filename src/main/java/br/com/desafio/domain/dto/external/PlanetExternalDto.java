package br.com.desafio.domain.dto.external;

import java.util.List;

import lombok.Data;

@Data
public class PlanetExternalDto {
    private List<String> films; 
    private String name; 
}