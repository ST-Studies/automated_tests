package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.service.AdocaoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

// Teste de Integração
// Teste de Unidade

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class AdocaoControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AdocaoService service;

    @Autowired
    private JacksonTester<SolicitacaoAdocaoDto> jsonDto;

    @Test
    @DisplayName("Devolve Código 400 Para Solicitacao De Adocao Com Erros")
    void cenario01() throws Exception {
        // ARRANGE
        String json = "{}";

        // ACT
        MockHttpServletResponse response = mvc.perform(
            post("/adocoes")
                    .content(json)
                    .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        // ASSERT
        assertEquals(400, response.getStatus());
    }

    @Test
    @DisplayName("Devolve Código 200 Para Solicitacao De Adocao Sem Erros")
    void cenario02() throws Exception {
        // ARRANGE
        String json = """
                {
                    "idPet": 1,
                    "idTutor": 1,
                    "motivo": "Motivo qualquer"
                }
                """;

        // ACT
        MockHttpServletResponse response = mvc.perform(
                post("/adocoes")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        // ASSERT
        assertEquals(200, response.getStatus());
    }

    @Test
    @DisplayName("Devolve Código 200 Para Solicitacao De Adocao Sem Erros V2")
    void cenario03() throws Exception {
        // ARRANGE
        SolicitacaoAdocaoDto dto = new SolicitacaoAdocaoDto(1l, 1l, "Motivo qualquer");

        // ACT
        MockHttpServletResponse response = mvc.perform(
                post("/adocoes")
                        .content(jsonDto.write(dto).getJson())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        // ASSERT
        assertEquals(200, response.getStatus());
        assertEquals("Adoção solicitada com sucesso!", response.getContentAsString());
    }
}
