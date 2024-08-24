package com.example.ProcessosJuridicos.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import com.example.ProcessosJuridicos.dto.ProcessoDTO;
import com.example.ProcessosJuridicos.dto.ReuDTO;
import com.example.ProcessosJuridicos.service.ProcessoService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ProcessoControllerIntegration {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private ProcessoService service;

  @BeforeEach
  void setUp() throws Exception {

  }

  @Test
  void getProcessShouldReturnProcessoDTOisOk() throws Exception {

    ProcessoDTO dtoToInsert = new ProcessoDTO(1L, "789101");

    Set<ReuDTO> reus = new HashSet<>();
    reus.add(new ReuDTO(1L, "João Silva", "12345678901")); // Reus fictícios
    reus.add(new ReuDTO(2L, "Maria Oliveira", "98765432100"));
    dtoToInsert.setReus(reus);

    String jsonBody = objectMapper.writeValueAsString(dtoToInsert);

    ResultActions result = mockMvc.perform(get("/processos/789101")
        .content(jsonBody)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON));

    result.andExpect(status().isOk());

  }

  @Test
  public void insertShouldReturnBadRequestWhenProcessExists() throws Exception {

    ProcessoDTO dtoToInsert = new ProcessoDTO(1L, "789101");

    String jsonBody = objectMapper.writeValueAsString(dtoToInsert);

    ResultActions result = mockMvc.perform(post("/processos")
        .content(jsonBody)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON));

    result.andExpect(status().isBadRequest());
  }
  /*
   * @Test
   * void postProcessShouldReturnProcessoDTOisCreated() throws Exception {
   * 
   * ProcessoDTO dtoToInsert = new ProcessoDTO(1L, "789101");
   * 
   * Set<ReuDTO> reus = new HashSet<>();
   * reus.add(new ReuDTO(null, "Sabrina Silva", "12345678821"));
   * reus.add(new ReuDTO(null, "Beto Oliveira", "98765432990"));
   * dtoToInsert.setReus(reus);
   * 
   * String jsonBody = objectMapper.writeValueAsString(dtoToInsert);
   * 
   * ResultActions result = mockMvc.perform(post("/processos")
   * .content(jsonBody)
   * .contentType(MediaType.APPLICATION_JSON)
   * .accept(MediaType.APPLICATION_JSON));
   * 
   * result.andExpect(status().isCreated());
   * 
   * 
   * }
   */

  @Test
  public void deleteShouldReturnNoContentWhenNumberExists() throws Exception {

    ResultActions result = mockMvc.perform(delete("/processos/789101"));

    result.andExpect(status().isNoContent());
  }
  /*
   * @Test
   * public void deleteShouldReturnNotFoundWhenNonExistingId() throws Exception {
   * 
   * ResultActions result = mockMvc.perform(delete("/processos/123456"));
   * result
   * .andExpect(jsonPath("status").value(404));
   * 
   * }
   */
}
