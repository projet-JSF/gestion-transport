package com.intiformation.gestiontransport.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Classe entité. Mère des classes Cargaison Aerienne et CargaisonRoutiere.
 * En relation OneToMany avec l'entité Marchandise 
 * @author IN-MP-039
 *
 */
@Entity(name="cargaison")
@Table(name="cargaisons")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)//Stratégie groupement une table
@DiscriminatorColumn(name="type_cargaison", discriminatorType=DiscriminatorType.STRING)//gérer la colonne discriminate
public class Cargaison implements Serializable{

	/*=================================Propriétés===============================================*/
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_cargaison")
	private Long idCargaison;
	
	@Column(name="reference")
	private String reference; 
	
	@Column(name="distance")
	private double distance; 
	
	@Column(name="date_livraison")
	private String dateLivraison; 

	//Liaison avec la table marchandises
	@OneToMany(mappedBy="cargaison", cascade=CascadeType.ALL)
	private List<Marchandise> listeMarchandise;
	
	
	/*=================================Constructeurs===============================================*/
	
	/**
	 * Ctor vide.
	 */
	public Cargaison() {
		super();
	}

	/**
	 * Ctor chargé
	 * @param idCargaison
	 * @param reference
	 * @param distance
	 * @param dateLivraison
	 */
	public Cargaison(Long idCargaison, String reference, double distance, String dateLivraison) {
		super();
		this.idCargaison = idCargaison;
		this.reference = reference;
		this.distance = distance;
		this.dateLivraison = dateLivraison;
	}

	/**
	 * Ctor sans idCargaison
	 * @param reference
	 * @param distance
	 * @param dateLivraison
	 */
	public Cargaison(String reference, double distance, String dateLivraison) {
		super();
		this.reference = reference;
		this.distance = distance;
		this.dateLivraison = dateLivraison;
	}

	
	/*=================================Getter/Setter===============================================*/

	public Long getIdCargaison() {
		return idCargaison;
	}

	public void setIdCargaison(Long idCargaison) {
		this.idCargaison = idCargaison;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public String getDateLivraison() {
		return dateLivraison;
	}

	public void setDateLivraison(String dateLivraison) {
		this.dateLivraison = dateLivraison;
	}

	public List<Marchandise> getListeMarchandise() {
		return listeMarchandise;
	}

	public void setListeMarchandise(List<Marchandise> listeMarchandise) {
		this.listeMarchandise = listeMarchandise;
	}

	@Override
	public String toString() {
		return "Cargaison [idCargaison=" + idCargaison + ", reference=" + reference + ", distance=" + distance
				+ ", dateLivraison=" + dateLivraison + "]";
	}


	

}//end classe
