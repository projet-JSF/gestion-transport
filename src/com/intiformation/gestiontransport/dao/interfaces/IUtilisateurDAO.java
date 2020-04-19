package com.intiformation.gestiontransport.dao.interfaces;

import com.intiformation.gestiontransport.entity.Utilisateur;
/**
 * Interface de la DAO pour l'utilisateur. D�finie les m�thodes sp�cifiques � l'utilisateur.
 * H�rite de l'interface IGenericDAO
 * @author Valentin
 *
 */
public interface IUtilisateurDAO extends IGenericDAO<Utilisateur>{
	
	public boolean isUtilisateurExists (String pLogin, String pMotDePasse);
	
	public Utilisateur getUserByMailMDP(String pLogin, String pMotDePasse);

}//fin classe
