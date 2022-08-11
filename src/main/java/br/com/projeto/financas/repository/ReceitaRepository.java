package br.com.projeto.financas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.projeto.financas.modelo.Receita;

public interface ReceitaRepository extends JpaRepository<Receita, Long>{
	
	@Query(value = "SELECT u FROM Receita u WHERE u.descricao = ?1 AND MONTH (u.data) = ?2 AND YEAR (u.data) = ?3")
	List<Receita> buscaDescricaoEData(String descricao, int mes, int ano);
	
	@Query(value = "SELECT u FROM Receita u WHERE u.descricao = ?1 AND MONTH (u.data) = ?2 AND YEAR (u.data) = ?3 AND u.id != ?4")
	List<Receita> buscaDescricaoDataId(String descricao, int mes, int ano, Long id);
	
	@Query(value = "SELECT u FROM Receita u WHERE MONTH (u.data) = ?1 AND YEAR (u.data) = ?2")
	List<Receita> buscaData(Integer mes, Integer ano);
	
	@Query(value = "SELECT u FROM Receita u WHERE u.descricao = ?1")
	List<Receita> buscaDescricao(String descricao);
	}


