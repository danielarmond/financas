package br.com.projeto.financas.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.projeto.financas.modelo.Despesa;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DespesaRepositoryTest {

	@Autowired
	private DespesaRepository despesaRepository;

	@Test
	void deveriaCarregarPorDescricaoEData() {
		String descricao = "telefone";
		int mes = 8;
		int ano = 2022;
		List<Despesa> despesas = despesaRepository.buscaDescricaoEData(descricao, mes, ano);
		assertNotNull(despesas, () -> "Nenhuma receita encontrada");
	}

	@Test
	void deveriaCarregarPorDescricaoDataEIDDiferente() {
		String descricao = "telefone";
		int mes = 8;
		int ano = 2022;
		Long id = (long) 1;
		List<Despesa> despesas = despesaRepository.buscaDescricaoDataId(descricao, mes, ano, id);
		assertNotNull(despesas, () -> "Nenhuma receita encontrada");
	}
	
	@Test
	void deveriaFiltrarPorMesEAno() {
		int mes = 8;
		int ano = 2022;
		List<Despesa> despesas = despesaRepository.buscaData(mes, ano);
		assertNotNull(despesas, () -> "Nenhuma receita encontrada");
	}
	
	@Test
	void deveriaCarregarPorDescricao() {
		String descricao = "telefone";
		List<Despesa> despesas = despesaRepository.buscaDescricao(descricao);
		assertNotNull(despesas, () -> "Nenhuma receita encontrada");
	}

}
