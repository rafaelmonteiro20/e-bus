package com.ebus.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ebus.model.Trecho;
import com.ebus.repository.TrechosRepository;

@RestController
@RequestMapping("/trechos")
public class TrechosResource {

	@Autowired
	private TrechosRepository trechosRepository;
	
	@GetMapping
	public List<Trecho> listar() {
		return trechosRepository.findAll();
	}
	
}
