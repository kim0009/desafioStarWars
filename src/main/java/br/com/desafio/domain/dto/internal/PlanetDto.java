package br.com.desafio.domain.dto.internal;

import lombok.Data;

@Data
public class PlanetDto {

    private String id;
    private String nome;
    private String terreno;
    private String clima;
    private int qtdAparicaoEmFilmes;
}