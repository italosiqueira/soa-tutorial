package com.knight.estoque.modelos;

import java.util.Date;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlTransient;

@MappedSuperclass
public abstract class EntidadeModelo {
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAtualizacao;
	
	@XmlTransient
	public Date getDataAtualizacao() {
		return this.dataAtualizacao;
	}
	
	@PreUpdate
	@PrePersist
	protected void ajusatarDataAtualizacao() {
		this.dataAtualizacao = new Date();
	}
	
	
}
