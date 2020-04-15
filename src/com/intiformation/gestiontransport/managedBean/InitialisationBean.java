package com.intiformation.gestiontransport.managedBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.intiformation.gestiontransport.dao.implementations.CargaisonDAOImpl;
import com.intiformation.gestiontransport.dao.implementations.MarchandiseDAOImpl;
import com.intiformation.gestiontransport.dao.implementations.UtilisateurDAOImpl;
import com.intiformation.gestiontransport.dao.interfaces.ICargaisonDAO;
import com.intiformation.gestiontransport.dao.interfaces.IMarchandiseDAO;
import com.intiformation.gestiontransport.dao.interfaces.IUtilisateurDAO;
import com.intiformation.gestiontransport.entity.Cargaison;
import com.intiformation.gestiontransport.entity.CargaisonAerienne;
import com.intiformation.gestiontransport.entity.CargaisonRoutiere;
import com.intiformation.gestiontransport.entity.Marchandise;
import com.intiformation.gestiontransport.entity.Utilisateur;

/**
 * Managed bean pour l'initialisation de la base de donnée à l'ouverture du site
 * @author Laure
 *
 */
@ManagedBean(name="initialisation")
@SessionScoped
public class InitialisationBean {

	/*============================Propriétées=================================================*/
	
	// DAO de la cargaison
	private ICargaisonDAO cargaisonDAO;
	
	//DAO des marchandises
	private IMarchandiseDAO marchandiseDAO;
	
	//DAO des utilisateurs
	private IUtilisateurDAO utilisateurDAO;
	
	/*============================Construsteur=================================================*/		
	/**
	 * Ctor vide pour l'instanciation du Mb.
	 * Instencie les dao 
	 */
	public InitialisationBean() {
		cargaisonDAO = new CargaisonDAOImpl();
		marchandiseDAO = new MarchandiseDAOImpl();
		utilisateurDAO = new UtilisateurDAOImpl();
	}
	
	/*============================Méthodes=================================================*/
	
	/* ****************************************************** */
	/* *************** Initialisation BDD  ****************** */
	/* ****************************************************** */
	
	/**
	 * Ajoute plusieurs cargaisons, marchandises et utilisateurs à la bdd.
	 * => Les tables sont vidées lorsque l'on ouvre l'application par la commande :
	 * <property name="javax.persistence.schema-generation.database.action"
	 *		value="drop-and-create" />
	 * située dans le fichier persistence.xml 
	 * => Permet de recommencer avec une bdd 'propre' pour les tests
	 */
	public void initValuesBDD() {
		
		// Création des cargaisons et marchandises à ajouter à la bdd
		
		Marchandise marchandise1=new Marchandise("Nounours", 5, 20, 10);
		Marchandise marchandise2=new Marchandise("Bonbons", 10, 15, 15);

		Cargaison cargaison1=new CargaisonAerienne("aerienne1", 1000.0, "01/01/2020", 100.0);
		
		Marchandise marchandise3=new Marchandise("Fraises", 20, 10, 30);
		Marchandise marchandise4=new Marchandise("Framboises", 15, 10, 25);

		Cargaison cargaison2=new CargaisonRoutiere("routiere1", 100, "15/06/2020", 5);
		
		// Attribution des cargaisons à chaque marchandise (relation manyToOne)
		
		marchandise1.setCargaison(cargaison1);
		marchandise2.setCargaison(cargaison1);
		
		
		marchandise3.setCargaison(cargaison2);
		marchandise4.setCargaison(cargaison2);
		
		//Ajout des marchandises à la BDD
		//=> L'ajout des cargaison se fera automatiquement grace à la relation ManyToOne

		marchandiseDAO.ajouter(marchandise1);
		marchandiseDAO.ajouter(marchandise2);
		
		marchandiseDAO.ajouter(marchandise3);
		marchandiseDAO.ajouter(marchandise4);
		
		//Création des objets utilisateurs
		
		Utilisateur utilisateur1 = new Utilisateur("Margot Denis", "margot", "0000");
		Utilisateur utilisateur2 = new Utilisateur("Nicolas Cayla", "nicolas", "0000");
		
		//Ajout des utilisateurs à la bdd
		
		utilisateurDAO.ajouter(utilisateur1);
		utilisateurDAO.ajouter(utilisateur2);
		
	}//end initValuesBDD

	
	/*============================Getters/Setters=================================================*/
	
	public ICargaisonDAO getCargaisonDAO() {
		return cargaisonDAO;
	}

	public void setCargaisonDAO(ICargaisonDAO cargaisonDAO) {
		this.cargaisonDAO = cargaisonDAO;
	}

	public IMarchandiseDAO getMarchandiseDAO() {
		return marchandiseDAO;
	}

	public void setMarchandiseDAO(IMarchandiseDAO marchandiseDAO) {
		this.marchandiseDAO = marchandiseDAO;
	}

	public IUtilisateurDAO getUtilisateurDAO() {
		return utilisateurDAO;
	}

	public void setUtilisateurDAO(IUtilisateurDAO utilisateurDAO) {
		this.utilisateurDAO = utilisateurDAO;
	}
	
	
	
}//end class
