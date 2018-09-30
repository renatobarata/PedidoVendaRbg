package br.com.rbg.pedidovenda.repository.filter;

import java.io.Serializable;

public class EstadoFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nome;
	private String sigla;
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getSigla() {
		return sigla;
	}
	
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	
}
