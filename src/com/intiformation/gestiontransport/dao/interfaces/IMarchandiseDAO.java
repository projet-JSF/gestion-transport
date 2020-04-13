package com.intiformation.gestiontransport.dao.interfaces;

import com.intiformation.gestiontransport.entity.Marchandise;

/**
 * 
 * @author Marie
 *
 */

public interface IMarchandiseDAO extends IGenericDAO<Marchandise> {

	/*=============== Méthodes propres aux marchandises ==============*/
	
	public boolean isMarchandiseExists(String nom, Long idMarchandise);
	
	
}//end interface
