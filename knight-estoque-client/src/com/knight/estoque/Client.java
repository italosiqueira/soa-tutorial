package com.knight.estoque;

import java.util.List;

import com.knight.estoque.servicos.ListagemLivros;
import com.knight.estoque.servicos.ListagemLivrosService;
import com.knight.estoque.servicos.Livro;

public class Client {

	public static void main(String[] args) {
		
		// Inicia a fábrica dos proxies
		ListagemLivrosService listagemLivrosFactory = new ListagemLivrosService();
		
		// Obtém um proxy
		ListagemLivros listagemLivros = listagemLivrosFactory
				.getListagemLivrosPort();
		// Executa o método remoto
		List<Livro> livros = listagemLivros.listarLivros();
		for (Livro livro : livros) {
			System.out.println("Nome: " + livro.getNome());
		}
	}

}
