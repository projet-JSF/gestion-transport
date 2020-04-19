package com.intiformation.gestiontransport.dao.interfaces;

import com.intiformation.gestiontransport.entity.Utilisateur;
/**
 * Interface de la DAO pour l'utilisateur. Définie les méthodes spécifiques à l'utilisateur.
 * Hérite de l'interface IGenericDAO
 * @author Valentin
 *
 */
public interface IUtilisateurDAO extends IGenericDAO<Utilisateur>{
	
	public boolean isUtilisateurExists (String pLogin, String pMotDePasse);
	
	public Utilisateur getUserByMailMDP(String pLogin, String pMotDePasse);

}//fin classe
