package br.com.projeto.financas.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.projeto.financas.controller.dto.ReceitaDto;
import br.com.projeto.financas.controller.form.AtualizacaoReceitaForm;
import br.com.projeto.financas.controller.form.ReceitaForm;
import br.com.projeto.financas.modelo.Receita;
import br.com.projeto.financas.repository.ReceitaRepository;

@RestController
@RequestMapping("/receitas")
public class ReceitaController {
	
	@Autowired
	private ReceitaRepository receitaRepository;

	@GetMapping()
	public List<ReceitaDto> lista(){
		List<Receita> receitas = receitaRepository.findAll();
		return ReceitaDto.converter(receitas);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ReceitaDto> detalhar (@PathVariable Long id) {
		Optional<Receita> receita = receitaRepository.findById(id);
		if(receita.isPresent()) {
		return ResponseEntity.ok(new ReceitaDto (receita.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<ReceitaDto> cadastrar(@RequestBody @Valid ReceitaForm receitaForm, UriComponentsBuilder uriBuilder) {
		Receita receita = receitaForm.converter();
		receitaRepository.save(receita);		
		URI uri = uriBuilder.path("/receitas/{id}").buildAndExpand(receita.getId()).toUri();
		return ResponseEntity.created(uri).body(new ReceitaDto(receita));
}	
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<ReceitaDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoReceitaForm atualizacaoReceitaForm){
		
		Optional<Receita> optional = receitaRepository.findById(id);
		if(optional.isPresent()) {
			Receita receita = atualizacaoReceitaForm.atualizar(id, receitaRepository);
			return ResponseEntity.ok(new ReceitaDto(receita));
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity remover(@PathVariable Long id){
		Optional<Receita> optional = receitaRepository.findById(id);
		if(optional.isPresent()) {
			receitaRepository.deleteById(id);
			return ResponseEntity.ok().build(); 		
	}
		return ResponseEntity.notFound().build();
}
}
