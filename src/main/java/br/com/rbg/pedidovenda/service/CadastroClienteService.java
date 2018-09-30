package br.com.rbg.pedidovenda.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.rbg.pedidovenda.model.Cliente;
import br.com.rbg.pedidovenda.repository.Clientes;
import br.com.rbg.pedidovenda.util.jpa.Transactional;

public class CadastroClienteService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Clientes clientes;
	
	@Transactional
	public Cliente salvar (Cliente cliente) {
		Cliente clienteExistente = clientes.porNome(cliente.getNome());
	
		if(clienteExistente != null && !clienteExistente.equals(cliente)) {
			throw new NegocioException("Ja existe um cliente com o nome informado!");
		}
		
		return clientes.guardar(cliente);
	}
	
}
