package br.com.projeto.financas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projeto.financas.modelo.Despesa;

public interface DespesaRepository extends JpaRepository<Despesa, Long>{

}
