package com.intiformation.gestiontransport.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Classe entité. 
 * En relation ManyToOne avec l'entité Cargaison 
 * @author IN-MP-039
 *
 */
@Entity(name="marchandise")
@Table(name="marchandises")
public class Marchandise implements Serializable{
	
	/*=================================Propriétés===============================================*/
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_marchandise")
	private Long idMarchandise;
	
	@Column(name="nom")
	private String nom; 
	
	@Column(name="poids")
	private double poids;
	
	@Column(name="temperature")
	private double temperature; 
	
	@Column(name="volume")
	private double volume;
	
	//Relation OneToMany avec la table cargaisons => Gestion de la FK
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="cargaison_id", referencedColumnName="id_cargaison")
	private Cargaison cargaison;
	
	
	/*=================================Constructeurs===============================================*/

	/**
	 * Ctor vide
	 */
	public Marchandise() {
		super();
	}
	
	/**
	 * Ctor chargé
	 * @param idMarchandise
	 * @param nom
	 * @param poids
	 * @param temperature
	 * @param volume
	 * @param cargaisonId
	 */
	public Marchandise(Long idMarchandise, String nom, double poids, double temperature, double volume) {
		super();
		this.idMarchandise = idMarchandise;
		this.nom = nom;
		this.poids = poids;
		this.temperature = temperature;
		this.volume = volume;
	}
	
	/**
	 * Ctor sans idMarchandise
	 * @param nom
	 * @param poids
	 * @param temperature
	 * @param volume
	 * @param cargaisonId
	 */
	public Marchandise(String nom, double poids, double temperature, double volume) {
		super();
		this.nom = nom;
		this.poids = poids;
		this.temperature = temperature;
		this.volume = volume;
	}
	
	


	/*=================================Getter/Setter===============================================*/

	public Long getIdMarchandise() {
		return idMarchandise;
	}
	public void setIdMarchandise(Long idMarchandise) {
		this.idMarchandise = idMarchandise;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public double getPoids() {
		return poids;
	}
	public void setPoids(double poids) {
		this.poids = poids;
	}
	public double getTemperature() {
		return temperature;
	}
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}
	public double getVolume() {
		return volume;
	}
	public void setVolume(double volume) {
		this.volume = volume;
	}

	public Cargaison getCargaison() {
		return cargaison;
	}

	public void setCargaison(Cargaison cargaison) {
		this.cargaison = cargaison;
	}

	@Override
	public String toString() {
		return "Marchandise [idMarchandise=" + idMarchandise + ", nom=" + nom + ", poids=" + poids + ", temperature="
				+ temperature + ", volume=" + volume + ", cargaison=" + cargaison + "]";
	}


	
	
}//end classe
