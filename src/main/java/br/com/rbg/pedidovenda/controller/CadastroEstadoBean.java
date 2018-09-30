package br.com.rbg.pedidovenda.controller;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.rbg.pedidovenda.comparator.PaisComparator;
import br.com.rbg.pedidovenda.model.Estado;
import br.com.rbg.pedidovenda.model.Pais;
import br.com.rbg.pedidovenda.repository.Paises;
import br.com.rbg.pedidovenda.service.CadastroEstadoService;
import br.com.rbg.pedidovenda.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroEstadoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Estado estado;
	
	@Inject
	private CadastroEstadoService cadastroEstadoService;
	
	@Inject
	private Paises repositorioPaises;
	
	private List<Pais> listaPaises;
	
	public CadastroEstadoBean() {
		limpar();
	}
	
	public void inicializar() {
		if (this.estado == null) {
			limpar();
		}
		listaPaises = repositorioPaises.paises();
		Collections.sort(listaPaises, new PaisComparator());
		
	}
	
	private void limpar() {
		estado = new Estado();
		listaPaises = null;
	}
	
	public boolean isEditando() {
		return this.estado.getId() != null;
	}
	
	public void salvar() {
		this.estado = cadastroEstadoService.salvar(estado);
		limpar();
		
		FacesUtil.addInfoMessage("Estado salvo com sucesso.");
	}
	
	public Estado getEstado() {
		return estado;
	}
	
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	public List<Pais> getListaPaises() {
		return listaPaises;
	}
	
}
