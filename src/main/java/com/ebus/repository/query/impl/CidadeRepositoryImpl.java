package com.ebus.repository.query.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.ebus.model.Cidade;
import com.ebus.repository.filter.CidadeFilter;
import com.ebus.repository.query.CidadeRepositoryQuery;

@Repository
public class CidadeRepositoryImpl implements CidadeRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Page<Cidade> pesquisa(CidadeFilter filtro, Pageable pageable) {
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Cidade> criteria = builder.createQuery(Cidade.class);
		Root<Cidade> root = criteria.from(Cidade.class);
		
		criteria.where(criaRestricoes(filtro, builder, root));
		criteria.orderBy(builder.asc(root.get("nome")));
		
		TypedQuery<Cidade> query = manager.createQuery(criteria);
		addRestricoesDePaginacao(query, pageable);
		
		
		return new PageImpl<>(query.getResultList(), pageable, count(filtro));
	}
	
	@Override
	public Long count(CidadeFilter filtro) {

		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Cidade> root = criteria.from(Cidade.class);
		
		criteria.where(criaRestricoes(filtro, builder, root));
		
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}
	
	private Predicate[] criaRestricoes(CidadeFilter filtro, CriteriaBuilder builder,
			Root<Cidade> root) {
		
		List<Predicate> predicates = new ArrayList<>();
		
		if (!StringUtils.isEmpty(filtro.getNome())) {
			predicates.add(builder.like(
					builder.lower(root.get("nome")), "%" + filtro.getNome().toLowerCase() + "%"));
		}
		
		if (filtro.getEstado() != null) {
			predicates.add(builder.equal(root.get("estado"), filtro.getEstado()));
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
	private void addRestricoesDePaginacao(TypedQuery<Cidade> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;
		
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
	}

}
