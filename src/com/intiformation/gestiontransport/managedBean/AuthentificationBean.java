package com.intiformation.gestiontransport.managedBean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.intiformation.gestiontransport.dao.implementations.UtilisateurDAOImpl;
import com.intiformation.gestiontransport.dao.interfaces.IUtilisateurDAO;
import com.intiformation.gestiontransport.entity.Utilisateur;

@ManagedBean(name = "authenticationBean")
@SessionScoped
public class AuthentificationBean implements Serializable {

	/* =========================PROPRIETES========================= */
	private String login="margot";
	private String motDePasse="0000";

	// propriété qui ava servir à stocker les informations de l'utilisateur
	// connecté.
	private Utilisateur utilisateurLogged;

	// Initialisation de la dao de l'utilisateur

	private IUtilisateurDAO utilisateurDAO;

	/* =========================CONSTRUCTEUR========================= */

	/**
	 * constructeur sans paramètre pour permettre au serveur d'instancier le managed
	 * bean
	 */
	public AuthentificationBean() {
		utilisateurDAO = new UtilisateurDAOImpl();
	}

	/* =========================GETTER/SETTER========================= */

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

	public Utilisateur getUtilisateurLogged() {
		return utilisateurLogged;
	}

	public void setUtilisateurLogged(Utilisateur utilisateurLogged) {
		this.utilisateurLogged = utilisateurLogged;
	}

	public IUtilisateurDAO getUtilisateurDAO() {
		return utilisateurDAO;
	}

	public void setUtilisateurDAO(IUtilisateurDAO utilisateurDAO) {
		this.utilisateurDAO = utilisateurDAO;
	}

	/* =========================METHODES========================= */

	
/*====================================================================================================*/	
/*==connecterUtilisateur==============================================================================*/
/*====================================================================================================*/	
		

	/**
	 * Permet de connecter le conseiller et de lui créer une session
	 * 
	 * @return "accueil.xhtml" pour renvoyer vers la page d'accueil
	 */

	public String connecterUtilisateur() {
		System.out.println("Je suis dans connecterUtilisateur du MB authentification ");

		// recup du context jsp
		FacesContext contextJSF = FacesContext.getCurrentInstance();

		// 1. vérif si l'utilisateur existe dans la bdd

		if (utilisateurDAO.isUtilisateurExists(login, motDePasse)) {
			System.out.println("L'utilisateur existe => connexion");

			/*---L'utilisateur existe ----*/

			// recup de la session utilisateur et Creation d'une session si elle n'existe
			// pas
			HttpSession session = (HttpSession) contextJSF.getExternalContext().getSession(true);

			// Sauvegarde du login dans la session
			session.setAttribute("user_login", login);

			// Recuperation du conseiller connecté et sauvegarde dans l'attribut du managed
			// bean
			setUtilisateurLogged(utilisateurDAO.getUserByMailMDP(login, motDePasse));

			return "accueil.xhtml";

		} else {
			/*---L'utilisateur n'existe pas ----*/
			System.out.println("L'utilisateur n'existe pas  => erreur");

			// def du message vie FacesMessage
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Echec de connexion",
					"Identifiant ou mot de passe invalide");

			// envoi du message vers la vue via le context JSF

			contextJSF.addMessage(null, message);

			// redirection vers Authentification.xhtml
			return "authentification.xhtml";
		}

	}// end connecterUtilisateur

	/*====================================================================================================*/	
	/*==deconnecterUtilisateur============================================================================*/
	/*====================================================================================================*/	


	/**
	 * Permet de deconnecter le conseiller
	 * 
	 * @return
	 */
	public String deconnecterUtilisateur() {
		System.out.println("Je suis dans deconnecterUtilisateur du MB authentification ");
		// recup du context jsp
		FacesContext contextJSF = FacesContext.getCurrentInstance();

		// recup de la session utilisateur
		HttpSession session = (HttpSession) contextJSF.getExternalContext().getSession(false);

		// Deconnexion = destruction de la session
		session.invalidate();

		// Initialisation d'un conseiller vide et sauvegarde dans l'attribut du managed
		// bean
		setUtilisateurLogged(new Utilisateur());

		// redirection vers authentification.xhtml
		return "authentification.xhtml";
	}// end deconnecterUtilisateur

}// end class
