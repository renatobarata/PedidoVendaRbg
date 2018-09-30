package br.com.rbg.pedidovenda.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.rbg.pedidovenda.model.Usuario;
import br.com.rbg.pedidovenda.repository.Usuarios;
import br.com.rbg.pedidovenda.util.jpa.Transactional;

public class CadastroUsuarioService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Usuarios usuarios;
	
	@Transactional
	public Usuario salvar (Usuario usuario) {
		Usuario usuarioExistente = usuarios.porNome(usuario.getNome());
	
		if(usuarioExistente != null && !usuarioExistente.equals(usuario)) {
			throw new NegocioException("Ja existe um usuário com o nome informado!");
		}
		
		return usuarios.guardar(usuario);
	}
	
}
