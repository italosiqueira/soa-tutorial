package com.knight.estoque.modelos;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.knight.estoque.adaptadores.AdaptadorAutores;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({ EBook.class })
public class Livro {

	private String nome;

	@XmlElementWrapper(name = "autores")
	@XmlElement(name = "autor")
	@XmlJavaTypeAdapter(value = AdaptadorAutores.class)
	private List<Autor> autores;
	// private List<String> autores;

	private String editora;

	private Integer anoDePublicacao;

	private String resumo;

	/*
	 * This is a class level adapter configuration, used on class attributes:
	 * 
	 * @XmlJavaTypeAdapter(AdaptadorDate.class)
	 */
	private Date dataDeCriacao = new Date();

	public Livro() {

	}

	public Livro(String nome, List<Autor> autores, String editora,
			Integer anoDePublicacao, String resumo) {
		super();
		this.nome = nome;
		this.autores = autores;
		this.editora = editora;
		this.anoDePublicacao = anoDePublicacao;
		this.resumo = resumo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Autor> getAutores() {
		return autores;
	}

	public void setAutores(List<Autor> autores) {
		this.autores = autores;
	}

	public String getEditora() {
		return editora;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}

	public Integer getAnoDePublicacao() {
		return anoDePublicacao;
	}

	public void setAnoDePublicacao(Integer anoDePublicacao) {
		this.anoDePublicacao = anoDePublicacao;
	}

	public String getResumo() {
		return resumo;
	}

	public void setResumo(String resumo) {
		this.resumo = resumo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((anoDePublicacao == null) ? 0 : anoDePublicacao.hashCode());
		result = prime * result + ((autores == null) ? 0 : autores.hashCode());
		result = prime * result
				+ ((dataDeCriacao == null) ? 0 : dataDeCriacao.hashCode());
		result = prime * result + ((editora == null) ? 0 : editora.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Livro other = (Livro) obj;
		if (anoDePublicacao == null) {
			if (other.anoDePublicacao != null)
				return false;
		} else if (!anoDePublicacao.equals(other.anoDePublicacao))
			return false;
		if (autores == null) {
			if (other.autores != null)
				return false;
		} else if (!autores.equals(other.autores))
			return false;
		if (dataDeCriacao == null) {
			if (other.dataDeCriacao != null)
				return false;
		} else if (!dataDeCriacao.equals(other.dataDeCriacao))
			return false;
		if (editora == null) {
			if (other.editora != null)
				return false;
		} else if (!editora.equals(other.editora))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

}
