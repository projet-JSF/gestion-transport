package com.intiformation.gestiontransport.tool;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

/**
 * utilitaire qui permet de r�cup�rer l'entityManager de JPA <br/>
 * 
 * @author INTIFORMATION
 *
 */
public class JPAUtil {

	// 1. d�calaration de la session factory
	private static EntityManager entityManager;

	public static EntityManager getEntityManager() {

		if (entityManager == null) {

			try {

				entityManager = Persistence.createEntityManagerFactory("pu_gestion_transport")
						                   .createEntityManager();

			} catch (PersistenceException e) {
				System.out.println("La r�cup�ration de l'entity manager � �chou�e");
				e.printStackTrace();
			}

		} // end if

		return entityManager;

	}// end getEntityManager()

}// end class
