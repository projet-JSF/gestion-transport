package com.intiformation.gestiontransport.testUnitaire;

//Ajout d'un import static de la classe Assert:
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.intiformation.gestiontransport.dao.implementations.MarchandiseDAOImpl;
import com.intiformation.gestiontransport.entity.Marchandise;

/**
 * Classe de test pour la classe marchandise.
 * 
 * @author Marie
 *
 */
public class MarchandiseDAOImplTest {


	

	/*******************************************
	 * testsupprimerMarchandise
	 *************************************************************/

	@Test
	public void testsupprimerMarchandise() {

		// Initialisation de la Dao
		MarchandiseDAOImpl marchandiseDao = new MarchandiseDAOImpl();

		// Ajout d'une nouvelle marchandise dans la bdd
		Marchandise marchandise = new Marchandise("testSuppr", 200, 10, 250);
		marchandiseDao.ajouter(marchandise);

		// Récupération de la liste des marchandises après ajout mais avant suppression
		// de
		// la marchandise
		List<Marchandise> listeMarchandises = marchandiseDao.getAll();

		// Calcule de l'id de la nouvelle marchandise (=nb de marchandise déjà dans la
		// bdd +
		// 1) et attribution de l'id à l'objet marchandise
		Long idNouvelleMarchandise = (long) (listeMarchandises.size());
		marchandise.setIdMarchandise(idNouvelleMarchandise);

		System.out.println("\n\n" + listeMarchandises + "\n\n");
		System.out.println(marchandise);
		
		
		// Suppression de la marchandise précédemment ajouté
		marchandiseDao.supprimer(idNouvelleMarchandise);

		// Mise à jour de la liste des marchandises dans la bdd
		listeMarchandises = marchandiseDao.getAll();

		// On test si le nouvel objet marchandise est présent dans la liste => on attend
		// false puisqu'on l'a supprimer
		assertFalse("Est ce que la suppression a été faite", listeMarchandises.contains(marchandise));

	}// endtestsupprimerMarchandise

	/*******************************************
	 * testsgetMarchandiseById
	 *************************************************************/

	@Test
	public void testgetMarchandiseById() {

		// Initialisation de la Dao
		MarchandiseDAOImpl marchandiseDao = new MarchandiseDAOImpl();

		// Ajout d'une nouvelle marchandise dans la bdd
		Marchandise marchandiseGetByID = new Marchandise("testGetByID", 180, 5, 220);
		marchandiseDao.ajouter(marchandiseGetByID);

		System.out.println("marchandiseGetByID ="+marchandiseGetByID);
		// Récupération de la liste des marchnadises après ajout mais avant suppression
		// de la marchandise
		List<Marchandise> listeMarchandises = marchandiseDao.getAll();

		// Calcule de l'id de la nouvelle marchandise (=nb de marchandise déjà dans la
		// bdd) et attribution de l'id à l'objet marchandise
		Long idNouvelleMarchandise = (long) (listeMarchandises.size()+1);
		marchandiseGetByID.setIdMarchandise(idNouvelleMarchandise);
		
		
		
		// Récupération de la marchandise précédemment ajoutée
		Marchandise marchandiseRecup = marchandiseDao.getById(idNouvelleMarchandise);
		System.out.println("marchandiseRecup ="+marchandiseRecup);
		
		
		// On test si la marchandise récupérée est égale à la marchandise ajoutée
		assertTrue("Est ce que la récupération a marché", marchandiseGetByID.equals(marchandiseRecup));

	}// end testgetMarchandiseById

	/*******************************************
	 * testmodifierMarchandise
	 *************************************************************/

	@Test
	public void testmodifierMarchandise() {

		// Initialisation de la Dao
		MarchandiseDAOImpl marchandiseDao = new MarchandiseDAOImpl();

		// Ajout d'une nouvelle marchandise dans la bdd
		Marchandise marchandise = new Marchandise("testModif", 80, 20, 150);
		marchandiseDao.ajouter(marchandise);

		// Récupération de la liste des marchandises après ajout mais avant modification
		// de
		// la marchandise
		List<Marchandise> listeMarchandises = marchandiseDao.getAll();

		// Calcule de l'id de la derniere marchandise ajoutée (=nb de marchandises dans
		// la
		// bdd) et attribution de l'id à l'objet marchandise
		Long idNouvelleMarchandise = (long) (listeMarchandises.size()+1);
		marchandise.setIdMarchandise(idNouvelleMarchandise);

		// Creation de l'objet marchandiseModif qui est une modification de l'objet
		// marchandise
		Marchandise marchandiseModif = marchandise;
		marchandiseModif.setNom("testModifReussit");

		// Mise à jour de la liste des marchandises dans la bdd
		listeMarchandises = marchandiseDao.getAll();

		// On test si l'objet marchandiseModif est présent dans la liste
		assertTrue("Est ce que la modification a été faite", listeMarchandises.contains(marchandiseModif));

	}// end testmodifierMarchandise

	/*******************************************
	 * testajouterMarchandise
	 *************************************************************/

	@Test
	public void testajouterMarchandise() {

		// Initialisation de la DAO
		MarchandiseDAOImpl marchandiseDao = new MarchandiseDAOImpl();

		// Récupération de la liste des marchandises avant ajout
		List<Marchandise> listeMarchandises = marchandiseDao.getAll();

		// Création de la marchandise à ajouter (sans l'id puisqu'il est
		// auto-incrementé)
		Marchandise marchandise = new Marchandise("testAjout", 50, 15, 100);

		// Ajout de la nouvelle marchandise à la bdd
		marchandiseDao.ajouter(marchandise);

		// Calcule de l'id de la nouvelle marchandise (=nb de marchandise dans la bdd +
		// 1) attribution de l'id à l'objet marchandise
		Long idNouvelleMarchandise = (long) (listeMarchandises.size() + 1);
		marchandise.setIdMarchandise(idNouvelleMarchandise);

		// Mise à jour de la liste des cargaisons dans la bdd
		listeMarchandises = marchandiseDao.getAll();

		// On test si le nouvel objet cargaison est présent dans la liste
		assertTrue("Est ce que l'ajout a été fait", listeMarchandises.contains(marchandise));

	}// end testmodifierMarchandise
	
	/*******************************************
	 * testgetAllMarchandisesBdd
	 *************************************************************/
	@Test
	public void testgetAllMarchandisesBdd() {
		// Test fait en 2ème. Après l'ajout d'une marchandise.

		// Initialisation de la Dao
		MarchandiseDAOImpl marchandiseDao = new MarchandiseDAOImpl();

		// Recup de la liste
		List<Marchandise> ListeMarchandises = marchandiseDao.getAll();

		System.out.println("\n\n ////////////////////////// "+ListeMarchandises);
		// Test si la liste fait bien la bonne longueur
		assertTrue("Est ce que la récupération a marché", ListeMarchandises.size() == 3);

	}// end testgetAllMarchandisesBdd

}// end classe
