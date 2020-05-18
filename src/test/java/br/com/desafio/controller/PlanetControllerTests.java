package br.com.desafio.controller;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import br.com.desafio.domain.dto.internal.PlanetDto;
import br.com.desafio.domain.service.internal.PlanetInternalService;  

@WebMvcTest(PlanetController.class)
public class PlanetControllerTests {
    
    @Autowired
    private MockMvc mockMvc;

    private PlanetDto planetDtoMock;
 
    @MockBean
    private PlanetInternalService planetInternalService;

    @MockBean 
    private ObjectMapper mapper;
    
    @BeforeEach
    public void setUp() {
        planetDtoMock = new PlanetDto();
        planetDtoMock.setClima("Clima");
        planetDtoMock.setTerreno("Terreno");
        planetDtoMock.setNome("Terra");
        
        mapper.setSerializationInclusion(Include.NON_NULL);
    }
    
	@Test
	public void test() throws Exception {
		when(planetInternalService.teste()).thenReturn("Hello Test");
		this.mockMvc.perform(get("/api/planet/teste")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("Hello Test")));
	}
    
    @Test
	public void getByIdTest() throws Exception {
		when(planetInternalService.getById(planetDtoMock.getId())).thenReturn(planetDtoMock);
        this.mockMvc.perform(get("/api/planet/" + planetDtoMock.getId())
                                .content("application/json"))
                        .andExpect(status().isOk());
	}
    
    @Test
	public void getByNameTest() throws Exception {
		when(planetInternalService.getByName(planetDtoMock.getNome())).thenReturn(planetDtoMock);
        this.mockMvc.perform(get("/api/planet/nome/" + planetDtoMock.getNome())
                                .content("application/json"))
                        .andExpect(status().isOk());
	}
   
    @Test
	public void getAllTest() throws Exception {
		when(planetInternalService.list()).thenReturn(Arrays.asList(planetDtoMock));
        this.mockMvc.perform(get("/api/planet/")
                                .content("application/json"))
                        .andExpect(status().isOk());
	}
    
    @Test
	public void saveTest() throws Exception {
        when(planetInternalService.save(planetDtoMock)).thenReturn(planetDtoMock);
        this.mockMvc.perform(post("/api/planet/")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(mapper.writeValueAsBytes(planetDtoMock)))
                        .andExpect(status().isCreated());
	}
  
    @Test
	public void deleteTest() throws Exception {
		when(planetInternalService.delete("111")).thenReturn(true);
        this.mockMvc.perform(delete("/api/planet/111")
                                .content("application/json"))
                        .andExpect(status().isOk());
	}
}