package br.com.projeto.financas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.financas.controller.dto.DespesaDto;
import br.com.projeto.financas.modelo.Despesa;
import br.com.projeto.financas.repository.DespesaRepository;

@RestController
public class DespesaController {
	
	@Autowired
	private DespesaRepository despesaRepository;

	@RequestMapping("/despesas")
	public List<DespesaDto> lista(){
		List<Despesa> despesas = despesaRepository.findAll();
		return DespesaDto.converter(despesas);
	}
}
