package com.intiformation.gestiontransport.dao.implementations;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.intiformation.gestiontransport.dao.interfaces.ICargaisonDAO;
import com.intiformation.gestiontransport.entity.Cargaison;
import com.intiformation.gestiontransport.entity.Marchandise;
import com.intiformation.gestiontransport.tool.JPAUtil;


/**
 * Implementation concrete de la dao pour les cargaisons.
 * Hérite de la classe JpaDAO pour récupérer les méthodes du CRUD et implémente l'interface ICargaisonDAO pour les méthodes spécifiques aux cargaisons
 * @author IN-MP-018
 *
 */
public class CargaisonDAOImpl extends JpaDAO<Cargaison> implements ICargaisonDAO{

	/**
	 * Constructeur vide pour initialiser l'entité dans JpaDAO
	 */
	public CargaisonDAOImpl() {
		super(Cargaison.class);
	}

	

	@Override
	public List<Marchandise> getMarchandise(Long idCargaison) {
		
		try {
			//1. recup de l'entity manager 
			EntityManager em=JPAUtil.getEntityManager();
			
			//3. Contenu de la requete JPAQ 
			
			String requeteSelect = "SELECT m FROM marchandise m WHERE m.cargaison.idCargaison = :pIdCargaison";
			
			//4. creation de la requete
			Query selectQuery =em.createQuery(requeteSelect);
			
			//passage de parametre
			selectQuery.setParameter("pIdCargaison", idCargaison);
	
			
			//5. exec +recup de resultat de la requete
			
			List<Marchandise> listeMarchandise=selectQuery.getResultList();

			
			return listeMarchandise;
		}catch (PersistenceException e){
			System.out.println("... Erreur lors de la recuperation de la liste des marchandises de la cargaison ....");
			e.printStackTrace();
		}
		return null;
	}//End getMarchandise

	
	
	
	
	
	
	@Override
	public double getVolumeTotal(Long idCargaison) {
		try {
			//1. recup de l'entity manager 
			EntityManager em=JPAUtil.getEntityManager();
			
			//3. Contenu de la requete JPAQ pour la modif
			
			String requeteSelect = "SELECT SUM(m.volume) FROM marchandise m WHERE m.cargaison.idCargaison = :pIdCargaison";
	
			//4. creation de la requete
			Query selectQuery =em.createQuery(requeteSelect);
			
			//passage de parametre
			selectQuery.setParameter("pIdCargaison", idCargaison);
	
			
			//5. exec +recup de resultat de la requete
			
			double volumeTotal=(double) selectQuery.getSingleResult();
			
			return volumeTotal;
			
		}catch (PersistenceException e){
			System.out.println("... Erreur lors du calcule du volume total de la cargaison ....");
			e.printStackTrace();
		}
		return 0;
	}//end getVolumeTotal

	
	@Override
	public double getPoidsTotal(Long idCargaison) {
		try {	
			//1. recup de l'entity manager 
			EntityManager em=JPAUtil.getEntityManager();
			
			//3. Contenu de la requete JPAQ pour la modif
			
			String requeteSelect = "SELECT SUM(m.poids) FROM marchandise m WHERE m.cargaison.idCargaison = :pIdCargaison";
	
			//4. creation de la requete
			Query selectQuery =em.createQuery(requeteSelect);
			
			//passage de parametre
			selectQuery.setParameter("pIdCargaison", idCargaison);
			
			//5. exec +recup de resultat de la requete
			
			double poidsTotal=(double) selectQuery.getSingleResult();
			
			return poidsTotal;
		}catch (PersistenceException e){
			System.out.println("... Erreur lors du calcul du poids total de la cargaison ....");
			e.printStackTrace();
		}
		return 0;
	}//end getPoidsTotal

	
	@Override
	public double getCout(Long idCargaison) {
		try {
			//1. recup de l'entity manager 
			EntityManager em=JPAUtil.getEntityManager();		
			
			//RECUPERATION DE LA CARGAISON
			Cargaison cargaison = em.find(Cargaison.class, idCargaison);
		
			
			//RECUPERATION DU TYPE
			//3. Contenu de la requete JPAQ pour la modif
			//NB  : TYPE() permet de viser la colonne discriminatoire (c'est à dire la foreign key)
			
			String requeteGetTypeCargaison = "SELECT TYPE(c) FROM cargaison c WHERE c.idCargaison = :pIdCargaison";
	
			//4. creation de la requete
			Query selectQuery =em.createQuery(requeteGetTypeCargaison);
			
			//passage de parametre
			selectQuery.setParameter("pIdCargaison", idCargaison);
	
			
			//5. exec +recup de resultat de la requete
			// NB : la requete renvoie un objet de type CargaisonAerienne ou CargaisonRoutiere
			String typeCargaison;
			if(selectQuery.getSingleResult().toString().equals("com.intiformation.gestiontransport.entity.CargaisonAerienne")){
				typeCargaison="aerienne";
			}else {
				typeCargaison="routiere";
			}
						
			//RECUPERATION DU POIDS TOTAL
			double poidsTotal=this.getPoidsTotal(idCargaison);
			
			//RECUPERATION DU VOLUME TOTAL
			double volumeTotal = this.getVolumeTotal(idCargaison);
			
			//CALCUL DU COUT
			double cout;
			if (typeCargaison.equals("aerienne")){
				if(volumeTotal<80000) {
					cout=10*cargaison.getDistance()*poidsTotal;
				}else {
					cout=12*cargaison.getDistance()*poidsTotal;
				}//end else
					
			}else {
				if(volumeTotal<380000) {
					cout=4*cargaison.getDistance()*poidsTotal;
				}else {
					cout=6*cargaison.getDistance()*poidsTotal;
				}//end else
			}//end else
			
	
			return cout;
			
		}catch (PersistenceException e){
			System.out.println("... Erreur lors du calcul du poids total de la cargaison ....");
			e.printStackTrace();
		}
		return 0;
	}//end getCout

}//end class
