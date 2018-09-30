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

import br.com.rbg.pedidovenda.model.Pais;
import br.com.rbg.pedidovenda.repository.filter.PaisFilter;
import br.com.rbg.pedidovenda.service.NegocioException;
import br.com.rbg.pedidovenda.util.jpa.Transactional;

public class Paises implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Pais guardar(Pais pais) {
		return pais = manager.merge(pais);
	}

	@Transactional
	public void remover(Pais paisSelecionado) {
		try {
			paisSelecionado = porId(paisSelecionado.getId());
			manager.remove(paisSelecionado);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("País não pode ser excluído.");
		}
	}
	
	public Pais porSigla(String sigla) {
		try {
			return manager.createQuery("from Pais where upper(sigla) = :sigla", Pais.class)
					.setParameter("sigla", sigla.toUpperCase())
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public Pais porId(Long id) {
		return manager.find(Pais.class, id);
	}

	public List<Pais> paises() {
		return manager.createQuery("from Pais", Pais.class).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Pais> filtrados(PaisFilter filtro) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Pais.class);
								
		// where like '%joao%'
		if(StringUtils.isNotBlank(filtro.getNome())) {
			criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
		}
		
		if(StringUtils.isNotBlank(filtro.getSigla())) {
			criteria.add(Restrictions.eq("sigla", filtro.getSigla()));
		}
		
		return criteria.addOrder(Order.asc("nome")).list();
	}
	
}
