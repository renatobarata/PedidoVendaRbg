package br.com.rbg.pedidovenda.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.rbg.pedidovenda.comparator.CidadeComparator;
import br.com.rbg.pedidovenda.comparator.EstadoComparator;
import br.com.rbg.pedidovenda.comparator.PaisComparator;
import br.com.rbg.pedidovenda.model.Cidade;
import br.com.rbg.pedidovenda.model.Cliente;
import br.com.rbg.pedidovenda.model.Endereco;
import br.com.rbg.pedidovenda.model.Estado;
import br.com.rbg.pedidovenda.model.Pais;
import br.com.rbg.pedidovenda.model.TipoPessoa;
import br.com.rbg.pedidovenda.repository.Cidades;
import br.com.rbg.pedidovenda.repository.Estados;
import br.com.rbg.pedidovenda.repository.Paises;
import br.com.rbg.pedidovenda.service.CadastroClienteService;
import br.com.rbg.pedidovenda.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroClienteBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Paises paises;
	
	@Inject
	private Estados estados;
	
	@Inject
	private Cidades cidades;
		
	@Inject
	private CadastroClienteService cadastroClienteService;
	
	@Produces
	@ClienteEdicao
	private Cliente cliente;
	
	private Pais  pais;
	private Estado estado;
	private Cidade cidade;
	
	private List<Pais> listaPaises;
	private List<Estado> listaEstados;
	private List<Cidade> listaCidades;
	
	private Endereco endereco;
	private Endereco enderecoSelecionado;
		
	private void limpar() {
		cliente = new Cliente();
		endereco = new Endereco();
		cidade = null;
		estado = null;
		pais = null;
		listaPaises = new ArrayList<>();
		listaEstados = new ArrayList<>();
		listaCidades = new ArrayList<>();
	}
		
	public CadastroClienteBean() {
		limpar();
	}
	
	public void inicializar() {
		if (cliente==null){
			limpar();
		}
		
		listaPaises = paises.paises();
		Collections.sort(listaPaises, new PaisComparator());
		
		if (this.pais != null) {
			carregarEstados();
		}
		
		if (this.estado != null) {
			carregarCidades();
		}
	}
	
	public void carregarEstados() {
		listaEstados = estados.estadosDe(pais);
		Collections.sort(listaEstados, new EstadoComparator());
	}
	
	public void carregarCidades() {
		listaCidades = cidades.cidadesDe(estado);
		Collections.sort(listaCidades, new CidadeComparator());
	}
	
	public void salvar() {
		this.cliente = cadastroClienteService.salvar(this.cliente);
		
		limpar();
		FacesUtil.addInfoMessage("Cliente salvo com sucesso!");
	}
	
	public void prepararNovoEndereco() {
		   this.endereco = new Endereco();
		}
	
	public void incluirEndereco() {
		if (!cliente.getEnderecos().contains(this.endereco)){
			this.endereco.setCidade(cidade);
			this.endereco.setCliente(cliente);
			this.cliente.getEnderecos().add(this.endereco);
			this.endereco = new Endereco();
		}
		
	}
	
	public void removerEndereco() {
		this.cliente.getEnderecos().remove(enderecoSelecionado);
		FacesUtil.addInfoMessage("Endereco excluído com sucesso!");
		prepararNovoEndereco();
	}
	
	public boolean isEditando() {
		return this.cliente.getId() != null;
	}
	
	public Cliente getCliente() {
		return cliente;
	}	
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
		
	
	public Paises getPaises() {
		return paises;
	}

	public void setPaises(Paises paises) {
		this.paises = paises;
	}

	public Estados getEstados() {
		return estados;
	}

	public void setEstados(Estados estados) {
		this.estados = estados;
	}

	public Cidades getCidades() {
		return cidades;
	}

	public void setCidades(Cidades cidades) {
		this.cidades = cidades;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public List<Pais> getListaPaises() {
		return listaPaises;
	}

	public void setListaPaises(List<Pais> listaPaises) {
		this.listaPaises = listaPaises;
	}

	public List<Estado> getListaEstados() {
		return listaEstados;
	}

	public void setListaEstados(List<Estado> listaEstados) {
		this.listaEstados = listaEstados;
	}

	public List<Cidade> getListaCidades() {
		return listaCidades;
	}

	public void setListaCidades(List<Cidade> listaCidades) {
		this.listaCidades = listaCidades;
	}

	public Endereco getEndereco() {
		return endereco;
	}
	
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	public Endereco getEnderecoSelecionado() {
		return enderecoSelecionado;
	}
	
	public void setEnderecoSelecionado(Endereco enderecoSelecionado) {
		this.enderecoSelecionado = enderecoSelecionado;
	}
	
	public TipoPessoa[] getTipos() {
		return TipoPessoa.values();
	}
			
}
