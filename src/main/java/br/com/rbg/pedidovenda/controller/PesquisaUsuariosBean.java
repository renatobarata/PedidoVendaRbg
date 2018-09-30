package br.com.rbg.pedidovenda.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.rbg.pedidovenda.model.Usuario;
import br.com.rbg.pedidovenda.repository.Usuarios;
import br.com.rbg.pedidovenda.repository.filter.UsuarioFilter;
import br.com.rbg.pedidovenda.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaUsuariosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Usuarios usuarios;
	
	private Usuario usuarioSelecionado;
	private UsuarioFilter filtro;
	private List<Usuario> usuariosFiltrados;
	
	public PesquisaUsuariosBean() {
		filtro = new UsuarioFilter();
	}
	
	public void pesquisar() {
		usuariosFiltrados = usuarios.filtrados(filtro);
	}
	
	public void excluir() {
		usuarios.remover(usuarioSelecionado);
		usuariosFiltrados.remove(usuarioSelecionado);
		
		FacesUtil.addInfoMessage("Usuário " + usuarioSelecionado.getNome() + " excluído com sucesso!");
	}
	
	public Usuarios getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Usuarios usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario getUsuarioSelecionado() {
		return usuarioSelecionado;
	}
	
	public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}
	
	public UsuarioFilter getFiltro() {
		return filtro;
	}
	
	public void setFiltro(UsuarioFilter filtro) {
		this.filtro = filtro;
	}
	
	public List<Usuario> getUsuariosFiltrados() {
		return usuariosFiltrados;
	}
	
	public void setUsuariosFiltrados(List<Usuario> usuariosFiltrados) {
		this.usuariosFiltrados = usuariosFiltrados;
	}
	
	
	
}
