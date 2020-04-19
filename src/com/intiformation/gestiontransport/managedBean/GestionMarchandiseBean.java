package com.intiformation.gestiontransport.managedBean;

import java.io.Serializable;
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
 * Managed bean pour la gestion des marchandises <br/>
 * 
 * @author Marie
 *
 */
@ManagedBean(name="gestion_marchandise")
@SessionScoped
public class GestionMarchandiseBean implements Serializable{

/*============================Propriétés=================================================*/
		// DAO de la marchandise
		private IMarchandiseDAO marchandiseDAO;
		private ICargaisonDAO cargaisonDAO;
	
		//Liste des identifiants des marchandises
		private List<Long> listeIDMarchandise;	
		
		//Identifiant de la marchandise séléctionnée
		private Long idMarchandiseSelectionner;
		
		private List<Long> listeIDCargaisonsBDD;
	
		
		//Propriété de la marchandise 
		private Marchandise marchandise;
		private Long idCargaisonMarchandise;


/*============================Construsteur=================================================*/		
		/**
		 * Ctor vide pour l'instanciation du Mb.
		 */
		public GestionMarchandiseBean() {
			marchandiseDAO = new MarchandiseDAOImpl();
			cargaisonDAO=new CargaisonDAOImpl();
		}
	
/*============================Méthodes=================================================*/
			
		/* ****************************************************** */
		/* *************** Liste de marchandises **************** */
		/* ******************************************************+ */
		/**
		 * Récupere la liste des marchandises à partir de la fonction getAll de la DAO pour affichage sur la page d'accueil et liste_all_marchandise.xhtml
		 * @return liste des cargaisons routières
		 */
		public List<Marchandise> findAllMarchandisesBdd(){
			
			List<Marchandise> listeMarchandiseBDD = marchandiseDAO.getAll();
			
			return listeMarchandiseBDD;
			
		}//end getAllMarchandisesBdd
	
		
		/* ****************************************************** */
		/* ****** Suppression d'une  marchandise **************** */
		/* ******************************************************+ */

		/**
		 * Supprime une marchandise de la bdd. Invoquée au click du bouton "supprimer" de la page afficher_marchandise.xhtml
		 * @param event
		 */
		public void supprimerMarchandise(ActionEvent event) {
			
			//1. recup de l'id de la marchandise à supprimer
			Long marchandiseID = marchandise.getIdMarchandise();
			
			//2. Recup du context
			FacesContext contextJSF = FacesContext.getCurrentInstance();
			
			//3. On fait la suppression
			marchandiseDAO.supprimer(marchandiseID);
			
		}//end supprimerMarchandise
		
		
		
		/* ****************************************************** */
		/* ****** Modification d'une marchandise via son Id ***** */
		/* ******************************************************+ */
	
		/**
		 * Invoquée au click du bouton modifier de la page modifier_marchandise.xhtml
		 * Modifie une marchandise dans la bdd
		 * @param event
		 */
		public void modifierMarchandise (ActionEvent event) {
			
			//On recupere la cargaison correspondant à l'id de cargaison indiqué dans le formulaire
			Cargaison cargaisonMarchandise = cargaisonDAO.getById(idCargaisonMarchandise);
			
			//Test du type de la cargaison
			
			if(cargaisonMarchandise instanceof CargaisonAerienne) {
				//=> Si la cargaison sélectionnée est aerienne, il y a un poids maximum
				double poidsMax=((CargaisonAerienne) cargaisonMarchandise).getPoids();
				double poidsTotal=cargaisonDAO.getPoidsTotal(idCargaisonMarchandise);
				double poidsMarchandise=marchandise.getPoids();
				
				//On vérifie que le poids total de la cargaison ne va pas depasser le poids maximum en ajoutant la nouvelle marchandise
				if((poidsTotal+poidsMarchandise)<=poidsMax) {
					//=> Le poids ne depasse pas le poids max
					//On attribut cette cargaison à la propriété marchandise
					marchandise.setCargaison(cargaisonMarchandise);
					
					//On modifie la marchandise dans la bdd
					marchandiseDAO.modifier(marchandise);
				}else {
					//=> Le poids  depasse  le poids max
					
					//recup context
					FacesContext contextJSF = FacesContext.getCurrentInstance();
					
					//message vers vue
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
							"La modification a échoué.", 
							" La marchandise n'a pas été modifiée car son poids est trop élevé. \n "
							+ "Poids maximum de la cargaison "+ idCargaisonMarchandise + " : "+ poidsMax +" kg. \n"
							+ "Poids actuelle de la cargaison : " + poidsTotal+" kg. \n"
							+ "Poids de la marchandise à ajouter : "+ poidsMarchandise+" kg. \n");
					
					contextJSF.addMessage(null, message);
				}//end else
			}else {
				//=> Si la cargaison sélectionnée est routiere, il y a une température à respecter : si la température necessaire à la conservation de la marchandise est plus faibke que celle de la cargaison, la marchandise risque d'etre endommagée.
				// NB : on part du principe qu'une marchandise peut supporter une température plus faible que sa température optimale mais pas une température plus élevée.
				double temperatureCargaison=((CargaisonRoutiere)cargaisonMarchandise).getTemperature();
				double temperatureMarchandise=marchandise.getTemperature();
				
				//On vérifie que la température de la cargaison est égale ou plus faible que la température de la marchandise
				if(temperatureMarchandise>=temperatureCargaison) {
					//=> La température de la cargaison est plus faible que celle de la marchandise donc c'est bon.
					
					//On attribut cette cargaison à la propriété marchandise
					marchandise.setCargaison(cargaisonMarchandise);
					
					//On modifie la marchandise dans la bdd
					marchandiseDAO.modifier(marchandise);
					
				}else {
					//=> La température de la cargaison est plus élevée que celle de la marchandise.
					
					//recup context
					FacesContext contextJSF = FacesContext.getCurrentInstance();
					
					//message vers vue
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
							"La modification a échoué.", 
							" La marchandise n'a pas été modifiée car sa température inférieur à celle de la cargaison. \n"
							+ "Température de la cargaison "+ idCargaisonMarchandise + " : "+ temperatureCargaison+"°C. \n" 
							+ "Température de la marchandise à ajouter : "+ temperatureMarchandise+"°C. \n");
					
					contextJSF.addMessage(null, message);
				}//end else
				
			}//end else
		}//end modifierMarchandise
		
		

		/* ****************************************************** */
		/* ****** Ajout d'une marchandise *********************** */
		/* ****************************************************** */
		/**
		 * Invoquée au click du bouton ajouter de la page ajouter_marchandise.xhtml
		 * Ajoute une marchandise dans la bdd et verifie que le poids ou la température correspond aux critères de la cargaison 
		 * @param event
		 */
		public void ajouterMarchandise(ActionEvent event) {
			

			
			//On recupere la cargaison correspondant à l'id de cargaison indiqué dans le formulaire
			Cargaison cargaisonMarchandise = cargaisonDAO.getById(idCargaisonMarchandise);
			
			//Test du type de la cargaison
			
			if(cargaisonMarchandise instanceof CargaisonAerienne) {
				//=> Si la cargaison sélectionnée est aerienne, il y a un poids maximum
				double poidsMax=((CargaisonAerienne) cargaisonMarchandise).getPoids();
				double poidsTotal=cargaisonDAO.getPoidsTotal(idCargaisonMarchandise);
				double poidsMarchandise=marchandise.getPoids();
				
				//On vérifie que le poids total de la cargaison ne va pas depasser le poids maximum en ajoutant la nouvelle marchandise
				if((poidsTotal+poidsMarchandise)<=poidsMax) {
					//=> Le poids ne depasse pas le poids max
					//On attribut cette cargaison à la propriété marchandise
					marchandise.setCargaison(cargaisonMarchandise);
					
					//On ajoute la marchandise à la DAO
					marchandiseDAO.ajouter(marchandise);
				}else {
					//=> Le poids  depasse  le poids max
					
					//recup context
					FacesContext contextJSF = FacesContext.getCurrentInstance();
					
					//message vers vue
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
							"L'ajout a échoué.", 
							" La marchandise n'a pas été ajoutée car son poids est trop élevé. \n"
							+ "Poids maximum de la cargaison "+ idCargaisonMarchandise + " : "+ poidsMax +" kg. \n"
							+ "Poids actuelle de la cargaison : " + poidsTotal+" kg. \n"
							+ "Poids de la marchandise à ajouter : "+ poidsMarchandise+" kg. \n");
					
					contextJSF.addMessage(null, message);
				}//end else
			}else {
				//=> Si la cargaison sélectionnée est routiere, il y a une température à respecter : si la température necessaire à la conservation de la marchandise est plus faibke que celle de la cargaison, la marchandise risque d'etre endommagée.
				// NB : on part du principe qu'une marchandise peut supporter une température plus faible que sa température optimale mais pas une température plus élevée.
				double temperatureCargaison=((CargaisonRoutiere)cargaisonMarchandise).getTemperature();
				double temperatureMarchandise=marchandise.getTemperature();
				
				//On vérifie que la température de la cargaison est égale ou plus faible que la température de la marchandise
				if(temperatureMarchandise>=temperatureCargaison) {
					//=> La température de la cargaison est plus faible que celle de la marchandise donc c'est bon.
					//On attribut cette cargaison à la propriété marchandise
					marchandise.setCargaison(cargaisonMarchandise);
					
					//On ajoute la marchandise à la DAO
					marchandiseDAO.ajouter(marchandise);
					
				}else {
					//=> La température de la cargaison est plus élevée que celle de la marchandise.
					
					//recup context
					FacesContext contextJSF = FacesContext.getCurrentInstance();
					
					//message vers vue
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
							"L'ajout a échoué.", 
							" La marchandise n'a pas été ajoutée car sa température inférieur à celle de la cargaison. \n"
							+ "Température de la cargaison "+ idCargaisonMarchandise + " : "+ temperatureCargaison +"°C.00 \n"
							+ "Température de la marchandise à ajouter : "+ temperatureMarchandise +"°C. \n");
					
					contextJSF.addMessage(null, message);
				}//end else
				
			}//end else
			
		}//end ajouterMarchandise 

		
		

/*============================Méthodes pour les templates=================================================*/
		
		/* ****************************************************** */
		/* ****** Initialiser marchandise *********************** */
		/* ****************************************************** */
			/**
			 * Invoqué au click du bouton 'ajouter une marchandise' de la bar de menu
			 * Permet d'initialiser un objet de type Marchandise à vide pour recuperer les valeurs saisies dans le formulaire de la page ajouter_marchandise.xhtml
			 */
			public void initialiserMarchandise(ActionEvent event){
				
				System.out.println("Je suis dans initialiserMarchandise");
				
				//Instanciation d'un nouvel objet marchandise vide dans lequel on va stocker les infos de la nouvelle marchandise via le formulaire
				Marchandise addMarchandise = new Marchandise();
				
				//Affectation de l'objet à la prop marchandise du MB
				setMarchandise(addMarchandise);
				
				//Recup de la liste des id des cargaisons
				System.out.println("Récupération de la liste des id cargaisons");
				
				//Recuperation de la liste de toutes les cargaisons
				List<Cargaison> listeAllCargaison = cargaisonDAO.getAll();
				
				//init de la liste des ID
				List<Long> listeIDCargaison =new ArrayList<>();
				
				//Recup des IDs
				for(Cargaison cargaison : listeAllCargaison) {
					listeIDCargaison.add(cargaison.getIdCargaison());
				}//end for
				
				listeIDCargaisonsBDD = listeIDCargaison;
				
				
				
			}//end initialiserMarchandise
			
			
			
			/* ************************************************************* */
			/* ****** Selection d'une marchandise sans param ***************** */
			/* ************************************************************* */	

			/**
			 * Invoquée au click du bouton Modifier de la page afficher_marchandise.xhtml <br/>
			 * Permet de stocker les info de la marchandise affichée dans la propriétée 'marchandise' du managed bean.
			 * @param event
			 */
				
			public void selectionnerMarchandiseSansParam(ActionEvent event) {

				//1. Recup de l'id de la marchandise
				Long idMarchandiseSelec=getIdMarchandiseSelectionner();
					
				//2. recup de la marchandise dans la bdd par l'id
				Marchandise marchandiseEdit = marchandiseDAO.getById(idMarchandiseSelec);
					
				//3. affectation de la marchandise sélectionnée à la prop marchandise du managedbean
				setMarchandise(marchandiseEdit);

				
				//Recuperation de la liste de toutes les cargaisons
				List<Cargaison> listeAllCargaison = cargaisonDAO.getAll();
				
				//init de la liste des ID
				List<Long> listeIDCargaison =new ArrayList<>();
				
				//Recup des IDs
				for(Cargaison cargaison : listeAllCargaison) {
					listeIDCargaison.add(cargaison.getIdCargaison());
				}//end for
				
				listeIDCargaisonsBDD = listeIDCargaison;
				
				}//end selectionnerMarchandiseSansParam
				
			/* ************************************************************* */
			/* ****** Selection d'une marchandise avec param ***************** */
			/* ************************************************************* */	
			/**
			 * Invoquée au click du bouton Afficher de la page accueil.xhtml et liste_all_marchandises.xhtml <br/>
			 * Permet de stocker les info de la marchandise selectionnée dans la propriétée 'marchandise' du managed bean.
			 * @param event
			 */
			
			public void selectionnerMarchandise(ActionEvent event) {
				System.out.println("Je suis dans selectionnerCargaison du MB de Compte");

				//1. récup du param passé dans le composant afficherID au click du lien afficher
				UIParameter cp = (UIParameter) event.getComponent().findComponent("afficherID");
				
				//2. Recup de la valeur du param => l'id de la marchandise à afficher
				Long marchandiseID = (Long) cp.getValue();
				
				//2. recup de la marchandise dans la bdd par l'id
				Marchandise marchandiseAffichage = marchandiseDAO.getById(marchandiseID);
					
				//3. affectation de la marchandise sélectionnée à la prop marchandise du managedbean
				setMarchandise(marchandiseAffichage);
				
			}//end selectionnerCargaison
				
	
			
			/* ****************************************************** */
			/* ****** Recuperer la liste des id des marchandises  ***** */
			/* ****************************************************** */
			/**
			 * Invoquée au click du bouton 'Chercher une marchandise' de la bar de menu <br/>
			 * Initialise la prop 'marchandise' du mb 
			 * @param event
			 */
			public void findListIdMarchandise(ActionEvent event) {

				//Recuperation de la liste de toutes les cargaisons
				List<Marchandise> listeAllMarchandise = marchandiseDAO.getAll();
				
				//init de la liste des ID
				List<Long> listeIDMarchandise =new ArrayList<>();
				
				//Recup des IDs
				for(Marchandise marchandise : listeAllMarchandise) {
					listeIDMarchandise.add(marchandise.getIdMarchandise());
				}//end for
				
				//Sauvegarde dans la prop "listeIDCargaison"
				setListeIDMarchandise(listeIDMarchandise);
			}//end initialiserCargaison 
			
			
/*============================Getter Setter=================================================*/
			
			public Marchandise getMarchandise() {
				return marchandise;
			}
			public void setMarchandise(Marchandise marchandise) {
				this.marchandise = marchandise;
			}

			public List<Long> getListeIDMarchandise() {
				return listeIDMarchandise;
			}

			public void setListeIDMarchandise(List<Long> listeIDMarchandise) {
				this.listeIDMarchandise = listeIDMarchandise;
			}



			public Long getIdMarchandiseSelectionner() {
				return idMarchandiseSelectionner;
			}

			public void setIdMarchandiseSelectionner(Long idMarchandiseSelectionner) {
				this.idMarchandiseSelectionner = idMarchandiseSelectionner;
			}

			public ICargaisonDAO getCargaisonDAO() {
				return cargaisonDAO;
			}

			public void setCargaisonDAO(ICargaisonDAO cargaisonDAO) {
				this.cargaisonDAO = cargaisonDAO;
			}

			public Long getIdCargaisonMarchandise() {
				return idCargaisonMarchandise;
			}

			public void setIdCargaisonMarchandise(Long idCargaisonMarchandise) {
				this.idCargaisonMarchandise = idCargaisonMarchandise;
			}

			public List<Long> getListeIDCargaisonsBDD() {
				return listeIDCargaisonsBDD;
			}

			public void setListeIDCargaisonsBDD(List<Long> listeIDCargaisonsBDD) {
				this.listeIDCargaisonsBDD = listeIDCargaisonsBDD;
			}

			public IMarchandiseDAO getMarchandiseDAO() {
				return marchandiseDAO;
			}

			public void setMarchandiseDAO(IMarchandiseDAO marchandiseDAO) {
				this.marchandiseDAO = marchandiseDAO;
			}		
			
			
}//end classe
