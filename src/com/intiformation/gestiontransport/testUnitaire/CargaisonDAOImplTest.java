package com.intiformation.gestiontransport.testUnitaire;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.intiformation.gestiontransport.dao.implementations.CargaisonDAOImpl;
import com.intiformation.gestiontransport.dao.implementations.MarchandiseDAOImpl;
import com.intiformation.gestiontransport.entity.Cargaison;
import com.intiformation.gestiontransport.entity.Marchandise;

public class CargaisonDAOImplTest {
	
	//======================================================================//
	//================ testAjouterCargaison ================================//
	//======================================================================//
	
	/**
	 * Test l'ajout d'une cargaison dans la bdd
	 */
	@Test
	public void testAjouterCargaison() {
		
		//Initialisation de la dao
		CargaisonDAOImpl cargaisondao = new CargaisonDAOImpl();
		
		//Récupération de la liste des cargaison avant ajout de la nouvelle cargaison
		List<Cargaison> listeCargaisons =cargaisondao.getAll();
		
		//Création de la cargaison à ajouter (sans l'id puisqu'il est auto-incrementé)
		Cargaison cargaison = new Cargaison("testAjout", 100, "01/02/2020");
		
		//Ajout de la nouvelle cargaison à la bdd
		cargaisondao.ajouter(cargaison);
		
		//Calcule de l'id de la nouvelle cargaison (=nb de cargaison déjà dans la bdd + 1) et attribution de l'id à l'objet cargaison
		Long idNouvelleCargaison = (long) (listeCargaisons.size()+1);
		cargaison.setIdCargaison(idNouvelleCargaison);
		
		//Mise à jour de la liste des cargaisons dans la bdd
		listeCargaisons =cargaisondao.getAll();
		
		//On test si le nouvel objet cargaison est présent dans la liste
		assertTrue("Est ce que l'ajout a été fait",listeCargaisons.contains(cargaison));

	}//end testAjouterCargaison

	
	
	//======================================================================//
	//================ testModifierCargaison ===============================//
	//======================================================================//
	
	
	/**
	 * Test la modification d'une cargaison dans la bdd
	 */
	@Test
	public void testModifierCargaison() {
		
		//Initialisation de la dao
		CargaisonDAOImpl cargaisondao = new CargaisonDAOImpl();
		
		//Ajout d'une nouvelle cargaison dans la bdd
		Cargaison cargaison = new Cargaison("testModif", 100, "01/02/2020");
		cargaisondao.ajouter(cargaison);
		
		//Récupération de la liste des cargaison après ajout mais avant modification de la cargaison
		List<Cargaison> listeCargaisons =cargaisondao.getAll();
		
		//Calcule de l'id de la derniere cargaison ajoutée (=nb de cargaison dans la bdd) et attribution de l'id à l'objet cargaison
		Long idNouvelleCargaison = (long) (listeCargaisons.size());
		cargaison.setIdCargaison(idNouvelleCargaison);
		
		//Creation de l'objet cargaisonModif qui est une modification de l'objet cargaison
		Cargaison cargaisonModif=cargaison;
		cargaisonModif.setReference("testModifReussit");

		//Mise à jour de la liste des cargaisons dans la bdd
		listeCargaisons =cargaisondao.getAll();
		
		//On test si l'objet cargaisonModif est présent dans la liste
		assertTrue("Est ce que la modification a été faite",listeCargaisons.contains(cargaisonModif));
		
	}//end tesModifierCargaison

	
	
	//======================================================================//
	//================ testSupprimerCargaison ==============================//
	//======================================================================//
	
	
	@Test
	public void testSupprimerCargaison() {
		//Initialisation de la dao
		CargaisonDAOImpl cargaisondao = new CargaisonDAOImpl();
		
		//Ajout d'une nouvelle cargaison dans la bdd
		Cargaison cargaison = new Cargaison("testSuppr", 100, "01/02/2020");
		cargaisondao.ajouter(cargaison);
		
		//Récupération de la liste des cargaison après ajout mais avant suppression de la cargaison
		List<Cargaison> listeCargaisons =cargaisondao.getAll();
		
		//Calcule de l'id de la nouvelle cargaison (=nb de cargaison déjà dans la bdd + 1) et attribution de l'id à l'objet cargaison
		Long idNouvelleCargaison = (long) (listeCargaisons.size()+1);
		cargaison.setIdCargaison(idNouvelleCargaison);
		
		System.out.println("\n\n"+listeCargaisons+"\n\n");
		System.out.println(cargaison);
		//Suppression de la cargaison précédemment ajouté 
		cargaisondao.supprimer(idNouvelleCargaison);

		//Mise à jour de la liste des cargaisons dans la bdd
		listeCargaisons =cargaisondao.getAll();
		
		//On test si le nouvel objet cargaison est présent dans la liste => on attend false puisqu'on l'a supprimer
		assertFalse("Est ce que la suppression a été faite",listeCargaisons.contains(cargaison));
	}//end testSupprimerCargaison

	
	
	//======================================================================//
	//================ testGetByIdCargaison ================================//
	//======================================================================//
	
	
	@Test
	public void testGetByIdCargaison() {
		
		//Initialisation de la dao
		CargaisonDAOImpl cargaisondao = new CargaisonDAOImpl();

		//Ajout d'une nouvelle cargaison dans la bdd
		Cargaison cargaisonGetByID = new Cargaison("testGetByID", 100, "01/02/2020");
		cargaisondao.ajouter(cargaisonGetByID);
		
		//Récupération de la liste des cargaison après ajout mais avant suppression de la cargaison
		List<Cargaison> listeCargaisons =cargaisondao.getAll();
		
		//Calcule de l'id de la nouvelle cargaison (=nb de cargaison déjà dans la bdd + 1) et attribution de l'id à l'objet cargaison
		Long idNouvelleCargaison = (long) (listeCargaisons.size());
		cargaisonGetByID.setIdCargaison(idNouvelleCargaison);
		
		//Récupération de la cargaison pr&écédemment ajoutée
		Cargaison cargaisonRecup = cargaisondao.getById(idNouvelleCargaison);

		//On test si la cargaison récupérée est égale à la cargaison ajoutée
		assertTrue("Est ce que la récupération a marché", cargaisonGetByID.equals(cargaisonRecup));
	}//end testGetByIdCargaison

	
	//======================================================================//
	//================ testGetAllCargaison ================================//
	//======================================================================//
	
	@Test
	public void testGetAllCargaison() {
		//Ce test est effectué en 2e. A ce stade on a ajouté 1 cargaison.
		
		//Initialisation de la dao
		CargaisonDAOImpl cargaisondao = new CargaisonDAOImpl();
		
		//Recup de la liste 
		List<Cargaison> listeCargaisons=cargaisondao.getAll();

		//Test si la liste fait bien la bonne longueur
		assertTrue("Est ce que la récupération a marché", listeCargaisons.size()==1);

	}//end testGetAllCargaison
	
	@Test
	public void testGetMarchandise() {
		//Initialisation de la dao
		CargaisonDAOImpl cargaisondao = new CargaisonDAOImpl();
		MarchandiseDAOImpl marchandisedao = new MarchandiseDAOImpl();
		
		//Ajout d'une cargaison avec des marchandises
		Cargaison cargaison = new Cargaison("testGetMarchandise", 100, "01/02/2020");
		Marchandise marchandise1=new Marchandise("marchandise1", 50, 20, 30);
		Marchandise marchandise2=new Marchandise("marchandise2", 50, 20, 30);
		
		List<Marchandise> listeMarchandisesInitiale= new ArrayList<Marchandise>();
		listeMarchandisesInitiale.add(marchandise1);
		listeMarchandisesInitiale.add(marchandise2);
		
		// Attribution de la cargaison à chaque marchandise (relation manyToOne)
		marchandise1.setCargaison(cargaison);
		marchandise2.setCargaison(cargaison);
		
		//Ajout des marchandises et automatiquement de la cargaison
		marchandisedao.ajouter(marchandise1);
		marchandisedao.ajouter(marchandise2);
		
		
		//Recup de l'id de la cargaison
		Long idNouvelleCargaison = (long) (cargaisondao.getAll().size());
		cargaison.setIdCargaison(idNouvelleCargaison);
		
	
		
		//Recup de la liste des marchandises de la cargaison ajoutée
		
		List<Marchandise> listeMarchandisesRecup = cargaisondao.getMarchandise(idNouvelleCargaison);

		
		//Test si la liste récupérée est bien la meme que la liste initiale
		assertTrue("Est ce que la récupération a marchée", listeMarchandisesInitiale.equals(listeMarchandisesRecup));
	}//end testGetMarchandise
	
	
	//======================================================================//
	//================ getVolumeTotal ======================================//
	//======================================================================//
	
	@Test
	public void getVolumeTotal() {
		//Initialisation de la dao
		CargaisonDAOImpl cargaisondao = new CargaisonDAOImpl();
		MarchandiseDAOImpl marchandisedao = new MarchandiseDAOImpl();
		
		//Ajout d'une cargaison avec des marchandises
		Cargaison cargaison = new Cargaison("testGetVolumeTotal", 100, "01/02/2020");
		Marchandise marchandise1=new Marchandise("marchandise1", 50, 20, 30);
		Marchandise marchandise2=new Marchandise("marchandise2", 50, 20, 30);
		
		List<Marchandise> listeMarchandisesInitiale= new ArrayList<Marchandise>();
		listeMarchandisesInitiale.add(marchandise1);
		listeMarchandisesInitiale.add(marchandise2);
		
		// Attribution de la cargaison à chaque marchandise (relation manyToOne)
		marchandise1.setCargaison(cargaison);
		marchandise2.setCargaison(cargaison);
		
		//Ajout des marchandises et automatiquement de la cargaison
		marchandisedao.ajouter(marchandise1);
		marchandisedao.ajouter(marchandise2);
		
		
		//Recup de l'id de la cargaison
		Long idNouvelleCargaison = (long) (cargaisondao.getAll().size());
		cargaison.setIdCargaison(idNouvelleCargaison);
		
		
		//calcule du volume total attendu
		double volumeAttendu= marchandise1.getVolume()+marchandise2.getVolume();
		
		//Recup du poid par la fonction getVolumeTotal
		double volumerecupere= cargaisondao.getVolumeTotal(idNouvelleCargaison);
		
		//Test si le volume est bien le meme que le volume attendu
		assertTrue("Est ce que le calcul du volume total a marché ?", volumeAttendu==volumerecupere);
	}//end getVolumeTotal
	
	@Test
	public void getPoidsTotal() {
		//Initialisation de la dao
		CargaisonDAOImpl cargaisondao = new CargaisonDAOImpl();
		MarchandiseDAOImpl marchandisedao = new MarchandiseDAOImpl();
		
		//Ajout d'une cargaison avec des marchandises
		Cargaison cargaison = new Cargaison("testGetPoidsTotal", 100, "01/02/2020");
		Marchandise marchandise1=new Marchandise("marchandise1", 50, 20, 30);
		Marchandise marchandise2=new Marchandise("marchandise2", 50, 20, 30);
		
		List<Marchandise> listeMarchandisesInitiale= new ArrayList<Marchandise>();
		listeMarchandisesInitiale.add(marchandise1);
		listeMarchandisesInitiale.add(marchandise2);
		
		// Attribution de la cargaison à chaque marchandise (relation manyToOne)
		marchandise1.setCargaison(cargaison);
		marchandise2.setCargaison(cargaison);
		
		//Ajout des marchandises et automatiquement de la cargaison
		marchandisedao.ajouter(marchandise1);
		marchandisedao.ajouter(marchandise2);
		
		
		//Recup de l'id de la cargaison
		Long idNouvelleCargaison = (long) (cargaisondao.getAll().size());
		cargaison.setIdCargaison(idNouvelleCargaison);
		
		
		//calcule du volume total attendu
		double poidsAttendu= marchandise1.getPoids()+marchandise2.getPoids();
		
		//Recup du poid par la fonction getVolumeTotal
		double poidsrecupere= cargaisondao.getPoidsTotal(idNouvelleCargaison);
		
		//Test si le volume est bien le meme que le volume attendu
		assertTrue("Est ce que le calcul du poids total a marché ?", poidsrecupere==poidsAttendu);
	}//end getPoidsTotal
	

}//end class
