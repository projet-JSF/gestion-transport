package com.intiformation.gestiontransport.dao.interfaces;

import com.intiformation.gestiontransport.entity.Utilisateur;

public interface IUtilisateurDAO extends IGenericDAO<Utilisateur>{
	
	public boolean isUtilisateurExists (String pLogin, String pMotDePasse);
	
	public Utilisateur getUserByMailMDP(String pLogin, String pMotDePasse);

}//fin classe
