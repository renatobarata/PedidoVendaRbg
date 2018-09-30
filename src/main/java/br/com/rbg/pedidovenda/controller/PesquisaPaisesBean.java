package br.com.rbg.pedidovenda.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.rbg.pedidovenda.model.Pais;
import br.com.rbg.pedidovenda.repository.Paises;
import br.com.rbg.pedidovenda.repository.filter.PaisFilter;
import br.com.rbg.pedidovenda.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaPaisesBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Paises paises;
	
	private PaisFilter filtro;
	
	private List<Pais> paisesFiltrados;
	
	private Pais paisSelecionado;
	
	public PesquisaPaisesBean() {
		filtro = new PaisFilter();
	}
	
	public void pesquisar() {
		paisesFiltrados = paises.filtrados(filtro);
	}
	
	public void excluir() {
		paises.remover(paisSelecionado);
		paisesFiltrados.remove(paisSelecionado);
		
		FacesUtil.addInfoMessage("País " + paisSelecionado.getNome() 
			+ " excluído com sucesso.");
	}
	
	public PaisFilter getFiltro() {
		return filtro;
	}

	public List<Pais> getPaisesFiltrados() {
		return paisesFiltrados;
	}

	public Pais getPaisSelecionado() {
		return paisSelecionado;
	}

	public void setPaisSelecionado(Pais paisSelecionado) {
		this.paisSelecionado = paisSelecionado;
	}
	
}
