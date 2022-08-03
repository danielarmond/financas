package br.com.projeto.financas.controller.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import br.com.projeto.financas.modelo.Despesa;

public class DespesaDto {

	private String descricao;
	private Long valor;
	private LocalDate data;
	
	
	public DespesaDto(Despesa despesa) {
		super();
		this.descricao = despesa.getDescricao();
		this.valor = despesa.getValor();
		this.data = despesa.getData();
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
	
	public static List<DespesaDto> converter(List<Despesa> despesas) {
		return despesas.stream().map(DespesaDto::new).collect(Collectors.toList());
	}
	
}
