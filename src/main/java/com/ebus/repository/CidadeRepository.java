package com.ebus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ebus.model.Cidade;
import com.ebus.repository.query.CidadeRepositoryQuery;

public interface CidadeRepository extends JpaRepository<Cidade, Integer>, CidadeRepositoryQuery {

	List<Cidade> findByNomeContainingIgnoreCase(String nome);
	
}
