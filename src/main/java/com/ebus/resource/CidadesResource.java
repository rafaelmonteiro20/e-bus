package com.ebus.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ebus.model.Cidade;
import com.ebus.model.filter.CidadeFilter;
import com.ebus.repository.CidadeRepository;
import com.ebus.util.RecursoCriado;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/cidades")
public class CidadesResource {

	@Autowired
	private CidadeRepository cidadeRepository;
	
	@GetMapping
	public Page<Cidade> pesquisa(CidadeFilter filtro, @PageableDefault(size = 5) Pageable pageable) {
		return cidadeRepository.pesquisa(filtro, pageable);
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
