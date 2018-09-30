package br.com.rbg.pedidovenda.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.rbg.pedidovenda.model.Cliente;
import br.com.rbg.pedidovenda.repository.Clientes;
import br.com.rbg.pedidovenda.repository.filter.ClienteFilter;
import br.com.rbg.pedidovenda.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaClientesBean implements Serializable  {
		
	private static final long serialVersionUID = 1L;

	@Inject
	private Clientes clientes;
	
	private Cliente clienteSelecionado;
	
	private ClienteFilter filtro;
	private List<Cliente> clientesFiltrados;
	
	public PesquisaClientesBean() {
		filtro = new ClienteFilter();
		clientesFiltrados = new ArrayList<>();
	
	}
	
	public void pesquisar() {
		clientesFiltrados = clientes.filtrados(filtro);
	}
	
	public void excluir() {
		clientes.remover(clienteSelecionado);
		clientesFiltrados.remove(clienteSelecionado);
		
		FacesUtil.addInfoMessage("Cliente " + clienteSelecionado.getNome() + " excluído com sucesso!");
	}
	
	public Clientes getClientes() {
		return clientes;
	}

	public void setClientes(Clientes clientes) {
		this.clientes = clientes;
	}

		
	public Cliente getClienteSelecionado() {
		return clienteSelecionado;
	}

	public void setClienteSelecionado(Cliente clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
	}

	public ClienteFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(ClienteFilter filtro) {
		this.filtro = filtro;
	}

	public List<Cliente> getClientesFiltrados() {
		return clientesFiltrados;
	}

	public void setClientesFiltrados(List<Cliente> clientesFiltrados) {
		this.clientesFiltrados = clientesFiltrados;
	}
	
}
