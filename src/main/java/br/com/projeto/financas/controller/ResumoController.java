package br.com.projeto.financas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.financas.controller.dto.ResumoDto;
import br.com.projeto.financas.modelo.Despesa;
import br.com.projeto.financas.modelo.Receita;
import br.com.projeto.financas.repository.DespesaRepository;
import br.com.projeto.financas.repository.ReceitaRepository;

@RestController
@RequestMapping("/resumo")
public class ResumoController {

	@Autowired
	private ReceitaRepository receitaRepository;
	
	@Autowired
	private DespesaRepository despesaRepository;
	
	@GetMapping("/{ano}/{mes}")
	public ResumoDto resumoMesAno (@PathVariable Integer ano, @PathVariable Integer mes) {
		List<Receita> receitas = receitaRepository.buscaData(mes, ano);
		List<Despesa> despesas = despesaRepository.buscaData(mes, ano);
		return new ResumoDto(receitas, despesas);
	}

	
	
}
