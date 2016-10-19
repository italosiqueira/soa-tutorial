package com.knight.estoque.modelos;

import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpConnection;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpState;
import org.apache.commons.httpclient.methods.GetMethod;

@Entity
public class Autor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;

	private Date dataNascimento;

	public Autor() {

	}

	public Autor(String nome, Date dataNascimento) {
		super();
		this.nome = nome;
		this.dataNascimento = dataNascimento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	public List<URL> getRefs() throws HttpException, IOException {

		String autor = URLEncoder.encode(nome, "UTF-8");
		String searchString = new StringBuilder(
				"/ajax/services/search/web?v=1.0&q=%22").append(autor)
				.append("%22").toString();

		GetMethod getMethod = new GetMethod(searchString);
		HttpState httpState = new HttpState();
		HttpConnection httpConnection = new HttpConnection(
				"ajax.googleapis.com", 80);
		httpConnection.open();
		getMethod.setFollowRedirects(true);
		int result = getMethod.execute(httpState, httpConnection);

		if (result == 200) {
			List<URL> responseList = new ArrayList<>();
			JSONObject jsonObject = JSONObject.fromObject(getMethod
					.getResponseBodyAsString());
			JSONArray results = jsonObject.getJSONObject("responseData")
					.getJSONArray("results");
			for (int i = 0; i < results.size(); i++) {
				String urlCrua = results.getJSONObject(i).getString(
						"unescapedUrl");
				URL url = new URL(urlCrua);
				responseList.add(url);
			}
			return responseList;
		}

		return Collections.emptyList();

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dataNascimento == null) ? 0 : dataNascimento.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Autor other = (Autor) obj;
		if (dataNascimento == null) {
			if (other.dataNascimento != null)
				return false;
		} else if (!dataNascimento.equals(other.dataNascimento))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

}
