package com.intiformation.gestiontransport.dao.implementations;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.intiformation.gestiontransport.dao.interfaces.IGenericDAO;
import com.intiformation.gestiontransport.tool.JPAUtil;



/**
 * 
 * @author INTIFORMATION
 *
 * @param <T>
 */
public abstract class JpaDAO<T> implements IGenericDAO<T> {

	protected Class<T> entityClass;

	protected EntityManager entityManager = JPAUtil.getEntityManager();

	/**
	 * ctor
	 */
	public JpaDAO(Class<T> entityClass) {
		
		this.entityClass = entityClass;
		
	}//end ctor

	/**
	 * ajout d'une entité  
	 */
	@Override
	public void ajouter(T entity) {

		EntityTransaction transaction = null;

		try {

			transaction = entityManager.getTransaction();
			transaction.begin();
			entityManager.persist(entity);
			transaction.commit();

		} catch (PersistenceException ex) {

			if (transaction != null) {
				transaction.rollback();
				ex.printStackTrace();
			}

		}

	}// end ajouter

	/**
	 * modif d'une entité  
	 */
	@Override
	public void modifier(T entity) {

		EntityTransaction transaction = null;

		try {

			transaction = entityManager.getTransaction();
			transaction.begin();
			entityManager.merge(entity);
			transaction.commit();

		} catch (PersistenceException ex) {

			if (transaction != null) {
				transaction.rollback();
				ex.printStackTrace();
			}

		}

	}// end modifier

	/**
	 * supp  d'une entité  
	 */
	@Override
	public void supprimer(Long id) {

		EntityTransaction transaction = null;

		try {

			transaction = entityManager.getTransaction();
			transaction.begin();
			entityManager.remove(getById(id));
			transaction.commit();

		} catch (PersistenceException ex) {

			if (transaction != null) {
				transaction.rollback();
				ex.printStackTrace();
			}

		}
		
		
	}// end supprimer

	
	/**
	 * get by id  une entité.  
	 */
	@Override
	public T getById(Long id) {
		return entityManager.find(entityClass, id);
	}// end getById

	
	/**
	 * get all entités.
	 */
	@Override
	public List<T> getAll() {

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

		CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entityClass);

		Root<T> clauseFROM = criteriaQuery.from(entityClass);

		CriteriaQuery<T> clauseSELECT = criteriaQuery.select(clauseFROM);

		TypedQuery<T> getAllQuery = entityManager.createQuery(clauseSELECT);

		return getAllQuery.getResultList();

	}// end getAll

}//end class 
