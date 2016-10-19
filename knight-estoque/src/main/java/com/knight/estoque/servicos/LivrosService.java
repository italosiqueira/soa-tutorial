package com.knight.estoque.servicos;

import java.util.List;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFactory;
import javax.xml.soap.SOAPFault;
import javax.xml.ws.Endpoint;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
import javax.xml.ws.soap.SOAPFaultException;

import com.knight.estoque.modelos.Livro;
import com.knight.estoque.modelos.Usuario;

@WebService
@Stateless
public class LivrosService {

	@PersistenceContext
	private EntityManager em;

	@WebResult(name = "livro")
	public List<Livro> listarLivros() {
		return em.createQuery(
				"SELECT DISTINCT l FROM Livro l LEFT JOIN FETCH l.autores ORDER BY l.id",
				Livro.class).getResultList();
	}

	@RequestWrapper(className = "com.knight.estoque.servicos.jaxws.ListarLivrosPaginacao", localName = "listarLivrosPaginacao")
	@ResponseWrapper(className = "com.knight.estoque.servicos.jaxws.ListarLivrosPaginacaoResponse", localName = "livrosPaginados")
	@WebResult(name = "livro")
	@WebMethod(operationName = "listarLivrosPaginacao")
	public List<Livro> listarLivros(Integer numeroDaPagina,
			Integer tamanhoDaPagina) {
		TypedQuery<Livro> query = em.createQuery(
				"SELECT DISTINCT l FROM Livro l LEFT JOIN FETCH l.autores ORDER BY l.id",
				Livro.class);

		query.setFirstResult(numeroDaPagina * tamanhoDaPagina);

		query.setMaxResults(tamanhoDaPagina);

		return query.getResultList();
	}

	public void criarLivro(@WebParam(name = "livro") Livro livro,
			@WebParam(name = "usuario", header = true) Usuario usuario)
			throws UsuarioNaoAutorizadoException, SOAPException {
		if (usuario.getLogin().equals("soa")
				&& usuario.getSenha().equals("soa")) {
			em.persist(livro);
		} else if (usuario.getNome().equals("faultCode")) {

			/*
			 * Alternative exception action. Recommended when one wishes to
			 * address net traffic volume, since it allows more control over
			 * what will be sent, or have more control over SOAP Exceptions.
			 */
			SOAPFault soapFault = SOAPFactory.newInstance().createFault(
					"Não autorizado",
					new QName(SOAPConstants.URI_NS_SOAP_1_1_ENVELOPE,
							"Client.autorizacao"));

			soapFault
					.setFaultActor("http://servicos.estoque.knight.com/LivrosService");

			throw new SOAPFaultException(soapFault);

		} else {
			throw new UsuarioNaoAutorizadoException("Não autorizado");
		}
	}

	public static void main(String[] args) {
		Endpoint.publish("http://localhost:8080/livros", new LivrosService());

		System.out.println("Serviço inicializado!");
	}

}
