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
@DiscriminatorValue("aerienne")
public class CargaisonAerienne extends Cargaison{

	/*=================================Propriétés===============================================*/

	@Column(name="poids")
	private double poids;

	/*=================================Constructeurs===============================================*/
	/**
	 * Constructeur vide
	 */
	public CargaisonAerienne() {

	}

	/**
	 * Constructeur chargé
	 * @param idCargaison
	 * @param reference
	 * @param distance
	 * @param dateLivraison
	 * @param poids
	 */
	public CargaisonAerienne(Long idCargaison, String reference, double distance, String dateLivraison,double poids) {
		super(idCargaison, reference, distance, dateLivraison);
		this.poids = poids;
	}
	/**
	 * Constructeur chargé sans l'id
	 * @param reference
	 * @param distance
	 * @param dateLivraison
	 * @param poids
	 */
	public CargaisonAerienne(String reference, double distance, String dateLivraison,double poids) {
		super(reference, distance, dateLivraison);
		this.poids = poids;
	}
	
	/*=================================Getter/Setter===============================================*/

	public double getPoids() {
		return poids;
	}

	public void setPoids(double poids) {
		this.poids = poids;
	}
	
	@Override
	public String toString() {
		return "CargaisonAerienne [idCargaison=" + super.getIdCargaison() + ", reference=" + super.getReference() + ", distance=" + super.getDistance()
				+ ", dateLivraison=" + super.getDateLivraison() + ", poids=" + poids + "]";
	}

}//end class
