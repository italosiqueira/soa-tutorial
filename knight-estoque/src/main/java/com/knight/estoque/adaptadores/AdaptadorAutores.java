package com.knight.estoque.adaptadores;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import com.knight.estoque.modelos.Autor;

public class AdaptadorAutores extends XmlAdapter<String, Autor> {

	@Override
	public Autor unmarshal(String v) throws Exception {
		return new Autor(v, null);
	}

	@Override
	public String marshal(Autor v) throws Exception {
		return v.getNome();
	}

}
