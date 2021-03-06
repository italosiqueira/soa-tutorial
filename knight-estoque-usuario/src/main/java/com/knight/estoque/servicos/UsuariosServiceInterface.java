package com.knight.estoque.servicos;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.knight.estoque.modelos.Usuario;

@Path("/usuarios")
@Produces(MediaType.APPLICATION_XML)
@Consumes(MediaType.APPLICATION_XML)
public interface UsuariosServiceInterface {

	public static final String CAMPO_DESCRICAO_IMAGEM = "Descricao";

	public static final String PARAM_INICIO = "inicio";

	public static final String PARAM_TAMANHO_PAGINA = "tamanhoPagina";

	@GET
	public abstract Response listarUsuarios(
			@HeaderParam("If-Modified-Since") Date modifiedSince,
			@QueryParam(PARAM_INICIO) @DefaultValue("0") Integer inicio,
			@QueryParam(PARAM_TAMANHO_PAGINA) @DefaultValue("20") Integer tamanhoPagina,
			@Context UriInfo uriInfo);

	@GET
	@Path("/{id}")
	public abstract Response find(@PathParam("id") Long id,
			@HeaderParam("If-Modified-Since") Date modifiedSince);

	@POST
	public abstract Response create(@Context UriInfo uriInfo, Usuario usuario);

	@GET
	@Path("/{id}")
	@Produces("image/*")
	public abstract Response recupearImagem(@PathParam("id") Long id,
			@HeaderParam("If-Modified-Since") Date modifiedSince);
	
	@PUT
	@Path("/{id}")
	@Consumes("image/*")
	public abstract Response adicionarImagem(@PathParam("id") Long id,
			@HeaderParam("Descricao") String descricao,
			@Context HttpServletRequest httpServletRequest, byte[] dadosImagem);
	
	@PUT
	public abstract Response update(Usuario usuario);
	
	@PUT
	@Path("/{id}")
	public abstract Response update(@PathParam("id") Long id, Usuario usuario);
	
	@DELETE
	public abstract Response delete (Usuario usuario);
	
	@DELETE
	@Path("/{id}")
	public abstract Response delete (@PathParam("id") Long id);
	
	

}