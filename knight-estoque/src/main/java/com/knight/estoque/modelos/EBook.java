package com.knight.estoque.modelos;

import java.util.List;

public class EBook extends Livro {
	
	private FormatoArquivo formato = FormatoArquivo.PDF;

	public EBook() {
		super();
	}

	public EBook(String nome, List<Autor> autores, String editora,
			Integer anoDePublicacao, String resumo) {
		super(nome, autores, editora, anoDePublicacao, resumo);
	}

	public FormatoArquivo getFormato() {
		return formato;
	}

	public void setFormato(FormatoArquivo formato) {
		this.formato = formato;
	}

}
