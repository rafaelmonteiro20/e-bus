package com.ebus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ebus.model.Cidade;
import com.ebus.repository.query.CidadeRepositoryQuery;

public interface CidadeRepository extends JpaRepository<Cidade, Integer>, CidadeRepositoryQuery {

}
