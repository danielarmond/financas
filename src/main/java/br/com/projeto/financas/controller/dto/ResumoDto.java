package br.com.projeto.financas.controller.dto;

import java.util.List;

import br.com.projeto.financas.modelo.Despesa;
import br.com.projeto.financas.modelo.Receita;

public class ResumoDto {

	private List<Receita> receitas;
	private List<Despesa> despesas;
	private Long valorTotalReceitas;
	private Long valorTotalDespesas;
	private Long saldoMes;
	
		

	public List<Receita> getReceitas() {
		return receitas;
	}

	public void setReceitas(List<Receita> receitas) {
		this.receitas = receitas;
	}

	public List<Despesa> getDespesas() {
		return despesas;
	}

	public void setDespesas(List<Despesa> despesas) {
		this.despesas = despesas;
	}

	public Long getValorTotalReceitas() {
		return valorTotalReceitas;
	}

	public void setValorTotalReceitas(Long valorTotalReceitas) {
		this.valorTotalReceitas = valorTotalReceitas;
	}

	public Long getValorTotalDespesas() {
		return valorTotalDespesas;
	}

	public void setValorTotalDespesas(Long valorTotalDespesas) {
		this.valorTotalDespesas = valorTotalDespesas;
	}

	public Long getSaldoMes() {
		return saldoMes;
	}

	public void setSaldoMes(Long saldoMes) {
		this.saldoMes = saldoMes;
	}

	public ResumoDto(List<Receita> receitas, List<Despesa> despesas) {

		Long valorTotalReceitas = receitas.stream().mapToLong(receita -> receita.getValor()).sum();
		Long valorTotalDespesas = despesas.stream().mapToLong(despesa -> despesa.getValor()).sum();
		Long saldoMes = valorTotalReceitas - valorTotalDespesas;
		
		this.receitas = receitas;
		this.despesas = despesas;
		this.valorTotalReceitas = valorTotalReceitas;
		this.valorTotalDespesas = valorTotalDespesas;
		this.saldoMes = saldoMes;
				
	}
	
}
