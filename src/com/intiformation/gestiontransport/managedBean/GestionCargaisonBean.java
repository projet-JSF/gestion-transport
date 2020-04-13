package com.intiformation.gestiontransport.managedBean;

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
	List<Cargaison> listeCargaisonsBDD;

	//Propriété cargaison
	private Cargaison cargaison;
	
	// DAO de la marchandise
	ICargaisonDAO cargaisonDAO;

/*============================Construsteur=================================================*/		
	/**
	 * Ctor vide pour l'instanciation du Mb.
	 * Instencie la dao des cargaisons
	 */
	public GestionCargaisonBean() {
		cargaisonDAO = new CargaisonDAOImpl();
	}
	
	/*============================Méthodes=================================================*/
	
	/* ****************************************************** */
	/* *************** Liste de cargaison  ****************** */
	/* ******************************************************+ */
	
	public List<Cargaison> getAllCargaisonsBdd(){
		
		listeCargaisonsBDD = cargaisonDAO.getAll();
		
		return listeCargaisonsBDD;
		
	}//end getAllCargaisonsBdd

	
	/* ****************************************************** */
	/* ****** Suppression d'une  cargaison **************** */
	/* ******************************************************+ */
	
	public void supprimerCargaison(ActionEvent event) {
		
		//1.Récupération de paramètre passé dans le composant au click.
		UIParameter cp = (UIParameter) event.getComponent().findComponent("deleteID");
		
		//2.Recup de la valeur du parametre
		Long cargaisonID = (Long) cp.getValue();
		
		//3. suppresion de la marchandise dans la BDD
		//envoit d'uun message vers la vue via context
		FacesContext contextJSF =FacesContext.getCurrentInstance();
		
		//Supression + message
		cargaisonDAO.supprimer(cargaisonID);
		
		if (cargaisonDAO.getById(cargaisonID)!=null) {
			
			//............Suppression OK....
			//message a envoye vers la vue
			contextJSF.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "La cargaison a été supprimée avec succès.", ""));
			
		}else {
			
			//............Suppression NOT OK....
			//message a envoye vers la vue
			contextJSF.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "La suppression de la cargaison a échoué.", ""));
			
		}//endelse
		
	}//end supprimerCargaison
	
	
	/* ****************************************************** */
	/* ****** Afficher une cargaison via son Id ************ */
	/* ******************************************************+ */
	
	public void getCargaisonById (ActionEvent event) {
		
		//1.recup de param passé dans le composant ai click du lien
		UIParameter cp = (UIParameter) event.getComponent().findComponent("findID");
		
		//2.recup de la valeur du parametre
		Long cargaisonID =  (Long) cp.getValue();
		
		//3. recup de la marchandise dans la BDD
		Cargaison cargaison = cargaisonDAO.getById(cargaisonID);
		
		//affichage
		setCargaison(cargaison);
	
	}//end getCargaisonById
	
	
	/* ****************************************************** */
	/* ****** Modification d'une cargaison via son Id ***** */
	/* ******************************************************+ */
	
	public void modifierCargaison (ActionEvent event) {
	
		//Message vers la VUE
		FacesContext contextJSF = FacesContext.getCurrentInstance();
		
		cargaisonDAO.modifier(cargaison);
		
	}//end modifierCargaison
	
	

	/* ****************************************************** */
	/* ****** Ajout d'une cargaison  ************************ */
	/* ****************************************************** */
	
	public void ajouterMarchandise(ActionEvent event) {
		
		//recup context
		FacesContext contextJSF = FacesContext.getCurrentInstance();
		
		//ajout BDD
		
		cargaisonDAO.ajouter(cargaison);
		
	}//end ajouterMarchandise 



	
/*============================Getter Setter=================================================*/

	public List<Cargaison> getListeCargaisonsBDD() {
		return listeCargaisonsBDD;
	}

	public void setListeCargaisonsBDD(List<Cargaison> listeCargaisonsBDD) {
		this.listeCargaisonsBDD = listeCargaisonsBDD;
	}

	public Cargaison getCargaison() {
		return cargaison;
	}

	public void setCargaison(Cargaison cargaison) {
		this.cargaison = cargaison;
	}
}//end class
