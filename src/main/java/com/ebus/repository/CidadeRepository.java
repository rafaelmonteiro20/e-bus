package com.ebus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ebus.model.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Integer> {

	List<Cidade> findByNomeContainingIgnoreCase(String nome);
	
}
