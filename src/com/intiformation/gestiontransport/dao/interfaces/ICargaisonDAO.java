package com.intiformation.gestiontransport.dao.interfaces;

import java.util.List;

import com.intiformation.gestiontransport.entity.Cargaison;
import com.intiformation.gestiontransport.entity.Marchandise;

/**
 * Interface pour la dao des Cargaisons.
 * Hérite de l'interface générale IGenericDAO. 
 * 
 * @author IN-MP-018
 *
 */
public interface ICargaisonDAO extends IGenericDAO<Cargaison>{
	
	/*=========== Méthodes propres aux cargaisons =========*/
	/**
	 * Récupère la liste des marchandise dans la cargaison
	 * @param idCargaison = identifiant de la cargaison dont on veut la liste des marchandises
	 * @return Liste des marchandises
	 */
	public List<Marchandise> getMarchandise(Long idCargaison);
	
	
	/**
	 * Renvoie le volume total de la cargaison (somme des volumes de chaque marchadise)
	 * @param idCargaison = identifiant de la cargaison dont on veut le volume total
	 * @return volume total (double)
	 */
	public double getVolumeTotal(Long idCargaison);
	
	
	/**
	 * Renvoie le poids total de la cargaison (somme des poids de chaque marchadise)
	 * @param idCargaison = identifiant de la cargaison dont on veut le poids total
	 * @return poids total (double)
	 */
	public double getPoidsTotal(Long idCargaison);
	
	
	/**
	 * Renvoie le cout de la cargaison 
	 * @param idCargaison = identifiant de la cargaison dont on veut cout
	 * @return cout (double)
	 */
	public double getCout(Long idCargaison);
	
}//end interface
