package com.ebus.resource;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ebus.model.Trecho;
import com.ebus.repository.TrechoRepository;
import com.ebus.util.RecursoCriado;

@RestController
@RequestMapping("/trechos")
public class TrechosResource {

	@Autowired
	private TrechoRepository trechoRepository;
	
	@PostMapping
	public ResponseEntity<?> salva(@Valid @RequestBody Trecho trecho) {
		trecho = trechoRepository.save(trecho);
		return ResponseEntity.created(RecursoCriado.location(trecho.getId()))
					.body(trecho);
	}
	
	@GetMapping
	public List<Trecho> pesquisa() {
		return trechoRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscaPorId(@PathVariable Integer id) {
		Optional<Trecho> trecho = trechoRepository.findById(id);
		
		if (trecho.isPresent()) {
			return ResponseEntity.ok(trecho.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
}
