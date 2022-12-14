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
import org.springframework.web.bind.annotation.RequestParam;
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
	public List<ReceitaDto> lista(@RequestParam(required = false) String descricao){
		
		if(descricao == null) {
			List<Receita> receitas = receitaRepository.findAll();
			return ReceitaDto.converter(receitas);
		}
		else {
			List<Receita> receitas = receitaRepository.buscaDescricao(descricao);
			return ReceitaDto.converter(receitas);
			
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ReceitaDto> detalhar (@PathVariable Long id) {
		Optional<Receita> receita = receitaRepository.findById(id);
		if(receita.isPresent()) {
		return ResponseEntity.ok(new ReceitaDto (receita.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/{ano}/{mes}")
	public List<ReceitaDto> listarMes (@PathVariable Integer ano, @PathVariable Integer mes) {
		List<Receita> receitas = receitaRepository.buscaData(mes, ano);
		return ReceitaDto.converter(receitas);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<ReceitaDto> cadastrar(@RequestBody @Valid ReceitaForm receitaForm, UriComponentsBuilder uriBuilder) {
		
		List<Receita> validacaoReceita = receitaRepository.buscaDescricaoEData(
				receitaForm.getDescricao(),
				receitaForm.getData().getMonthValue(),
				receitaForm.getData().getYear());
	
		if(validacaoReceita.isEmpty()) {				
			Receita receita = receitaForm.converter();
			receitaRepository.save(receita);		
			URI uri = uriBuilder.path("/receitas/{id}").buildAndExpand(receita.getId()).toUri();
			return ResponseEntity.created(uri).body(new ReceitaDto(receita));
		}		
		else {
			return ResponseEntity.notFound().build();
		}	
}	
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<ReceitaDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoReceitaForm atualizacaoReceitaForm){
		
		Optional<Receita> optional = receitaRepository.findById(id);
		if(optional.isPresent()) {
			List<Receita> validacaoReceita = receitaRepository.buscaDescricaoDataId(
					atualizacaoReceitaForm.getDescricao(),
					atualizacaoReceitaForm.getData().getMonthValue(),
					atualizacaoReceitaForm.getData().getYear(),
					id);	
			if(validacaoReceita.isEmpty()) {			
				Receita receita = atualizacaoReceitaForm.atualizar(id, receitaRepository);
				return ResponseEntity.ok(new ReceitaDto(receita));
			}
			else {
				return ResponseEntity.notFound().build();
		}
		}	
		else {
			return ResponseEntity.notFound().build();
			}
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id){
		Optional<Receita> optional = receitaRepository.findById(id);
		if(optional.isPresent()) {
			receitaRepository.deleteById(id);
			return ResponseEntity.ok().build(); 		
	}
		return ResponseEntity.notFound().build();
}
}
