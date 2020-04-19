package com.intiformation.gestiontransport.dao.implementations;


import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.intiformation.gestiontransport.dao.interfaces.IMarchandiseDAO;
import com.intiformation.gestiontransport.entity.Marchandise;

/**
 * Implémentation concrète de la couche dao pour les Marchandises <br/> 
 * Hérite de la classe JpaDAO pour récupérer les méthodes du CRUD et implémente l'interface IMarchandiseDAO pour les méthodes spécifiques aux marchandises
 * @author Marie
 *
 */

public class MarchandiseDAOImpl extends JpaDAO<Marchandise> implements IMarchandiseDAO {

	
/*===============================Constructeur===============================================*/
	/**
	 * Ctor vide pour initialiser l'entité dans JpaDAO.
	 */
	public  MarchandiseDAOImpl() {
		super(Marchandise.class);
	}//end ctor

	
/*========================================Méthode===============================================*/
	@Override
	public boolean isMarchandiseExists(String nom, Long idMarchandise) {
	

		try {
			
			//Requête 
			Query query = this.entityManager.createNamedQuery("Marchandise.isExists");
			query.setParameter(1, nom);
			query.setParameter(2, idMarchandise);
			
			// Exécution + récupération du résultat de la requête. 
			Long isExistsVerif =  (Long) query.getSingleResult();
			
			return (isExistsVerif ==1);
			
		} catch (PersistenceException e) {
			System.out.println("*** Erreur lors de la vérif de l'existence de la marchandise ***");
			e.printStackTrace();
		}
		
		return false;
	}//end isMarchandiseExists

}//end classe
