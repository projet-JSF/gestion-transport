package com.intiformation.gestiontransport.dao.interfaces;

import com.intiformation.gestiontransport.entity.Marchandise;

/**
 * Interface de la DAO pour les marchandises. Définie les méthodes spécifiques aux marchandises.
 * Hérite de l'interface IGenericDAO
 * @author Marie
 *
 */

public interface IMarchandiseDAO extends IGenericDAO<Marchandise> {

	/*=============== Méthodes propres aux marchandises ==============*/
	
	public boolean isMarchandiseExists(String nom, Long idMarchandise);
	
	
}//end interface
