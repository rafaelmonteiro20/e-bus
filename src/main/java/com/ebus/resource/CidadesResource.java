package com.ebus.resource;

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

import com.ebus.model.Cidade;
import com.ebus.repository.CidadeRepository;
import com.ebus.util.RecursoCriado;

@RestController
@RequestMapping("/cidades")
public class CidadesResource {

	@Autowired
	private CidadeRepository cidadeRepository;
	
	@GetMapping
	public List<Cidade> pesquisa() {
		return cidadeRepository.findAll();
	}
	
	@PostMapping
	public ResponseEntity<?> salva(@Valid @RequestBody Cidade cidade) {
		cidade = cidadeRepository.save(cidade);
		return ResponseEntity.created(RecursoCriado.location(cidade.getId()))
					.body(cidade);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscaPorId(@PathVariable Integer id) {
		Cidade cidade = cidadeRepository.findOne(id);
		return cidade == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(cidade);
	}
	
}
