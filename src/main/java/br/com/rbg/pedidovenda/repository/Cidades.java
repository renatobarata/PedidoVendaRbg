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

import br.com.rbg.pedidovenda.model.Cidade;
import br.com.rbg.pedidovenda.model.Estado;
import br.com.rbg.pedidovenda.repository.filter.CidadeFilter;
import br.com.rbg.pedidovenda.service.NegocioException;
import br.com.rbg.pedidovenda.util.jpa.Transactional;

public class Cidades implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;
	
	public Cidade guardar(Cidade cidade) {
		return cidade = manager.merge(cidade);
	}
	
	@Transactional
	public void remover(Cidade cidadeSelecionada) {
		try {
			cidadeSelecionada = porId(cidadeSelecionada.getId());
			manager.remove(cidadeSelecionada);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Cidade não pode ser excluída.");
		}
	}
	
	public Cidade porNome(String nome) {
		try {
			return manager.createQuery("from Cidade where upper(nome) = :nome", Cidade.class)
					.setParameter("nome", nome.toUpperCase())
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public Cidade porId(Long id) {
		return manager.find(Cidade.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Cidade> filtradas(CidadeFilter filtro) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Cidade.class);
								
		// where like '%joao%'
		if(StringUtils.isNotBlank(filtro.getNome())) {
			criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
		}
								
		return criteria.addOrder(Order.asc("nome")).list();
	}

	public List<Cidade> cidadesDe(Estado estado) {
		return manager.createQuery("from Cidade where estado = :nome", 
				Cidade.class).setParameter("nome", estado).getResultList();
	}
	
	
}
