package com.knight.estoque.daos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.knight.estoque.modelos.Autor;
import com.knight.estoque.modelos.EBook;
import com.knight.estoque.modelos.Livro;

public class LivroDAO {

	public List<Livro> listarLivros() {

		List<Livro> livros = new ArrayList<Livro>();

		livros.add(getLivroGuiaDoProgramador());
		livros.add(getLivroRubyOnRails());
		livros.add(getEBookSoaAplicado());

		return livros;
	}

	private Livro getEBookSoaAplicado() {
		EBook ebook = new EBook(
				"SOA Aplicado Integrando com web services e além",
				Arrays.asList(new Autor("Alexandre Saudate", new Date()))
				, "Casa do Código", 2012,"...");
		return ebook;
	}

	private Livro getLivroGuiaDoProgramador() {
		Livro livro = new Livro();
		livro.setAnoDePublicacao(2012);
		livro.setAutores(Arrays.asList(new Autor("Paulo Silveira", new Date()), new Autor("Adriano Almeida", new Date())));
		livro.setEditora("Casa do Código");
		livro.setNome("Guia do Programador");
		livro.setResumo("Vá do \"nunca programei\" a ...");
		return livro;
	}

	private Livro getLivroRubyOnRails() {
		Livro livro2 = new Livro();
		livro2.setAnoDePublicacao(2012);
		livro2.setAutores(Arrays.asList(new Autor("Vinícius Baggio Fuentes", new Date())));
		livro2.setEditora("Casa do Código");
		livro2.setNome("Ruby On Rails");
		livro2.setResumo("Crie rapidamente aplicações web");
		return livro2;
	}

	public List<Livro> listarLivros(Integer numeroDaPagina,
			Integer tamanhoDaPagina) {
		// TODO Auto-generated method stub
		return this.listarLivros();
	}

	public void criarLivro(Livro livro) {
		// TODO Auto-generated method stub
	}

}
