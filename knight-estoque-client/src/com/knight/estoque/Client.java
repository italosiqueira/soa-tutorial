package com.knight.estoque;

import java.util.List;

import com.knight.estoque.servicos.ListagemLivros;
import com.knight.estoque.servicos.ListagemLivrosService;
import com.knight.estoque.servicos.Livro;

public class Client {

	public static void main(String[] args) {

		ListagemLivrosService listagemLivrosService = new ListagemLivrosService(
				Client.class.getResource("/livros.wsdl"));
		ListagemLivros listagemLivros = listagemLivrosService
				.getListagemLivrosPort();
		List<Livro> livros = listagemLivros.listarLivrosPaginacao(0, 2);
		for (Livro livro : livros) {
			System.out.println("Nome do livro: " + livro.getNome());
		}
	}
}
