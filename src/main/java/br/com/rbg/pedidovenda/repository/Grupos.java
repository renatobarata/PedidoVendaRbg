package br.com.rbg.pedidovenda.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import br.com.rbg.pedidovenda.model.Grupo;
import br.com.rbg.pedidovenda.service.NegocioException;
import br.com.rbg.pedidovenda.util.jpa.Transactional;

public class Grupos implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;
	
	public Grupo guardar(Grupo grupo) {
		return manager.merge(grupo);
	}
	
	@Transactional
	public void remover(Grupo grupo) {
		try {
			grupo = porId(grupo.getId());
			manager.remove(grupo);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Grupo não pode ser excluído.");
		}
	}
	
	public Grupo porNome(String nome) {
		try {
			return manager.createQuery("from Grupo where upper(nome) = :nome", Grupo.class)
					.setParameter("nome", nome.toUpperCase())
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
		
	}
	
	public Grupo porId(Long id) {
		return manager.find(Grupo.class, id);
	}
	
	public List<Grupo> raizes() {
		return manager.createQuery("from Grupo", Grupo.class).getResultList();
	}
	
		
}
