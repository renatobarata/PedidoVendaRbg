package br.com.rbg.pedidovenda.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.rbg.pedidovenda.model.Estado;
import br.com.rbg.pedidovenda.repository.Estados;
import br.com.rbg.pedidovenda.repository.filter.EstadoFilter;
import br.com.rbg.pedidovenda.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaEstadosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Estados estados;
	
	private EstadoFilter filtro;
	
	private List<Estado> estadosFiltrados;
	
	private Estado estadoSelecionado;
	
	public PesquisaEstadosBean() {
		filtro = new EstadoFilter();
	}
	
	public void pesquisar() {
		estadosFiltrados = estados.filtrados(filtro);
	}
	
	public void excluir() {
		estados.remover(estadoSelecionado);
		estadosFiltrados.remove(estadoSelecionado);
		
		FacesUtil.addInfoMessage("Estado " + estadoSelecionado.getNome() 
			+ " excluído com sucesso.");
	}
	
	public EstadoFilter getFiltro() {
		return filtro;
	}
	
	public List<Estado> getEstadosFiltrados() {
		return estadosFiltrados;
	}
	
	public Estado getEstadoSelecionado() {
		return estadoSelecionado;
	}
	
	public void setEstadoSelecionado(Estado estadoSelecionado) {
		this.estadoSelecionado = estadoSelecionado;
	}
	
}
