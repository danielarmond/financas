package br.com.projeto.financas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projeto.financas.modelo.Receita;

public interface ReceitaRepository extends JpaRepository<Receita, Long>{

}
