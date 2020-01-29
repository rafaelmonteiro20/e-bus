package com.ebus.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ebus.model.Cidade;
import com.ebus.repository.CidadeRepository;

@RestController
@RequestMapping("/cidades")
public class CidadesResource {

	@Autowired
	private CidadeRepository cidadeRepository;
	
	@GetMapping
	public List<Cidade> pesquisa() {
		return cidadeRepository.findAll();
	}
	
	@GetMapping(params = "autocomplete")
	public List<Cidade> pesquisaPorNome(@PathParam("nome") String nome) {
		return cidadeRepository.findByNomeContainingIgnoreCase(nome);
	}
	
	@PostMapping
	public ResponseEntity<?> salva(@Valid @RequestBody Cidade cidade) {
		cidade = cidadeRepository.save(cidade);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
							.buildAndExpand(cidade.getId()).toUri();
		
		return ResponseEntity.created(uri).body(cidade);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscaPorId(@PathVariable Integer id) {
		Optional<Cidade> cidade = cidadeRepository.findById(id);
		
		if (cidade.isPresent()) {
			return ResponseEntity.ok(cidade);
		}
		
		return ResponseEntity.notFound().build(); 
	}
	
}
