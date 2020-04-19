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
 * Implementation concrete de la DAO g�n�rale.
 * Impl�mente l'interface IGenericDAO en conservant un type g�n�rique.
 * Impl�mente les fonctions du CRUD de fa�on g�n�raliste (c'est � dire utilisable pour n'importe quelle classe)
 * @author Marie
 *
 * @param <T>
 */
public abstract class JpaDAO<T> implements IGenericDAO<T> {

	//============ Propri�t�es ====================//
	
	protected Class<T> entityClass;

	protected EntityManager entityManager = JPAUtil.getEntityManager();
	

	//============ Constructeurs ====================//
	/**
	 * Constructeur prenant en parametre la classe de l'entity
	 */
	public JpaDAO(Class<T> entityClass) {
		
		this.entityClass = entityClass;
		
	}//end ctor

	
	
	//============ M�thodes ====================//
	
	
	//--------------------------------------------------//
	//-------------- AJOUTER  --------------------------//
	//--------------------------------------------------//
	
	/**
	 * Ajout d'une entit� � la base de donn�e.
	 * @param entity � ajouter � la bdd
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
	 * Modification d'une entit� dans la base de donn�e
	 * @param entity � modifier
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
	 * Suppression d'une entit� dans la bdd
	 * @param id (cl� primaire) de l'entit� � supprimer
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
	 * R�cup�ration d'une entit� par son identifiant (cl� primaire)
	 * @param id de l'entit� � r�cuperer
	 * @return l'entit� cherch�e
	 */
	@Override
	public T getById(Long id) {
		return entityManager.find(entityClass, id);
	}// end getById

	
	
	//--------------------------------------------------//
	//-------------- GETBYID  --------------------------//
	//--------------------------------------------------//
	/**
	 * R�cup�re la liste des entit�s dans la bdd
	 * @return liste des entit�s 
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
