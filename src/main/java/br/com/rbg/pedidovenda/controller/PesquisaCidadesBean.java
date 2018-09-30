package br.com.rbg.pedidovenda.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.rbg.pedidovenda.model.Cidade;
import br.com.rbg.pedidovenda.repository.Cidades;
import br.com.rbg.pedidovenda.repository.filter.CidadeFilter;
import br.com.rbg.pedidovenda.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaCidadesBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Cidades cidades;
	
	private CidadeFilter filtro;
	
	private List<Cidade> cidadesFiltradas;
	
	private Cidade cidadeSelecionada;
	
	public PesquisaCidadesBean() {
		filtro = new CidadeFilter();
	}
	
	public void pesquisar() {
		cidadesFiltradas = cidades.filtradas(filtro);
	}
	
	public void excluir() {
		cidades.remover(cidadeSelecionada);
		cidadesFiltradas.remove(cidadeSelecionada);
		
		FacesUtil.addInfoMessage("Cidade " + cidadeSelecionada.getNome() 
			+ " excluída com sucesso.");
	}
	
	public CidadeFilter getFiltro() {
		return filtro;
	}
	
	public List<Cidade> getCidadesFiltradas() {
		return cidadesFiltradas;
	}
	
	public Cidade getCidadeSelecionada() {
		return cidadeSelecionada;
	}
	
	public void setCidadeSelecionada(Cidade cidadeSelecionada) {
		this.cidadeSelecionada = cidadeSelecionada;
	}
	
}
