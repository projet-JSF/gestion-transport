package com.intiformation.gestiontransport.managedBean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.intiformation.gestiontransport.dao.implementations.MarchandiseDAOImpl;
import com.intiformation.gestiontransport.dao.interfaces.IMarchandiseDAO;
import com.intiformation.gestiontransport.entity.Marchandise;

/**
 * Classe pour la gestion des marchandises <br/>
 * 
 * @author Marie
 *
 */
@ManagedBean(name="gestion_marchandise")
@SessionScoped
public class GestionMarchandiseBean implements Serializable{

/*============================Propri�t�s=================================================*/
		List<Marchandise> listeMarchandiseBDD;
	
		//Propri�t� de la marchandise 
		private Marchandise marchandise;
		
		// DAO de la marchandise
		IMarchandiseDAO marchandiseDAO;

/*============================Construsteur=================================================*/		
		/**
		 * Ctor vide pour l'instanciation du Mb.
		 */
		public GestionMarchandiseBean() {
			marchandiseDAO = new MarchandiseDAOImpl();
		}
	
/*============================M�thodes=================================================*/
			
		/* ****************************************************** */
		/* *************** Liste de marchandises **************** */
		/* ******************************************************+ */
		
		public List<Marchandise> getAllMarchandisesBdd(){
			
			listeMarchandiseBDD = marchandiseDAO.getAll();
			
			return listeMarchandiseBDD;
			
		}//end getAllMarchandisesBdd
	
		
		/* ****************************************************** */
		/* ****** Suppression d'une  marchandise **************** */
		/* ******************************************************+ */
		
		public void supprimerMarchandise(ActionEvent event) {
			
			//1.R�cup�ration de param�tre pass� dans le composant au click.
			UIParameter cp = (UIParameter) event.getComponent().findComponent("deleteID");
			
			//2.Recup de la valeur du parametre
			Long marchandiseID = (Long) cp.getValue();
			
			//3. suppresion de la marchandise dans la BDD
			//envoit d'uun message vers la vue via context
			FacesContext contextJSF =FacesContext.getCurrentInstance();
			
			//4. R�cup de la listeMarchandise
			System.out.println("R�cup�ration de la liste de marchandises");
			
			listeMarchandiseBDD = (List<Marchandise>) marchandiseDAO.getById(marchandiseID);
			
			//5. V�rif que la marchandise � bien �t� supprimer de la liste.
				if (marchandiseID == null) {
					System.out.println("La marchandise � bien �t� supprim� de la liste.");
				}else {
					System.out.println("La marchandise n'a pas �t� supprim�e de la liste.");
				}//end else
			
		}//end supprimerMarchandise
		
		
		/* ****************************************************** */
		/* ****** Afficher une marchandise via son Id ************ */
		/* ******************************************************+ */
		
		public void getMarchandiseById (ActionEvent event) {
			
			//1.recup de param pass� dans le composant ai click du lien
			UIParameter cp = (UIParameter) event.getComponent().findComponent("findID");
			
			//2.recup de la valeur du parametre
			Long marchandiseID =  (Long) cp.getValue();
			
			//3. recup de la marchandise dans la BDD
			Marchandise marchandise = marchandiseDAO.getById(marchandiseID);
			
			//affichage
			setMarchandise(marchandise);
		
}//end selection
		
		
		/* ****************************************************** */
		/* ****** Modification d'une marchandise via son Id ***** */
		/* ******************************************************+ */
		
		public void modifierMarchandise (ActionEvent event) {
		
			//Message vers la VUE
			FacesContext contextJSF = FacesContext.getCurrentInstance();
			
//			if (marchandiseDAO.modifier(marchandise)) {
//				//..........if ok
//				//message vers vue
//				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Modification r�ussie.", " - La marchandise a �t� modifi� avec succ�s.");
//				
//				contextJSF.addMessage(null, message);
//				
//			}else { ///..............not ok
//				
//				//message vers vue
//				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, " La modification �chou�.", "- La marchandise n'a pas �t� modifi�.");
//				
//				contextJSF.addMessage(null, message);
//				
//			}//end else
			
		}//end modifierMarchandise
		
		

		/* ****************************************************** */
		/* ****** Ajout d'une marchandise *********************** */
		/* ****************************************************** */
		
		public void ajouterMarchandise(ActionEvent event) {
			
			//recup context
			FacesContext contextJSF = FacesContext.getCurrentInstance();
			
			//ajout BDD
//			if (marchandiseDAO.ajouter(marchandise)) {
//				//-------------Ajout ok
//				//message vers vue
//				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Ajout r�ussie.", " - La marchandise a �t� ajout� avec succ�s.");
//				
//				contextJSF.addMessage(null, message);
//				
//				
//			}else {
//				//--------------Ajout not ok
//				//message vers vue
//				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, " L'ajout a �chou�.", "- La marchandise n'a pas �t� ajout�.");
//				
//				contextJSF.addMessage(null, message);
//				
//				
//			}//end else
			
		}//end ajouterMarchandise 

		
/*============================Getter Setter=================================================*/
		public Marchandise getMarchandise() {
			return marchandise;
		}

		public void setMarchandise(Marchandise marchandise) {
			this.marchandise = marchandise;
		}
		
		
}//end classe
