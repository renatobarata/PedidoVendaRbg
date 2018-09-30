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

import br.com.rbg.pedidovenda.model.Cliente;
import br.com.rbg.pedidovenda.repository.filter.ClienteFilter;
import br.com.rbg.pedidovenda.service.NegocioException;
import br.com.rbg.pedidovenda.util.jpa.Transactional;

public class Clientes implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;
	
	public Cliente guardar(Cliente cliente) {
		return manager.merge(cliente);
	}
	
	public Cliente porId(Long id) {
		return manager.find(Cliente.class, id);
	}
	
	public Cliente porNome(String nome) {
		try {
			return manager.createQuery("from Cliente where upper(nome) = :nome", Cliente.class)
					.setParameter("nome", nome.toUpperCase())
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
		
	public List<Cliente> listarPorNome(String nome) {
		return this.manager.createQuery("from Cliente where upper(nome) like :nome", Cliente.class)
					.setParameter("nome", nome.toUpperCase() + "%")
					.getResultList();
	}
	
	public Cliente porDocumentoReceitaFederal(String documentoReceitaFederal) {
		try {
			return manager.createQuery("from Cliente where upper(doc_receita_federal) = :doc_receita_federal", Cliente.class)
					.setParameter("doc_receita_federal", documentoReceitaFederal.toUpperCase())
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	@Transactional
	public void remover (Cliente cliente) {
		try {
			cliente = porId(cliente.getId());
			manager.remove(cliente);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Cliente não pode ser excluído!");
		}
	}
			
	@SuppressWarnings("unchecked")
	public List<Cliente> filtrados(ClienteFilter filtro) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Cliente.class);
		
		if(StringUtils.isNoneBlank(filtro.getDocumentoReceitaFederal())) {
			criteria.add(Restrictions.eq("doc_receita_federal", filtro.getDocumentoReceitaFederal()));
		}
		
		if(StringUtils.isNoneBlank(filtro.getNome())) {
			criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
		}
				
		return criteria.addOrder(Order.asc("nome")).list();
		
	}
	
}
