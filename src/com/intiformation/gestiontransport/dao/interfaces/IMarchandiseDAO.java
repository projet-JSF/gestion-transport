package com.intiformation.gestiontransport.dao.interfaces;

import com.intiformation.gestiontransport.entity.Marchandise;

/**
 * Interface de la DAO pour les marchandises. D�finie les m�thodes sp�cifiques aux marchandises.
 * H�rite de l'interface IGenericDAO
 * @author Marie
 *
 */

public interface IMarchandiseDAO extends IGenericDAO<Marchandise> {

	/*=============== M�thodes propres aux marchandises ==============*/
	
	public boolean isMarchandiseExists(String nom, Long idMarchandise);
	
	
}//end interface
