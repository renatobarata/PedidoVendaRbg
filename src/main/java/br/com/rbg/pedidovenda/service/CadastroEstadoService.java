package br.com.rbg.pedidovenda.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.rbg.pedidovenda.model.Estado;
import br.com.rbg.pedidovenda.repository.Estados;
import br.com.rbg.pedidovenda.util.jpa.Transactional;

public class CadastroEstadoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Estados estados;
	
	@Transactional
	public Estado salvar(Estado estado) {
		Estado estadoExistente = estados.porNome(estado.getNome());
		
		if(estadoExistente != null && !estadoExistente.equals(estado)) {
			throw new NegocioException("Já existe um estado com o nome informado.");
		}
						
		return estados.guardar(estado);
	}
	
}
