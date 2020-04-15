package com.intiformation.gestiontransport.managedBean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="authenticationBean")
@SessionScoped
public class AuthentificationBean implements Serializable{
	
	/*propriétés*/
	private String login;
	private String motDePasse;
	
	/*constructeur*/
	/**
	 * constructeur sans paramètre pour permettre au serveur d'instancier le managed bean
	 */
	public AuthentificationBean() {
		super();
	}
	
	/*getters/setters*/
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getMotDePasse() {
		return motDePasse;
	}
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}
	
	/*méthodes*/
	
	
	

}
