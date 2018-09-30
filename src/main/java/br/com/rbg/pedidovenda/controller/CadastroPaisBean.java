package br.com.rbg.pedidovenda.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.rbg.pedidovenda.model.Pais;
import br.com.rbg.pedidovenda.service.CadastroPaisService;
import br.com.rbg.pedidovenda.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroPaisBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroPaisService cadastroPaisService;
	
	private Pais pais;
	
	public CadastroPaisBean() {
		limpar();
	}
	
	public void inicializar() {
		if (pais==null){
			limpar();
		}
	}
	
	private void limpar() {
		pais = new Pais();
	}
	
	public void salvar() {
		this.pais = cadastroPaisService.salvar(this.pais);
		limpar();
		
		FacesUtil.addInfoMessage("País salvo com sucesso.");
	}
	
	public boolean isEditando() {
		return this.pais.getId() != null;
	}
	
	public Pais getPais() {
		return pais;
	}
	
	public void setPais(Pais pais) {
		this.pais = pais;
	}
	
}
