package com.knight.estoque.servicos;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.soap.SOAPException;
import javax.xml.ws.Endpoint;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import com.knight.estoque.daos.LivroDAO;
import com.knight.estoque.modelos.Livro;
import com.knight.estoque.modelos.Usuario;

@WebService
public class ListagemLivros {

	@WebResult(name = "livro")
	public List<Livro> listarLivros() {
		LivroDAO livroDAO = obterDAO();

		return livroDAO.listarLivros();
	}

	@RequestWrapper(className = "com.knight.estoque.servicos.jaxws.ListarLivrosPaginacao", localName = "listarLivrosPaginacao")
	@ResponseWrapper(className = "com.knight.estoque.servicos.jaxws.ListarLivrosPaginacaoResponse", localName = "livrosPaginados")
	@WebResult(name = "livro")
	@WebMethod(operationName = "listarLivrosPaginacao")
	public List<Livro> listarLivros(Integer numeroDaPagina,
			Integer tamanhoDaPagina) {
		LivroDAO livroDAO = obterDAO();

		return livroDAO.listarLivros(numeroDaPagina, tamanhoDaPagina);
	}

	public void criarLivro(@WebParam(name = "livro") Livro livro,
			@WebParam(name = "usuario", header = true) Usuario usuario)
			throws UsuarioNaoAutorizadoException, SOAPException {
		if (usuario.getLogin().equals("soa")
				&& usuario.getSenha().equals("soa")) {
			obterDAO().criarLivro(livro);
		} else {
			 throw new UsuarioNaoAutorizadoException("Não autorizado");

			/*
			 * Alternative exception action. Recommended when one wishes to address
			 * net traffic volume, since it allows more control over what will be sent, 
			 * or have more control over SOAP Exceptions.
			 * 
			 * SOAPFault soapFault = SOAPFactory.newInstance().createFault(
			 * 					"Não autorizado",
			 * 					new QName(SOAPConstants.URI_NS_SOAP_1_1_ENVELOPE,
			 * 							"Client.autorizacao"));
			 * 			
			 * soapFault.setFaultActor("http://servicos.estoque.knight.com/LivrosService");
			 * 			
			 * throw new SOAPFaultException(soapFault);
			 */
		}
	}

	private LivroDAO obterDAO() {

		return new LivroDAO();
	}

	public static void main(String[] args) {
		Endpoint.publish("http://localhost:8080/livros", new ListagemLivros());

		System.out.println("Serviço inicializado!");
	}

}
