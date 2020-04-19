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
 * Implementation concrete de la DAO générale.
 * Implémente l'interface IGenericDAO en conservant un type générique.
 * Implémente les fonctions du CRUD de façon généraliste (c'est à dire utilisable pour n'importe quelle classe)
 * @author Marie
 *
 * @param <T>
 */
public abstract class JpaDAO<T> implements IGenericDAO<T> {

	//============ Propriétées ====================//
	
	protected Class<T> entityClass;

	protected EntityManager entityManager = JPAUtil.getEntityManager();
	

	//============ Constructeurs ====================//
	/**
	 * Constructeur prenant en parametre la classe de l'entity
	 */
	public JpaDAO(Class<T> entityClass) {
		
		this.entityClass = entityClass;
		
	}//end ctor

	
	
	//============ Méthodes ====================//
	
	
	//--------------------------------------------------//
	//-------------- AJOUTER  --------------------------//
	//--------------------------------------------------//
	
	/**
	 * Ajout d'une entité à la base de donnée.
	 * @param entity à ajouter à la bdd
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
			}//end if

		}//end catch

	}// end ajouter

	
	//--------------------------------------------------//
	//-------------- MODIFIER  -------------------------//
	//--------------------------------------------------//
	
	/**
	 * Modification d'une entité dans la base de donnée
	 * @param entity à modifier
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
			}//end if

		}//end catch

	}// end modifier

	
	//--------------------------------------------------//
	//-------------- SUPPRIMER  ------------------------//
	//--------------------------------------------------//
	
	/**
	 * Suppression d'une entité dans la bdd
	 * @param id (clé primaire) de l'entité à supprimer
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
			}//end if

		}//end catch
		
	}// end supprimer

	
	//--------------------------------------------------//
	//-------------- GETBYID  --------------------------//
	//--------------------------------------------------//
	/**
	 * Récupération d'une entité par son identifiant (clé primaire)
	 * @param id de l'entité à récuperer
	 * @return l'entité cherchée
	 */
	@Override
	public T getById(Long id) {
		return entityManager.find(entityClass, id);
	}// end getById

	
	
	//--------------------------------------------------//
	//-------------- GETBYID  --------------------------//
	//--------------------------------------------------//
	/**
	 * Récupère la liste des entités dans la bdd
	 * @return liste des entités 
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
