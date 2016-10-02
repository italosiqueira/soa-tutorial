
package com.knight.estoque.servicos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de criarLivro complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="criarLivro">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="livro" type="{http://servicos.estoque.knight.com/}livro" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "criarLivro", propOrder = {
    "livro"
})
public class CriarLivro {

    protected Livro livro;

    /**
     * Obtém o valor da propriedade livro.
     * 
     * @return
     *     possible object is
     *     {@link Livro }
     *     
     */
    public Livro getLivro() {
        return livro;
    }

    /**
     * Define o valor da propriedade livro.
     * 
     * @param value
     *     allowed object is
     *     {@link Livro }
     *     
     */
    public void setLivro(Livro value) {
        this.livro = value;
    }

}
