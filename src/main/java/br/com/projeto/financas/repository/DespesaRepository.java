package br.com.projeto.financas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.projeto.financas.modelo.Despesa;

public interface DespesaRepository extends JpaRepository<Despesa, Long>{

	@Query(value = "SELECT u FROM Despesa u WHERE u.descricao = ?1 AND MONTH (u.data) = ?2 AND YEAR (u.data) = ?3")
	List<Despesa> buscaDescricaoEData(String descricao, int mes, int ano); 
	
	@Query(value = "SELECT u FROM Despesa u WHERE u.descricao = ?1 AND MONTH (u.data) = ?2 AND YEAR (u.data) = ?3 AND u.id != ?4")
	List<Despesa> buscaDescricaoDataId(String descricao, int mes, int ano, Long id); 
}
