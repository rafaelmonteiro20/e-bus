package com.ebus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ebus.model.Cidade;

public interface CidadesRepository extends JpaRepository<Cidade, Integer> {

}
