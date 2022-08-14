package br.com.projeto.financas.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.projeto.financas.modelo.Receita;


@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ReceitaRepositoryTest {
	
	@Autowired
	private ReceitaRepository receitaRepository;

	@Test
	void deveriaCarregarPorDescricaoEData() {
		String descricao = "telefone";
		int mes = 8;
		int ano = 2022;
		List<Receita> receitas = receitaRepository.buscaDescricaoEData(descricao, mes, ano);
		assertNotNull(receitas, () -> "Nenhuma receita encontrada");
	}

	@Test
	void deveriaCarregarPorDescricaoDataEIDDiferente() {
		String descricao = "Vendas";
		int mes = 8;
		int ano = 2022;
		Long id = (long) 1;
		List<Receita> receitas = receitaRepository.buscaDescricaoDataId(descricao, mes, ano, id);
		assertNotNull(receitas, () -> "Nenhuma receita encontrada");
	}
	
	@Test
	void deveriaFiltrarPorMesEAno() {
		int mes = 8;
		int ano = 2022;
		List<Receita> receitas = receitaRepository.buscaData(mes, ano);
		assertNotNull(receitas, () -> "Nenhuma receita encontrada");
	}
	
	@Test
	void deveriaCarregarPorDescricao() {
		String descricao = "Vendas";
		List<Receita> receitas = receitaRepository.buscaDescricao(descricao);
		assertNotNull(receitas, () -> "Nenhuma receita encontrada");
	}
}
