package com.knight.estoque.modelos;

import java.util.List;

public class EBook extends Livro {
	
	private String formato = "PDF";

	public EBook() {
		super();
	}

	public EBook(String nome, List<String> autores, String editora,
			Integer anoDePublicacao, String resumo) {
		super(nome, autores, editora, anoDePublicacao, resumo);
	}

	public String getFormato() {
		return formato;
	}

	public void setFormato(String formato) {
		this.formato = formato;
	}

}
