package com.ebus.resource;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ebus.model.Trecho;
import com.ebus.repository.TrechoRepository;

@RestController
@RequestMapping("/trechos")
public class TrechosResource {

	@Autowired
	private TrechoRepository trechoRepository;
	
	@PostMapping
	public ResponseEntity<?> salva(@Valid @RequestBody Trecho trecho) {
		trecho = trechoRepository.save(trecho);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
			.buildAndExpand(trecho.getId()).toUri();
	
		return ResponseEntity.created(location).body(trecho);
	}
	
	@GetMapping
	public List<Trecho> pesquisa() {
		return trechoRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscaPorId(@PathVariable Integer id) {
		Trecho trecho = trechoRepository.findOne(id);
		return trecho == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(trecho);
	}
	
}
