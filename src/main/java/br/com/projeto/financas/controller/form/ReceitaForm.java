package br.com.projeto.financas.controller.form;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ReceitaForm {

	@NotEmpty
	@NotNull
	private String descricao;
	
	@NotEmpty
	@NotNull
	private Long valor;
	
	@NotEmpty
	@NotNull
	
	private Date data;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getValor() {
		return valor;
	}

	public void setValor(Long valor) {
		this.valor = valor;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	
	
}
