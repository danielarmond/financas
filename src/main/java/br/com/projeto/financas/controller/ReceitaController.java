package br.com.projeto.financas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.financas.controller.dto.ReceitaDto;
import br.com.projeto.financas.modelo.Receita;
import br.com.projeto.financas.repository.ReceitaRepository;

@RestController
public class ReceitaController {
	
	@Autowired
	private ReceitaRepository receitaRepository;

	@RequestMapping("/receitas")
	public List<ReceitaDto> lista(){
		List<Receita> receitas = receitaRepository.findAll();
		return ReceitaDto.converter(receitas);
	}
}
