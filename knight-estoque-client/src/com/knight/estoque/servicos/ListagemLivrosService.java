
package com.knight.estoque.servicos;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "ListagemLivrosService", targetNamespace = "http://servicos.estoque.knight.com/", wsdlLocation = "http://localhost:8080/livros?wsdl")
public class ListagemLivrosService
    extends Service
{

    private final static URL LISTAGEMLIVROSSERVICE_WSDL_LOCATION;
    private final static WebServiceException LISTAGEMLIVROSSERVICE_EXCEPTION;
    private final static QName LISTAGEMLIVROSSERVICE_QNAME = new QName("http://servicos.estoque.knight.com/", "ListagemLivrosService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8080/livros?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        LISTAGEMLIVROSSERVICE_WSDL_LOCATION = url;
        LISTAGEMLIVROSSERVICE_EXCEPTION = e;
    }

    public ListagemLivrosService() {
        super(__getWsdlLocation(), LISTAGEMLIVROSSERVICE_QNAME);
    }

    public ListagemLivrosService(WebServiceFeature... features) {
        super(__getWsdlLocation(), LISTAGEMLIVROSSERVICE_QNAME, features);
    }

    public ListagemLivrosService(URL wsdlLocation) {
        super(wsdlLocation, LISTAGEMLIVROSSERVICE_QNAME);
    }

    public ListagemLivrosService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, LISTAGEMLIVROSSERVICE_QNAME, features);
    }

    public ListagemLivrosService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ListagemLivrosService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns ListagemLivros
     */
    @WebEndpoint(name = "ListagemLivrosPort")
    public ListagemLivros getListagemLivrosPort() {
        return super.getPort(new QName("http://servicos.estoque.knight.com/", "ListagemLivrosPort"), ListagemLivros.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ListagemLivros
     */
    @WebEndpoint(name = "ListagemLivrosPort")
    public ListagemLivros getListagemLivrosPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://servicos.estoque.knight.com/", "ListagemLivrosPort"), ListagemLivros.class, features);
    }

    private static URL __getWsdlLocation() {
        if (LISTAGEMLIVROSSERVICE_EXCEPTION!= null) {
            throw LISTAGEMLIVROSSERVICE_EXCEPTION;
        }
        return LISTAGEMLIVROSSERVICE_WSDL_LOCATION;
    }

}