package com.intiformation.gestiontransport.dao.appTest;

import java.util.List;

import com.intiformation.gestiontransport.dao.implementations.CargaisonDAOImpl;
import com.intiformation.gestiontransport.dao.implementations.MarchandiseDAOImpl;
import com.intiformation.gestiontransport.entity.Cargaison;
import com.intiformation.gestiontransport.entity.CargaisonAerienne;
import com.intiformation.gestiontransport.entity.CargaisonRoutiere;
import com.intiformation.gestiontransport.entity.Marchandise;
/**
 * Classe de tests préliminaires pour verifier que la dao fonctionne
 * @author IN-MP-018
 *
 */
public class AppTest {

	public static void main(String[] args) {
		//============== Instanciation DAO ==============================//
		
		CargaisonDAOImpl cargaisondao = new CargaisonDAOImpl();
		MarchandiseDAOImpl marchandisedao= new MarchandiseDAOImpl();
		
		List<Marchandise> listeMarchandises1 = marchandisedao.getAll();
		
		System.out.println("\n\n"+listeMarchandises1);
		//============== Remplissage des tables ==========================// 
		
		// création des objets à ajouter à la bdd
		
		Marchandise marchandise1=new Marchandise("Nounours", 5, 20, 10);
		Marchandise marchandise2=new Marchandise("Bonbons", 10, 15, 15);

		Cargaison cargaison1=new CargaisonAerienne("aerienne1", 1000.0, "01/01/2020", 100.0);
		
		Marchandise marchandise3=new Marchandise("Fraises", 20, 10, 30);
		Marchandise marchandise4=new Marchandise("Framboises", 15, 10, 25);

		Cargaison cargaison2=new CargaisonRoutiere("routiere1", 100, "15/06/2020", 5);
		
		// Attribution des cargaisons à chaque marchendise (relation manyToOne)
		marchandise1.setCargaison(cargaison1);
		marchandise2.setCargaison(cargaison1);
		
		
		marchandise3.setCargaison(cargaison2);
		marchandise4.setCargaison(cargaison2);
		
		//Ajout des marchandises à la BDD
		//=> L'ajout des cargaison se fera automatiquement grace à la relation ManyToOne

		marchandisedao.ajouter(marchandise1);
		marchandisedao.ajouter(marchandise2);
		
		marchandisedao.ajouter(marchandise3);
		marchandisedao.ajouter(marchandise4);
		
		//=============== Recupération d'une marchandise =========//

		Marchandise marchandise1Recup = marchandisedao.getById(1L);
		
		System.out.println("\n\n"+marchandise1Recup.toString());
		
		
		//============== Récupération d'une cargaison ============//
		
		Cargaison cargaison1Recup = cargaisondao.getById(1L);
		
		System.out.println("\n\n"+cargaison1Recup.toString());
		

		
		//============= Recuperation de la liste des marchandises ====//
		
		List<Marchandise> listeMarchandises = marchandisedao.getAll();
		
		System.out.println("\n\n"+listeMarchandises);
		
		//============= Recuperation de la liste des cargaisons ====//
		
		List<Cargaison> listeCargaisons = cargaisondao.getAll();
		
		System.out.println("\n\n"+listeCargaisons);
		
		
		//============ Modification Marchandise ===========//
		
		Marchandise marchandise4Modif = marchandisedao.getById(4L);
		marchandise4Modif.setNom("Abricots");
		
		marchandisedao.modifier(marchandise4Modif);
		
		System.out.println("\n\n"+marchandisedao.getById(4L).toString());
		
		
		//============ Modification Cargaison ===========//
		
		Cargaison cargaison2Modif = cargaisondao.getById(2L);
		cargaison2Modif.setDateLivraison("22/12/2020");
		
		cargaisondao.modifier(cargaison2Modif);
		
		System.out.println(cargaisondao.getById(2L).toString());
		
//		//============= Suppression d'une marchandise ====//
		
		marchandisedao.supprimer(3L);
		
		marchandisedao.supprimer(4L);
		
		List<Marchandise> listeMarchandisesApresSuppr = marchandisedao.getAll();
		
		System.out.println("\n\n Apres suppression "+ listeMarchandisesApresSuppr);
		
//		//============ Suppression d'une cargaison ====//
//		
//		cargaisondao.supprimer(2L);
//		
//		List<Cargaison> listeCargaisonsApresSuppr = cargaisondao.getAll();
//		
//		System.out.println("\n\n Apres suppression "+listeCargaisonsApresSuppr);
		
		
		//============ Recup de la liste des marchandises d'une cargaison ==========//
		List<Marchandise> listeMarchandisesCargaison2 = cargaisondao.getMarchandise(1L);
		System.out.println("\n\n"+listeMarchandisesCargaison2);
		
		//============ Recup du poids total d'une cargaison =======================//
		
		double poidsCargaison1 = cargaisondao.getPoidsTotal(1L);
		
		System.out.println("\n\n Poids cargaison 1 ="+poidsCargaison1);
		
		//============ Recup du volume total d'une cargaison =======================//
		
		double volumeCargaison1 = cargaisondao.getVolumeTotal(1L);
		
		System.out.println("\n\n Volume cargaison 1 ="+volumeCargaison1);
		
		//============ Recup du cout total d'une cargaison =======================//
		
		double coutCargaison1 = cargaisondao.getCout(1L);
		
		System.out.println("\n\n cout cargaison 1 ="+coutCargaison1);
		
	}//end main

}//end class
