package com.intiformation.gestiontransport.managedBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.intiformation.gestiontransport.dao.implementations.CargaisonDAOImpl;
import com.intiformation.gestiontransport.dao.implementations.MarchandiseDAOImpl;
import com.intiformation.gestiontransport.dao.interfaces.ICargaisonDAO;
import com.intiformation.gestiontransport.dao.interfaces.IMarchandiseDAO;
import com.intiformation.gestiontransport.entity.Cargaison;
import com.intiformation.gestiontransport.entity.CargaisonAerienne;
import com.intiformation.gestiontransport.entity.CargaisonRoutiere;
import com.intiformation.gestiontransport.entity.Marchandise;

/**
 * Managed bean pour la gestion des cargaisons
 * @author Laure
 *
 */
@ManagedBean(name="gestion_cargaison")
@SessionScoped
public class GestionCargaisonBean {
	
/*============================Propriétés=================================================*/
	//Liste des identifiants des cargaisons
	private List<Long> listeIDCargaison;

	//Identifiant de la cargaison séléctionnée
	private Long idCargaisonSelectionner;
	
	
	//Propriété cargaison
	private Cargaison cargaison;
	private String typeCargaison;
	private double volumeTotal;
	private double poidsTotal;
	private double cout;
	private double temperature;
	private double poids;
	
	// DAO de la cargaison
	private ICargaisonDAO cargaisonDAO;
	private IMarchandiseDAO marchandiseDAO;

/*============================Constructeur=================================================*/		
	/**
	 * Ctor vide pour l'instanciation du Mb.
	 * Instencie la dao des cargaisons
	 */
	public GestionCargaisonBean() {
		cargaisonDAO = new CargaisonDAOImpl();
		marchandiseDAO=new MarchandiseDAOImpl();
	}
	
	/*============================Méthodes=================================================*/
	
	/* ***************************************************************** */
	/* *************** Liste de cargaisons routières  ****************** */
	/* ***************************************************************** */
	/**
	 * Récupere la liste des cargaisons routières à partir de la fonction getAll de la DAO pour affichage sur la page d'accueil et liste_all_cargaisons.xhtml
	 * @return liste des cargaisons routières
	 */
	public List<Cargaison> findAllCargaisonsRoutieresBdd(){
		
		//Recuperation de la listes des cargaisons de la bdd
		List<Cargaison>listeCargaisonsBDD = cargaisonDAO.getAll();
		
		//Initialisation de la liste des cargaisons routières
		List<Cargaison> listeCargaisonsRoutieresBDD = new ArrayList<>();
		
		//AJout des cargaisons routières à la liste listeCargaisonsRoutieresBDD
		for(Cargaison cargaison : listeCargaisonsBDD) {
			if(cargaison instanceof CargaisonRoutiere) {
				listeCargaisonsRoutieresBDD.add(cargaison);
			}//end if
		}//end for
		
		return listeCargaisonsRoutieresBDD;
		
	}//end getAllCargaisonsRoutieresBdd

	
	
	/* ***************************************************************** */
	/* *************** Liste de cargaisons aeriennes  ****************** */
	/* ***************************************************************** */
	/**
	 * Récupere la liste des cargaisons aeriennes à partir de la fonction getALL de la DAO
	 * @return liste des cargaisons aeriennes
	 */
	public List<Cargaison> findAllCargaisonsAeriennesBdd(){
		
		//Recuperation de la listes des cargaisons de la bdd
		List<Cargaison>listeCargaisonsBDD = cargaisonDAO.getAll();
		
		//Initialisation de la liste des cargaisons aeriennes
		List<Cargaison> listeCargaisonsAeriennesBDD = new ArrayList<>();
		
		//AJout des cargaisons aeriennes à la liste listeCargaisonsAeriennesBDD
		for(Cargaison cargaison : listeCargaisonsBDD) {
			if(cargaison instanceof CargaisonAerienne) {
				listeCargaisonsAeriennesBDD.add(cargaison);
			}//end if
		}//end for
		
		return listeCargaisonsAeriennesBDD;
		
	}//end getAllCargaisonsRoutieresBdd
	
	/* ***************************************************************** */
	/* *************** Liste des marchandises d'une cargaison   ****************** */
	/* ***************************************************************** */
	/**
	 * Récupere la liste des marchandises d'une cargaison 
	 * @return liste des marchandises
	 */
	public List<Marchandise> getListeMarchandises(){
		
		Long idCargaison = getIdCargaisonSelectionner();
		//Recuperation de la listes des marchandises
		List<Marchandise> listeMarchandise = cargaisonDAO.getMarchandise(idCargaison);
		
		System.out.println(listeMarchandise);
		
		return listeMarchandise;
		
	}//end getAllCargaisonsRoutieresBdd
	
	/* ***************************************************** */
	/* ****** Suppression d'une  cargaison ***************** */
	/* ***************************************************** */
	
	/**
	 * Supprime une cargaison de la bdd. Invoquée au click du bouton 'supprimer' de la page afficher_cargaison.xhtml
	 * @param event
	 */
	public void supprimerCargaison(ActionEvent event) {
		
		//1. recup de l'id de la cargaison à supprimer
		Long cargaisonID = cargaison.getIdCargaison();
		System.out.println("Id de la cargaison à supprimer :"+ cargaisonID);
		
		//2. Recup du context
		FacesContext contextJSF = FacesContext.getCurrentInstance();
		
		//Recup de la liste des marchandises de la cargaison
		List<Marchandise> listeMarchandises = cargaisonDAO.getMarchandise(cargaisonID);
		
		//Suppression des marchandises de la cargaison
		for(Marchandise marchandise : listeMarchandises) {
			marchandiseDAO.supprimer(marchandise.getIdMarchandise());
		}//end for
		
		
		//3.Suppression de la cargaison
		cargaisonDAO.supprimer(cargaisonID);
		
	}//end supprimerCargaison
	

	/* ************************************************************* */
	/* ****** Selection d'une cargaison sans param ***************** */
	/* ************************************************************* */	

	/**
	 * Invoquée au click du bouton Modifier de la page afficher_cargaison.xhtml <br/>
	 * Permet de stocker les info de la cargaison affichée dans la propriétée 'cargaison' du managed bean.
	 * @param event
	 */
		
	public void selectionnerCargaisonSansParam(ActionEvent event) {
		System.out.println("Je suis dans selectionnerCargaisonSansParam du MB de Cargaison");

		//1. Recup de l'id de la cargaison
		Long idCargaisonSelec=getIdCargaisonSelectionner();
		
		System.out.println("id de la cargaison selectionnée : "+idCargaisonSelec);
		
		//2. recup de la cargaison dans la bdd par l'id
		Cargaison cargaisonEdit = cargaisonDAO.getById(idCargaisonSelec);
			
		//3. affectation de la cargaison sélectionnée à la prop cargaison du managedbean
		setCargaison(cargaisonEdit);
		
		//4. Recup du type de cargaison
		if(cargaisonEdit instanceof CargaisonAerienne) {
			setTypeCargaison("aerienne");
		}else {
			setTypeCargaison("routiere");
		}//end else
		
		// Recuperation de la liste des marchandises pour tester si elle est vide
		List<Marchandise> listeMarchandises=cargaisonDAO.getMarchandise(idCargaisonSelec);
		if(listeMarchandises.size()!=0) {
			//=> La liste des marchandises n'est pas vide 
			//5. Recup du volume total
			setVolumeTotal(cargaisonDAO.getVolumeTotal(idCargaisonSelec));
			
			//6. Recup du poid total
			setPoidsTotal(cargaisonDAO.getPoidsTotal(idCargaisonSelec));
			
			//7. recup du cout 
			setCout(cargaisonDAO.getCout(idCargaisonSelec));
		}else {
			//=> La liste des marchandises est vide 
			//5. Recup du volume total
			setVolumeTotal(0.0);
			
			//6. Recup du poid total
			setPoidsTotal(0.0);
			
			//7. recup du cout 
			setCout(0.0);
		}//end else
		
		}//end selectionnerCargaisonSansParam
		
	/* ************************************************************* */
	/* ****** Selection d'une cargaison avec param ***************** */
	/* ************************************************************* */	
	/**
	 * Invoquée au click du bouton Afficher de la page accueil.xhtml et liste_all_cargaisons.xhtml <br/>
	 * Permet de stocker les info de la cargaison selectionnée dans la propriétée 'cargaison' du managed bean.
	 * @param event
	 */
	
	public void selectionnerCargaison(ActionEvent event) {
		System.out.println("Je suis dans selectionnerCargaison du MB de Cargaison");

		//1. récup du param passé dans le composant afficherID au click du lien afficher
		UIParameter cp = (UIParameter) event.getComponent().findComponent("afficherID");
		
		//2. Recup de la valeur du param => l'id de la cargaison à afficher
		Long cargaisonID = (Long) cp.getValue();
		
		System.out.println("id de la cargaison selectionnée : "+cargaisonID);
		
		setIdCargaisonSelectionner(cargaisonID);
		
		//2. recup de la cargaison dans la bdd par l'id
		Cargaison cargaisonAffichage = cargaisonDAO.getById(cargaisonID);
			
		//3. affectation de la cargaison sélectionnée à la prop cargaison du managedbean
		setCargaison(cargaisonAffichage);
		
		//4. Recup du type de cargaison
		if(cargaisonAffichage instanceof CargaisonAerienne) {
			setTypeCargaison("aerienne");
		}else {
			setTypeCargaison("routiere");
		}//end else
		
		// Recuperation de la liste des marchandises pour tester si elle est vide
		List<Marchandise> listeMarchandises=cargaisonDAO.getMarchandise(cargaisonID);
		if(listeMarchandises.size()!=0) {
			//=> La liste des marchandises n'est pas vide 
			//5. Recup du volume total
			setVolumeTotal(cargaisonDAO.getVolumeTotal(cargaisonID));
			
			//6. Recup du poid total
			setPoidsTotal(cargaisonDAO.getPoidsTotal(cargaisonID));
			
			//7. recup du cout 
			setCout(cargaisonDAO.getCout(cargaisonID));
		}else {
			//=> La liste des marchandises est vide 
			//5. Recup du volume total
			setVolumeTotal(0.0);
			
			//6. Recup du poid total
			setPoidsTotal(0.0);
			
			//7. recup du cout 
			setCout(0.0);
			
			
		}//end else
		
		
	}//end selectionnerCargaison
		

	
	
	/* ****************************************************** */
	/* ****** Modification d'une cargaison ******************* */
	/* ****************************************************** */
	/**
	 * Invoquée au click du bouton Modifier de la page modifier_cargaison.xhtml <br/>
	 * Modifie la cargaison stockée dans la rpop 'cargaison' du mb dans la bdd
	 * @param event
	 */
	public void modifierCargaison (ActionEvent event) {
		
		cargaisonDAO.modifier(cargaison);
		
	}//end modifierCargaison
	
	

	/* ****************************************************** */
	/* ****** Ajout d'une cargaison  ************************ */
	/* ****************************************************** */
	/**
	 * Invoquée au click du bouton Ajouter de la page ajouter_cargaison.xhtml <br/>
	 * Ajoute la cargaison stockée dans la rpop 'cargaison' du mb dans la bdd
	 * @param event
	 */
	public void ajouterCargaison(ActionEvent event) {
		
		if(getTypeCargaison().equals("routiere")) {
			CargaisonRoutiere cargaisonRoutiere = new CargaisonRoutiere(cargaison.getReference(), cargaison.getDistance(), cargaison.getDateLivraison(), temperature);
			cargaisonDAO.ajouter(cargaisonRoutiere);
		}else {
			CargaisonAerienne cargaisonAerienne = new CargaisonAerienne(cargaison.getReference(), cargaison.getDistance(), cargaison.getDateLivraison(), poids);
			cargaisonDAO.ajouter(cargaisonAerienne);
		}
		
		//ajout BDD
		
		
		
	}//end ajouterCargaison 


	/* ****************************************************** */
	/* ****** Initialisation de cargaison  ****************** */
	/* ****************************************************** */
	/**
	 * Invoquée au click du bouton 'Ajouter une cargaison'de la bar de menu <br/>
	 * Initialise la prop 'cargaison' du mb 
	 * @param event
	 */
	public void initialiserCargaison(ActionEvent event) {

		Cargaison cargaison=new Cargaison();
		
		setCargaison(cargaison);
		
	}//end initialiserCargaison 

	
	/* ****************************************************** */
	/* ****** Recuperer la liste des id des cargaisons  ***** */
	/* ****************************************************** */
	/**
	 * Invoquée au click du bouton 'Chercher une cargaison'de la bar de menu <br/>
	 * Initialise la prop 'cargaison' du mb 
	 * @param event
	 */
	public void findListIdCargaison(ActionEvent event) {

		//Recuperation de la liste de toutes les cargaisons
		List<Cargaison> listeAllCargaison = cargaisonDAO.getAll();
		
		//init de la liste des ID
		List<Long> listeIDCargaison =new ArrayList<>();
		
		//Recup des IDs
		for(Cargaison cargaison : listeAllCargaison) {
			listeIDCargaison.add(cargaison.getIdCargaison());
		}//end for
		
		//Sauvegarde dans la prop "listeIDCargaison"
		setListeIDCargaison(listeIDCargaison);
	}//end initialiserCargaison 
	
/*============================Getter Setter=================================================*/



	public Cargaison getCargaison() {
		return cargaison;
	}

	public List<Long> getListeIDCargaison() {
		return listeIDCargaison;
	}

	public void setListeIDCargaison(List<Long> listeIDCargaison) {
		this.listeIDCargaison = listeIDCargaison;
	}

	public Long getIdCargaisonSelectionner() {
		return idCargaisonSelectionner;
	}

	public void setIdCargaisonSelectionner(Long idCargaisonSelectionner) {
		this.idCargaisonSelectionner = idCargaisonSelectionner;
	}

	public String getTypeCargaison() {
		return typeCargaison;
	}

	public void setTypeCargaison(String typeCargaison) {
		this.typeCargaison = typeCargaison;
	}

	public double getVolumeTotal() {
		return volumeTotal;
	}

	public void setVolumeTotal(double volumeTotal) {
		this.volumeTotal = volumeTotal;
	}

	public double getPoidsTotal() {
		return poidsTotal;
	}

	public void setPoidsTotal(double poidsTotal) {
		this.poidsTotal = poidsTotal;
	}

	public double getCout() {
		return cout;
	}

	public void setCout(double cout) {
		this.cout = cout;
	}

	public ICargaisonDAO getCargaisonDAO() {
		return cargaisonDAO;
	}

	public void setCargaisonDAO(ICargaisonDAO cargaisonDAO) {
		this.cargaisonDAO = cargaisonDAO;
	}

	public void setCargaison(Cargaison cargaison) {
		this.cargaison = cargaison;
	}

	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	public double getPoids() {
		return poids;
	}

	public void setPoids(double poids) {
		this.poids = poids;
	}
}//end class
