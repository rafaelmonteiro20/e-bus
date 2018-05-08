package com.ebus.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "cidades")
@SequenceGenerator(name = "seq_cidade_id", sequenceName = "seq_cidade_id", allocationSize = 1)
public class Cidade {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_cidade_id")
	@Column(name = "id_cidade")
	private Integer id;
	
	@NotNull
	@Size(min = 2, max = 50)
	private String nome;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private Estado estado;
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public Estado getEstado() {
		return estado;
	}
	
	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return nome;
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
		Cidade other = (Cidade) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
