package com.knight.estoque.daos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
				"SOA Aplicado Integrando com web services e al�m",
				Arrays.asList("Alexandre Saudate"), "Casa do C�digo", 2012,
				"...");
		return ebook;
	}

	private Livro getLivroGuiaDoProgramador() {
		Livro livro = new Livro();
		livro.setAnoDePublicacao(2012);
		livro.setAutores(Arrays.asList("Paulo Silveira", "Adriano Almeida"));
		livro.setEditora("Casa do C�digo");
		livro.setNome("Guia do Programador");
		livro.setResumo("V� do \"nunca programei\" a ...");
		return livro;
	}

	private Livro getLivroRubyOnRails() {
		Livro livro2 = new Livro();
		livro2.setAnoDePublicacao(2012);
		livro2.setAutores(Arrays.asList("Vin�cius Baggio Fuentes"));
		livro2.setEditora("Casa do C�digo");
		livro2.setNome("Ruby On Rails");
		livro2.setResumo("Crie rapidamente aplica��es web");
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
