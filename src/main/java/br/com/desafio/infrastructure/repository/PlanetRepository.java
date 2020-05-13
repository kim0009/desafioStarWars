package br.com.desafio.infrastructure.repository;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import br.com.desafio.infrastructure.model.Planet;

@EnableScan
public interface PlanetRepository extends CrudRepository<Planet, String> {
    Planet findByNome(String nome);
    boolean existsByNome(String nome);
}