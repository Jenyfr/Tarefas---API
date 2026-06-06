package com.exercicio.tarefas;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class TarefaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void deveCriarListarAtualizarEExcluirTarefa() throws Exception {
        String tarefa = """
                {
                  "titulo": "Estudar Spring Boot",
                  "descricao": "Implementar metodos HTTP",
                  "concluida": false
                }
                """;

        mockMvc.perform(post("/api/tarefas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(tarefa))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.titulo").value("Estudar Spring Boot"));

        mockMvc.perform(get("/api/tarefas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));

        String tarefaAtualizada = """
                {
                  "titulo": "Estudar API REST",
                  "descricao": "Testar o metodo PUT",
                  "concluida": true
                }
                """;

        mockMvc.perform(put("/api/tarefas/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(tarefaAtualizada))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("Estudar API REST"))
                .andExpect(jsonPath("$.concluida").value(true));

        mockMvc.perform(delete("/api/tarefas/1"))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/tarefas/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void deveRetornarErroQuandoTituloEstiverVazio() throws Exception {
        String tarefaInvalida = """
                {
                  "titulo": "",
                  "descricao": "Sem titulo",
                  "concluida": false
                }
                """;

        mockMvc.perform(post("/api/tarefas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(tarefaInvalida))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.campos.titulo").exists());
    }
}
