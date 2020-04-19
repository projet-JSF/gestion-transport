package com.intiformation.gestiontransport.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Classe entité. Fille de la classe Cargaison
 * @author Laure
 *
 */
@Entity
@DiscriminatorValue("routiere") //Gestion de l'héritage :  valeur indiquée dans la colonne discriminante dans la table de la bdd
public class CargaisonRoutiere extends Cargaison{
	
	/*=================================Propriétés===============================================*/

	@Column(name="temperature")
	private double temperature;

	
	/*=================================Constructeurs===============================================*/
	/**
	 * Constructeur vide
	 */
	public CargaisonRoutiere() {
	}
	
	/**
	 * Constructeur chargé
	 * @param idCargaison
	 * @param reference
	 * @param distance
	 * @param dateLivraison
	 * @param temperature
	 */
	public CargaisonRoutiere(Long idCargaison, String reference, double distance, String dateLivraison, double temperature) {
		super(idCargaison, reference, distance, dateLivraison);
		this.temperature = temperature;
	}

	/**
	 * Constructeur chargé sans l'id
	 * @param reference
	 * @param distance
	 * @param dateLivraison
	 * @param temperature
	 */
	public CargaisonRoutiere(String reference, double distance, String dateLivraison, double temperature) {
		super(reference, distance, dateLivraison);
		this.temperature = temperature;
	}
	
	
	/*=================================Getter/Setter===============================================*/

	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	@Override
	public String toString() {
		return "CargaisonRoutiere [idCargaison=" + super.getIdCargaison() + ", reference=" + super.getReference() + ", distance=" + super.getDistance()
				+ ", dateLivraison=" + super.getDateLivraison() + ", temperature=" + temperature + "]";
	}


	
	

}//end class
