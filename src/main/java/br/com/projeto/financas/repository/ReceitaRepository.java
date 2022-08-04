package br.com.projeto.financas.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projeto.financas.modelo.Receita;

public interface ReceitaRepository extends JpaRepository<Receita, Long>{

	List<Receita> findByDescricao(String descricao);

	List<Receita> findByDescricaoAndData(String descricao, LocalDate data);


}