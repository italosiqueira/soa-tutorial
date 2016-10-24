package com.knight.estoque.servicos;

import java.net.URI;
import java.util.Date;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

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
	public Response find(@PathParam("id") Long id, @HeaderParam("If-Modified-Since") Date modifiedSince) {
		
		Usuario usuario = em.find(Usuario.class, id);
		
		if (usuario != null) {
			if (modifiedSince == null || modifiedSince != null && usuario.getDataAtualizacao().after(modifiedSince)) {
				return Response.ok(usuario).build();
			}
			
			return Response.notModified().build();
		}
		
		return Response.status(Status.NOT_FOUND).build();
	}
	
	@POST
	public Response create(@Context UriInfo uriInfo, Usuario usuario) {
		em.persist(usuario);
		UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
		URI location = uriBuilder.path("/{id}").build(usuario.getId());
		
		return Response.created(location).build();
	}
}
