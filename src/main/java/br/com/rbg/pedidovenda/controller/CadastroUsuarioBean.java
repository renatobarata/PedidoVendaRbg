package br.com.rbg.pedidovenda.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.rbg.pedidovenda.model.Grupo;
import br.com.rbg.pedidovenda.model.Usuario;
import br.com.rbg.pedidovenda.repository.Grupos;
import br.com.rbg.pedidovenda.service.CadastroUsuarioService;
import br.com.rbg.pedidovenda.service.NegocioException;
import br.com.rbg.pedidovenda.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroUsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private CadastroUsuarioService cadastroUsuarioService;
	
	@Inject
	private Grupos grupos;
	
	private Usuario usuario;
	private Grupo grupoSelecionado;
	
	private List<Grupo> gruposRaizes;
	
	public CadastroUsuarioBean() {
		limpar();
	}
	
	public void inicializar() {
		if(this.usuario == null) {
			limpar();
		}
		gruposRaizes = grupos.raizes();
	}

	private void limpar() {
		usuario = new Usuario();
		grupoSelecionado = null;
		gruposRaizes = new ArrayList<>();
	}
	
	public void salvar() {
		this.usuario = cadastroUsuarioService.salvar(this.usuario);
		limpar();
		
		FacesUtil.addInfoMessage("Usuário salvo com sucesso!");
	}
		
	public boolean isEditando() {
		return this.usuario.getId() != null;
	}
	
	public void adicionarGrupo() {		
		if(grupoSelecionado != null){

			for(Grupo g : usuario.getGrupos()){

				if(grupoSelecionado.equals(g)){
					throw new NegocioException("Este grupo já foi adicionado!");
		        }

		    }
		    this.usuario.getGrupos().add(this.grupoSelecionado);
		}
	}
	
	public void removerGrupo() {
		this.usuario.getGrupos().remove(this.grupoSelecionado);
	}
	
	public CadastroUsuarioService getCadastroUsuarioService() {
		return cadastroUsuarioService;
	}

	public void setCadastroUsuarioService(CadastroUsuarioService cadastroUsuarioService) {
		this.cadastroUsuarioService = cadastroUsuarioService;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Grupo getGrupoSelecionado() {
		return grupoSelecionado;
	}

	public void setGrupoSelecionado(Grupo grupoSelecionado) {
		this.grupoSelecionado = grupoSelecionado;
	}
	
	public List<Grupo> getGruposRaizes() {
		return gruposRaizes;
	}

	public void setGruposRaizes(List<Grupo> gruposRaizes) {
		this.gruposRaizes = gruposRaizes;
	}
		
}
