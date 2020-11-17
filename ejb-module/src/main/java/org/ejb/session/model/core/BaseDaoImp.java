package org.ejb.session.model.core;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

/**
 * An implementation of the base DAO interface
 * <p>
 * 
 *
 */
public abstract class BaseDaoImp<E extends Indexable> implements BaseDao<E> {

	/**
	 * The JPA entity manager to be used from all derived DAO classes
	 */
	@PersistenceContext(unitName = "ejbSampleDS")
	protected EntityManager em;

	/**
	 * This method should be used in out-of-container environment only injecting
	 * a NON-"JTA" ("RESOURCE_LOCAL") EntityManager
	 */
//	public void setEntityManager(EntityManager em) {
//		this.em = em;
//	}

	public E create(E newInstance) {
		em.persist(newInstance);
		em.flush();
		return newInstance;
	}

	public void delete(E persistentObject) {
		em.remove(persistentObject);
		em.flush();
	}

	public E read(Serializable id) {
		return em.find(getType(), id);
	}

	public boolean exists(Serializable id) {
		return (null != read(id));
	}

	public E update(E transientObject) {
		em.merge(transientObject);
		em.flush();
		return transientObject;
	}

	@SuppressWarnings("unchecked")
	public List<E> list() {
		// TODO make query more generic using JPA criteria
		Query qry = em.createQuery("from " + getType().getSimpleName() + " u");
		return qry.getResultList();
	}

}
