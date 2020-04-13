package com.intiformation.gestiontransport.testUnitaire;

//Ajout d'un import static de la classe Assert:
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.intiformation.gestiontransport.dao.interfaces.IMarchandiseDAO;
import com.intiformation.gestiontransport.entity.Marchandise;



/**
 * Classe de test pour la classe marchandise.
 * 
 * @author Marie
 *
 */
 public class MarchandiseDAOImplTest {

		//Déclaration d'une variable de type de la classe à tester : Marchandise et de quelques propriétés
	private Marchandise marchandise;
	IMarchandiseDAO marchandiseDAO;
	List<Marchandise> listeMarchandiseBDD;

	@Test
	public void  testgetAllMarchandisesBdd() {

		System.out.println("\t\t Cas de test pour tester la méthode 'getAllMarchandisesBdd'.");
		
		// Méthode d'assertion pour tester la méthode getAllMarchandisesBdd : asser***
		
		listeMarchandiseBDD = marchandiseDAO.getAll();
		
		assertTrue("Est-ce que la liste de marchandise est bien affichée ?", marchandise.getAll(listeMarchandiseBDD));
		
		

	}//end testgetAllMarchandisesBdd
	
	
}// end classe
