package com.intiformation.gestiontransport.testUnitaire;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.intiformation.gestiontransport.dao.implementations.CargaisonDAOImpl;
import com.intiformation.gestiontransport.dao.implementations.MarchandiseDAOImpl;
import com.intiformation.gestiontransport.entity.Cargaison;
import com.intiformation.gestiontransport.entity.Marchandise;

public class CargaisonDAOImplTest {
	
	@Test
	public void testAjouterCargaison() {
		CargaisonDAOImpl cargaisondao = new CargaisonDAOImpl();
		Cargaison cargaison = new Cargaison("test", 100, "01/02/2020");
		cargaisondao.ajouter(cargaison);
		cargaison.setIdCargaison(1L);
		List<Cargaison> listeCargaisons = cargaisondao.getAll();
		assertTrue("Est ce que l'ajout a été fait",listeCargaisons.contains(cargaison));
	}//end testAjouterCargaison

	@Test
	public void tesModifierCargaison() {
		
	}//end tesModifierCargaison

	@Test
	public void testSupprimerCargaison() {
		
	}//end testSupprimerCargaison

	@Test
	public void testGetByIdCargaison() {
		
	}//end testGetByIdCargaison

	@Test
	public void testGetAllCargaison() {
		
	}//end testGetAllCargaison
	
	@Test
	public void testGetMarchandise() {
		
	}//end testGetMarchandise
	
	@Test
	public void getVolumeTotal() {
		
	}//end getVolumeTotal
	
	@Test
	public void getPoidsTotal() {
		
	}//end getPoidsTotal
	

}//end class
