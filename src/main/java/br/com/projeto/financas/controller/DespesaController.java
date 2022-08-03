package br.com.projeto.financas.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.projeto.financas.controller.dto.DespesaDto;
import br.com.projeto.financas.controller.form.DespesaForm;
import br.com.projeto.financas.modelo.Despesa;
import br.com.projeto.financas.repository.DespesaRepository;

@RestController
@RequestMapping("/despesas")
public class DespesaController {
	
	@Autowired
	private DespesaRepository despesaRepository;

	@GetMapping()
	public List<DespesaDto> lista(){
		List<Despesa> despesas = despesaRepository.findAll();
		return DespesaDto.converter(despesas);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DespesaDto> detalhar (@PathVariable Long id) {
		Optional<Despesa> despesa = despesaRepository.findById(id);
		if(despesa.isPresent()) {
		return ResponseEntity.ok(new DespesaDto (despesa.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<DespesaDto> cadastrar(@RequestBody @Valid DespesaForm despesaForm, UriComponentsBuilder uriBuilder) {
		Despesa despesa = despesaForm.converter();
		despesaRepository.save(despesa);		
		URI uri = uriBuilder.path("/receitas/{id}").buildAndExpand(despesa.getId()).toUri();
		return ResponseEntity.created(uri).body(new DespesaDto(despesa));
}	
}
