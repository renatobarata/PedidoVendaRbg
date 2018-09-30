package br.com.rbg.pedidovenda.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.rbg.pedidovenda.model.Cidade;
import br.com.rbg.pedidovenda.repository.Cidades;
import br.com.rbg.pedidovenda.util.jpa.Transactional;

public class CadastroCidadeService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Cidades cidades;
	
	@Transactional
	public Cidade salvar(Cidade cidade) {
		Cidade cidadeExistente = cidades.porNome(cidade.getNome());
		
		if (cidadeExistente != null && !cidadeExistente.equals(cidade)) {
			throw new NegocioException("Já existe uma cidade com o nome informado.");
		}
		
		return cidades.guardar(cidade);
	}
	
}
