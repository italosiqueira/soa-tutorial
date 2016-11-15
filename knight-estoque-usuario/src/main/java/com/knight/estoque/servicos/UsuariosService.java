package com.knight.estoque.servicos;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import com.knight.estoque.modelos.Imagem;
import com.knight.estoque.modelos.Usuario;
import com.knight.estoque.modelos.Usuarios;
import com.knight.estoque.modelos.rest.Link;

@Stateless
public class UsuariosService implements UsuariosServiceInterface {

	@PersistenceContext
	private EntityManager em;

	public Response listarUsuarios(Date modifiedSince, Integer inicio,
			Integer tamanhoPagina, UriInfo uriInfo) {

		// TODO Validate variable values inicio and tamanhoPagina
		Collection<Usuario> usuarios = em
				.createQuery("SELECT u FROM Usuario u", Usuario.class)
				.setFirstResult(inicio).setMaxResults(tamanhoPagina.intValue())
				.getResultList();

		// Recuperamos o número de usuários presentes em nossa base
		// para que possamos realizar o cálculo de páginas
		Long numeroUsuarios = em.createQuery("SELECT COUNT(u) FROM Usuario u",
				Long.class).getSingleResult();

		boolean atualizado = false;

		if (modifiedSince != null) {
			for (Usuario usuario : usuarios) {
				if (usuario.getDataAtualizacao().after(modifiedSince)) {
					atualizado = true;
					break;
				}
			}
		} else {
			atualizado = true;
		}

		if (atualizado) {
			for (Usuario usuario : usuarios) {
				Link link = criarLinkImagemUsuario(usuario);
				usuario.adicionarLink(link);
			}

			return Response.ok(
					new Usuarios(usuarios, criarLinksUsuarios(uriInfo,
							tamanhoPagina, inicio, numeroUsuarios))).build();
		} else {
			return Response.notModified().build();
		}
	}

	private Link criarLinkImagemUsuario(Usuario usuario) {

		String uri = UriBuilder.fromPath("usuarios/{id}")
				.build(usuario.getId()).toString();
		String rel = "imagem";
		String type = "image/*";
		return new Link(uri, rel, type);

	}

	private Link[] criarLinksUsuarios(UriInfo uriInfo, Integer tamanhoPagina,
			Integer inicio, Long numeroUsuarios) {
		Collection<Link> links = new ArrayList<Link>();

		double numeroUsuariosDouble = numeroUsuarios;

		double tamanhoPaginaDouble = tamanhoPagina;

		Long numeroPaginas = (long) Math.ceil(numeroUsuariosDouble
				/ tamanhoPaginaDouble);

		Long paginaAtual = new Long(inicio / tamanhoPagina);

		Link linkPrimeiraPagina = new Link(UriBuilder
				.fromPath(uriInfo.getPath()).queryParam(PARAM_INICIO, 0)
				.queryParam(PARAM_TAMANHO_PAGINA, tamanhoPagina).build()
				.toString(), "primeiraPagina");

		links.add(linkPrimeiraPagina);

		if (paginaAtual > 0) {

			Link linkPaginaAnterior = new Link(
					UriBuilder
							.fromPath(uriInfo.getPath())
							.queryParam(PARAM_INICIO,
									(paginaAtual - 1) * tamanhoPagina)
							.queryParam(PARAM_TAMANHO_PAGINA, tamanhoPagina)
							.build().toString(), "paginaAnterior");

			links.add(linkPaginaAnterior);

		} else {
			Link linkPaginaAnterior = new Link(UriBuilder
					.fromPath(uriInfo.getPath())
					.queryParam(PARAM_INICIO,
							(numeroPaginas - 1) * tamanhoPagina)
					.queryParam(PARAM_TAMANHO_PAGINA, tamanhoPagina).build()
					.toString(), "paginaAnterior");

			links.add(linkPaginaAnterior);
		}

		if (paginaAtual < (numeroPaginas - 1)) {

			Link linkProximaPagina = new Link(
					UriBuilder
							.fromPath(uriInfo.getPath())
							.queryParam(PARAM_INICIO,
									(paginaAtual + 1) * tamanhoPagina)
							.queryParam(PARAM_TAMANHO_PAGINA, tamanhoPagina)
							.build().toString(), "proximaPagina");

			links.add(linkProximaPagina);

		}

		Link linkUltimaPagina = new Link(UriBuilder.fromPath(uriInfo.getPath())
				.queryParam(PARAM_INICIO, (numeroPaginas - 1) * tamanhoPagina)
				.queryParam(PARAM_TAMANHO_PAGINA, tamanhoPagina).build()
				.toString(), "ultimaPagina");

		links.add(linkUltimaPagina);

		return links.toArray(new Link[] {});
	}

	public Response find(Long id, Date modifiedSince) {

		Usuario usuario = em.find(Usuario.class, id);

		if (usuario != null) {
			if (modifiedSince == null || modifiedSince != null
					&& usuario.getDataAtualizacao().after(modifiedSince)) {
				return Response.ok(usuario).build();
			}

			return Response.notModified().build();
		}

		return Response.status(Status.NOT_FOUND).build();
	}

	public Response create(UriInfo uriInfo, Usuario usuario) {
		em.persist(usuario);
		UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
		URI location = uriBuilder.path("/{id}").build(usuario.getId());

		return Response.created(location).build();
	}

	public Response recupearImagem(Long id, Date modifiedSince) {

		Usuario usuario = em.find(Usuario.class, id);

		if (usuario != null) {

			Imagem imagem = usuario.getImagem();

			if (modifiedSince != null
					&& imagem.getDataAtualizacao().before(modifiedSince)) {
				return Response.notModified().build();
			}

			return Response.ok(imagem.getDados(), imagem.getTipo())
					.header("Descricao", imagem.getDescricao()).build();
		}

		return Response.status(Status.NOT_FOUND).build();
	}

	public Response adicionarImagem(Long id, String descricao,
			HttpServletRequest httpServletRequest, byte[] dadosImagem) {

		Usuario usuario = em.find(Usuario.class, id);

		if (usuario == null) {
			return Response.status(Status.NOT_FOUND).build();
		}

		Imagem imagem = new Imagem();
		imagem.setDados(dadosImagem);
		imagem.setDescricao(descricao);
		imagem.setTipo(httpServletRequest.getContentType());
		usuario.setImagem(imagem);
		em.merge(usuario);

		return Response.noContent().build();

	}

	public Response update(Usuario usuario) {
		usuario = em.merge(usuario);
		return Response.noContent().build();
	}

	public Response update(Long id, Usuario usuario) {
		usuario.setId(id);
		return update(usuario);
	}

	public Response delete(Usuario usuario) {
		usuario = em.find(Usuario.class, usuario.getId());
		if (usuario == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		em.remove(usuario);
		return Response.noContent().build();
	}

	public Response delete(Long id) {
		Usuario usuario = new Usuario();
		usuario.setId(id);
		return delete(usuario);
	}
}
