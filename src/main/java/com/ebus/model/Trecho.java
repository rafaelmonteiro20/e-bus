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
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "trechos")
@SequenceGenerator(name = "trechos_sequence", sequenceName = "trechos_sequence", allocationSize = 1)
public class Trecho {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trechos_sequence")
	@Column(name = "id_trecho")
	private Integer id;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_cidade_origem")
	private Cidade origem;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_cidade_destino")
	private Cidade destino;
	
	@NotNull
	@DecimalMin("10.0")
	private BigDecimal distancia;
	
	
	public Trecho() {

	}
	
	public Trecho(Integer id) {
		this.id = id;
	}

	public Trecho(Cidade origem, Cidade destino, BigDecimal distancia) {
		this.origem = origem;
		this.destino = destino;
		this.distancia = distancia;
	}

	public Integer getId() {
		return id;
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
