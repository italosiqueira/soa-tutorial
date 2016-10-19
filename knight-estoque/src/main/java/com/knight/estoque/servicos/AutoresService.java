package com.knight.estoque.servicos;

import java.util.List;

import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.xml.ws.Endpoint;

import com.knight.estoque.modelos.Autor;

@WebService
@Stateless
public class AutoresService {
	
	@PersistenceContext
	private EntityManager em;

	public List<Autor> listarAutores() {		
		return em.createQuery("SELECT a FROM Autor a", Autor.class).getResultList();
	}

	public static void main(String[] args) {
		Endpoint.publish("http://localhost:8080/autores", new AutoresService());

		System.out.println("Serviço inicializado!");
	}

}
