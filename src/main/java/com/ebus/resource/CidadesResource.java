package com.ebus.resource;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ebus.model.Cidade;
import com.ebus.repository.CidadesRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/cidades")
public class CidadesResource {

	@Autowired
	private CidadesRepository cidadesRepository;
	
	@GetMapping
	public List<Cidade> findAll() {
		return cidadesRepository.findAll();
	}
	
	@PostMapping
	public ResponseEntity<?> create(@Valid @RequestBody Cidade cidade) {
		cidade = cidadesRepository.save(cidade);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
							.buildAndExpand(cidade.getId()).toUri();
		
		return ResponseEntity.created(uri).body(cidade);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findOne(@PathVariable Integer id) {
		Cidade cidade = cidadesRepository.findOne(id);
		return cidade == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(cidade);
	}
	
}
