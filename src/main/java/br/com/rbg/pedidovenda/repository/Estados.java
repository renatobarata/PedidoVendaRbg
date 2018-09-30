package br.com.rbg.pedidovenda.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.rbg.pedidovenda.model.Estado;
import br.com.rbg.pedidovenda.model.Pais;
import br.com.rbg.pedidovenda.repository.filter.EstadoFilter;
import br.com.rbg.pedidovenda.service.NegocioException;
import br.com.rbg.pedidovenda.util.jpa.Transactional;

public class Estados implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;
	
	public Estado guardar(Estado estado) {
		return estado = manager.merge(estado);
	}
	
	@Transactional
	public void remover(Estado estadoSelecionado) {
		try {
			estadoSelecionado = porId(estadoSelecionado.getId());
			manager.remove(estadoSelecionado);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Estado não pode ser excluído.");
		}
	}
	
	public Estado porNome(String nome) {
		try {
			return manager.createQuery("from Estado where upper(nome) = :nome", Estado.class)
					.setParameter("nome", nome.toUpperCase())
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public Estado porId(Long id) {
		return manager.find(Estado.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Estado> filtrados(EstadoFilter filtro) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Estado.class);
								
		// where like '%joao%'
		if(StringUtils.isNotBlank(filtro.getNome())) {
			criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
		}
		
		if(StringUtils.isNotBlank(filtro.getSigla())) {
			criteria.add(Restrictions.eq("sigla", filtro.getSigla()));
		}
				
		return criteria.addOrder(Order.asc("nome")).list();
	}
	
	public List<Estado> estadosDe(Pais pais) {
		return manager.createQuery("from Estado where pais = :nome", 
				Estado.class).setParameter("nome", pais).getResultList();
	}
	
}
