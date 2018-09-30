package br.com.rbg.pedidovenda.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.rbg.pedidovenda.comparator.EstadoComparator;
import br.com.rbg.pedidovenda.comparator.PaisComparator;
import br.com.rbg.pedidovenda.model.Cidade;
import br.com.rbg.pedidovenda.model.Estado;
import br.com.rbg.pedidovenda.model.Pais;
import br.com.rbg.pedidovenda.repository.Estados;
import br.com.rbg.pedidovenda.repository.Paises;
import br.com.rbg.pedidovenda.service.CadastroCidadeService;
import br.com.rbg.pedidovenda.service.NegocioException;
import br.com.rbg.pedidovenda.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroCidadeBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Paises paises;
	
	@Inject
	private Estados estados;
	
	@Inject
	private CadastroCidadeService cadastroCidadeService;
	
	private Cidade cidade;
	private Estado estado;
	private Pais pais;
	
	private List<Estado> listaEstados;
	private List<Pais> listaPaises;
	
	public CadastroCidadeBean() {
		limpar();
	}
	
	public void inicializar() {
		if (this.cidade == null) {
			limpar();
		}
		listaPaises = paises.paises();
		Collections.sort(listaPaises, new PaisComparator());
						
		if (this.pais != null) {
			carregarEstados();
		}
	}
	
	public void carregarEstados() {
		listaEstados = estados.estadosDe(pais);
		Collections.sort(listaEstados, new EstadoComparator());
	}
	
	private void limpar() {
		cidade = new Cidade();
		estado = null;
		pais = null;
		listaPaises = new ArrayList<>();
		listaEstados = new ArrayList<>();
	}
	
	public boolean isEditando() {
		return this.cidade.getId() != null;
	}
	
	public void salvar() {
		try {
			this.cidade = cadastroCidadeService.salvar(this.cidade);
			limpar();
			FacesUtil.addInfoMessage("Cidade salva com sucesso!");
		} catch (NegocioException ne) {
			FacesUtil.addErrorMessage(ne.getMessage());
		}
	}
	
	public Cidade getCidade() {
		return cidade;
	}
	
	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
		
		if(this.cidade != null) {
			this.pais = this.cidade.getEstado().getPais();
		}
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public List<Estado> getListaEstados() {
		return listaEstados;
	}

	public List<Pais> getListaPaises() {
		return listaPaises;
	}
	
}
