package br.com.rbg.pedidovenda.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.rbg.pedidovenda.model.Pais;
import br.com.rbg.pedidovenda.repository.Paises;
import br.com.rbg.pedidovenda.util.jpa.Transactional;

public class CadastroPaisService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Paises paises;
	
	@Transactional
	public Pais salvar(Pais pais) {
		Pais paisExistente = paises.porSigla(pais.getSigla());
		
		if(paisExistente != null && !paisExistente.equals(pais)) {
			throw new NegocioException("Já existe um país com a sigla informada.");
		}
		
		return paises.guardar(pais);
	}
	
}
