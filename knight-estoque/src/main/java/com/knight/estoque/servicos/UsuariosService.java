package com.knight.estoque.servicos;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.knight.estoque.modelos.Usuario;
import com.knight.estoque.modelos.Usuarios;

@Path("/usuarios")
@Produces(MediaType.APPLICATION_XML)
@Consumes(MediaType.APPLICATION_XML)
@Stateless
public class UsuariosService {

	@PersistenceContext
	private EntityManager em;

	@GET
	public Usuarios listarUsuarios() {
		return new Usuarios(em.createQuery("SELECT u FROM Usuario u",
				Usuario.class).getResultList());
	}
	
	@GET
	@Path("/{id}")
	public Usuario find(@PathParam("id") Long id) {
		return em.find(Usuario.class, id);
	}
}
