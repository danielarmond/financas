package br.com.projeto.financas.controller.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import br.com.projeto.financas.modelo.Receita;

public class ReceitaDto {

	private String descricao;
	private Long valor;
	private LocalDate data;
	
	
	public ReceitaDto(Receita receita) {
		this.descricao = receita.getDescricao();
		this.valor = receita.getValor();
		this.data = receita.getData();
	}


	public String getDescricao() {
		return descricao;
	}


	public Long getValor() {
		return valor;
	}


	public LocalDate getData() {
		return data;
	}

	public static List<ReceitaDto> converter(List<Receita> receitas) {
		return receitas.stream().map(ReceitaDto::new).collect(Collectors.toList());
	}
	
	
	
}
