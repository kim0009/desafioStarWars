package br.com.desafio.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.dozer.MappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.http.ResponseEntity.status;
import static org.springframework.http.ResponseEntity.ok;

import br.com.desafio.domain.dto.internal.PlanetDto;
import br.com.desafio.domain.dto.internal.ResponseDto;
import br.com.desafio.domain.service.internal.PlanetInternalService;
import br.com.desafio.exceptions.ApiException;
import br.com.desafio.exceptions.DeleteException;
import br.com.desafio.exceptions.NoDataException;
import br.com.desafio.exceptions.SaveException;
import br.com.desafio.utils.ApiMessage;

@RestController
@RequestMapping("/api/planet")
public class PlanetController {

    private Logger logger = LogManager.getLogger(getClass());

	@Autowired
	private PlanetInternalService planetInternalService;
    
    @GetMapping("teste")
    public ResponseEntity<String> test() {
        logger.info("Teste de aplicação");
        return new ResponseEntity<String>(planetInternalService.teste(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseDto> getById(@PathVariable("id") String id) {
        try {
            logger.info("GetById");
            return ok(new ResponseDto(planetInternalService.getById(id)));
        } catch (Exception ex) {
            throw new NoDataException(ApiMessage.SEARCH_ERROR_MESSAGE);
        }
    }

    @GetMapping("nome/{nome}")
    public ResponseEntity<ResponseDto> getByName(@PathVariable("nome") String name) {
        try {
            logger.info("GetByName");
            return ok(new ResponseDto(planetInternalService.getByName(name)));
        } catch (Exception ex) {
            throw new NoDataException(ApiMessage.SEARCH_ERROR_MESSAGE);
        }
    }

    @GetMapping
    public ResponseEntity<ResponseDto> getAll() {
        try {       
            logger.info("GetAll");
            return ok(new ResponseDto(planetInternalService.list()));
        } catch (Exception ex) {
            throw new NoDataException(ApiMessage.SEARCH_ERROR_MESSAGE);
        }
    }

    @PostMapping
    public ResponseEntity<ResponseDto> save(@RequestBody PlanetDto planet) {
        try {
            logger.info("Save");
            return status(HttpStatus.CREATED).body(new ResponseDto(planetInternalService.save(planet)));
        } catch (Exception ex) {
            if(ex instanceof MappingException)
                throw new ApiException(ApiMessage.API_INTERNAL_ERROR_MESSAGE);
            
            throw new SaveException(ApiMessage.SAVE_ERROR_MESSAGE);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ResponseDto> delete(@PathVariable("id") String id) {
        try {
            logger.info("Delete");
            return ok(new ResponseDto(planetInternalService.delete(id), ApiMessage.DELETE_SUCESS_MESSAGE));
        } catch (Exception ex) {
            throw new DeleteException(ApiMessage.DELETE_ERROR_MESSAGE);
        }
    }
}