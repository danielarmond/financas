package br.com.projeto.financas.controller.form;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.projeto.financas.modelo.Categoria;
import br.com.projeto.financas.modelo.Despesa;

public class DespesaForm {

	@NotNull @NotEmpty
	private String descricao;
	
	@NotNull
	private Long valor;
	
	@NotNull
	private LocalDate data;
	
	private Categoria categoria;
	
	public DespesaForm() {
	}

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

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}
	
	
	
	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Despesa converter() {
		return new Despesa(descricao, valor, data, categoria);
	}
}
