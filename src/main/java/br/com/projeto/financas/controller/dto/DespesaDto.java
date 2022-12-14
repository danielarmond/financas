package br.com.projeto.financas.controller.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import br.com.projeto.financas.modelo.Categoria;
import br.com.projeto.financas.modelo.Despesa;

public class DespesaDto {

	private String descricao;
	private Long valor;
	private LocalDate data;
	private Categoria categoria;
	
	
	public DespesaDto(Despesa despesa) {
		super();
		this.descricao = despesa.getDescricao();
		this.valor = despesa.getValor();
		this.data = despesa.getData();
		this.categoria = despesa.getCategoria();
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
	
	
	
	public Categoria getCategoria() {
		return categoria;
	}


	public static List<DespesaDto> converter(List<Despesa> despesas) {
		return despesas.stream().map(DespesaDto::new).collect(Collectors.toList());
	}
	
}
