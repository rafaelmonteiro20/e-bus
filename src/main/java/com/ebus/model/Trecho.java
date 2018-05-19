package com.ebus.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "trechos")
@SequenceGenerator(name = "seq_trecho_id", sequenceName = "seq_trecho_id", allocationSize = 1)
public class Trecho {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_trecho_id")
	@Column(name = "id_trecho")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "id_cidade_origem")
	private Cidade origem;
	
	@ManyToOne
	@JoinColumn(name = "id_cidade_destino")
	private Cidade destino;
	
	private BigDecimal distancia;
	
	
	public Trecho() {

	}

	public Trecho(Cidade origem, Cidade destino, BigDecimal distancia) {
		this.origem = origem;
		this.destino = destino;
		this.distancia = distancia;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Cidade getOrigem() {
		return origem;
	}

	public void setOrigem(Cidade origem) {
		this.origem = origem;
	}

	public Cidade getDestino() {
		return destino;
	}

	public void setDestino(Cidade destino) {
		this.destino = destino;
	}

	public BigDecimal getDistancia() {
		return distancia;
	}

	public void setDistancia(BigDecimal distancia) {
		this.distancia = distancia;
	}

	@Override
	public String toString() {
		return "Trecho [" + origem + " - " + destino + ", " + distancia + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Trecho other = (Trecho) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
