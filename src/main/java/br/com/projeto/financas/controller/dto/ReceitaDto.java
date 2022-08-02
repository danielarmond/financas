package br.com.projeto.financas.controller.dto;

import java.util.Date;

import br.com.projeto.financas.modelo.Receita;

public class ReceitaDto {

	private Long id;
	private String descricao;
	private Long valor;
	private Date data;
	
	
	public ReceitaDto(Receita receita) {
		this.id = receita.getId();
		this.descricao = receita.getDescricao();
		this.valor = receita.getValor();
		this.data = receita.getData();
	}


	public Long getId() {
		return id;
	}


	public String getDescricao() {
		return descricao;
	}


	public Long getValor() {
		return valor;
	}


	public Date getData() {
		return data;
	}
	
	
	
}
