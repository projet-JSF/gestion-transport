package com.intiformation.gestiontransport.dao.implementations;


import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.intiformation.gestiontransport.dao.interfaces.IMarchandiseDAO;
import com.intiformation.gestiontransport.entity.Marchandise;

/**
 * Impl�mentation concr�te de la couche dao pour les Marchandises <br/> 
 * H�rite de la classe JpaDAO pour r�cup�rer les m�thodes du CRUD et impl�mente l'interface IMarchandiseDAO pour les m�thodes sp�cifiques aux marchandises
 * @author Marie
 *
 */

public class MarchandiseDAOImpl extends JpaDAO<Marchandise> implements IMarchandiseDAO {

	
/*===============================Constructeur===============================================*/
	/**
	 * Ctor vide pour initialiser l'entit� dans JpaDAO.
	 */
	public  MarchandiseDAOImpl() {
		super(Marchandise.class);
	}//end ctor

	
/*========================================M�thode===============================================*/
	@Override
	public boolean isMarchandiseExists(String nom, Long idMarchandise) {
	

		try {
			
			//Requ�te 
			Query query = this.entityManager.createNamedQuery("Marchandise.isExists");
			query.setParameter(1, nom);
			query.setParameter(2, idMarchandise);
			
			// Ex�cution + r�cup�ration du r�sultat de la requ�te. 
			Long isExistsVerif =  (Long) query.getSingleResult();
			
			return (isExistsVerif ==1);
			
		} catch (PersistenceException e) {
			System.out.println("*** Erreur lors de la v�rif de l'existence de la marchandise ***");
			e.printStackTrace();
		}
		
		return false;
	}//end isMarchandiseExists

}//end classe
