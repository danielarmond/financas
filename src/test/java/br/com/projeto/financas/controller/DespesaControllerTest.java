package br.com.projeto.financas.controller;

import java.net.URI;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureMockMvc
public class DespesaControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void DeeriaRetornarAListaCompleta() throws Exception{
		URI uri = new URI("/despesas");
		String json = "{}";
		
		mockMvc.perform(MockMvcRequestBuilders
				.post(uri)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers
				.status()
				.is(200));
	}

}
