package com.intiformation.gestiontransport.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Classe entité Utilisateur
 * @author IN-MP-039
 *
 */

@Entity(name="utilisateur") //Déclarer la classe comme entité
@Table(name="utilisateurs") // Choix du nom de la table

//Named Query pour la fonction IsUtilisateurExist : 
@NamedQueries({@NamedQuery(name="Utilisateur.isExists", 
query="SELECT COUNT(f.idUtilisateur) FROM utilisateur f WHERE f.login = ?1 AND f.motDePasse = ?2")}) 

public class Utilisateur implements Serializable {

	/*=================================Propriétés===============================================*/
	@Id //Pour désigner la clé primaire
	@GeneratedValue(strategy=GenerationType.IDENTITY) //Pour l'auto-incrementation
	@Column(name="id_utilisateur") //nom de la colonne dans la table de la bdd
	private Long idUtilisateur; 
	
	@Column(name="nom")//nom de la colonne dans la table de la bdd
	private String nom;
	
	@Column(name="login")//nom de la colonne dans la table de la bdd
	private String login;
	
	@Column(name="mot_de_passe")//nom de la colonne dans la table de la bdd
	private String motDePasse;
	
	
	/*=================================Constructeurs=============================================*/
	/**
	 * Ctor vide
	 */
	public Utilisateur() {
		super();
	}
	
	/**
	 * Ctor chargé
	 * @param idUtilisateur
	 * @param nom
	 * @param login
	 * @param motDePasse
	 */
	public Utilisateur(Long idUtilisateur, String nom, String login, String motDePasse) {
		super();
		this.idUtilisateur = idUtilisateur;
		this.nom = nom;
		this.login = login;
		this.motDePasse = motDePasse;
	}
	
	/**
	 * Ctor sans IdUtilisateur
	 * @param nom
	 * @param login
	 * @param motDePasse
	 */
	public Utilisateur(String nom, String login, String motDePasse) {
		super();
		this.nom = nom;
		this.login = login;
		this.motDePasse = motDePasse;
	}

	
	
	
	/*=================================Getter/Setter===============================================*/
	
	public Long getIdUtilisateur() {
		return idUtilisateur;
	}

	public void setIdUtilisateur(Long idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

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

	@Override
	public String toString() {
		return "Utilisateur [idUtilisateur=" + idUtilisateur + ", nom=" + nom + ", login=" + login + ", motDePasse="
				+ motDePasse + "]";
	} 
	
	
	
}//end classe
