package br.com.rbg.pedidovenda.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.rbg.pedidovenda.model.Grupo;
import br.com.rbg.pedidovenda.repository.Grupos;
import br.com.rbg.pedidovenda.util.jpa.Transactional;

public class CadastroGrupoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Grupos grupos;
	
	@Transactional
	public Grupo salvar(Grupo grupo) {
		Grupo grupoExistente = grupos.porNome(grupo.getNome());
		
		if(grupoExistente != null){
			throw new NegocioException("Já existe um grupo com o nome informado.");
		}
		
		return grupos.guardar(grupo);
	}
	
}
