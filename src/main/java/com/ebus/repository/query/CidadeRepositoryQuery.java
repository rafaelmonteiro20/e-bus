package com.ebus.repository.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ebus.model.Cidade;
import com.ebus.model.filter.CidadeFilter;

public interface CidadeRepositoryQuery {

	Page<Cidade> pesquisa(CidadeFilter filtro, Pageable pageable);
	
	Long count(CidadeFilter filtro);
	
}
