package com.intiformation.gestiontransport.dao.implementations;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.intiformation.gestiontransport.dao.interfaces.IUtilisateurDAO;
import com.intiformation.gestiontransport.entity.Marchandise;
import com.intiformation.gestiontransport.entity.Utilisateur;
import com.intiformation.gestiontransport.tool.JPAUtil;
/**
 * Classe concrete de la DAO pour l'utilisateur.
 * Hérite de la classe JpaDAO pour récupérer les méthodes du CRUD et implémente l'interface UtilisateurDAOImpl pour les méthodes spécifiques aux utilisateurs
 * @author Valentin
 *
 */
public class UtilisateurDAOImpl extends JpaDAO<Utilisateur> implements IUtilisateurDAO {

	//====== Constructeur =============
	/**
	 * Constructeur vide
	 */
	public UtilisateurDAOImpl() {
		super(Utilisateur.class);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Test si l'utilisateur est présent dans la base de données (et si il n'y en a qu'un)
	 * Utilisise la NamedQuery qui est définie sur la classe entity de l'Utilisateur
	 */
	@Override
	public boolean isUtilisateurExists(String pLogin, String pMotDePasse) {
		// TODO Auto-generated method stub
		try {

			// requete
			Query query = this.entityManager.createNamedQuery("Utilisateur.isExists");
			query.setParameter(1, pLogin);
			query.setParameter(2, pMotDePasse);

			// exec + récup du résultat de la requete
			Long isExistsVerif = (Long) query.getSingleResult();

			return (isExistsVerif == 1);

		} catch (PersistenceException e) {
			System.out.println("... Erreur lors de la vérif de l'existance de l'utilisateur ....");
			e.printStackTrace();
		}

		
		return false;
	}//end isUtilisateurExists

	
	/**
	 * recupere l'utilisateur par son mot de passe et id
	 */
	@Override
	public Utilisateur getUserByMailMDP(String pLogin, String pMotDePasse) {
		
		try {
			//1. recup de l'entity manager 
			EntityManager em=JPAUtil.getEntityManager();
			
			//3. Contenu de la requete JPAQ 
			
			String requeteSelect = "SELECT u FROM utilisateur u WHERE u.login= :pLogin AND u.motDePasse= :pMdp";
			
			//4. creation de la requete
			Query selectQuery =em.createQuery(requeteSelect);
			
			//passage de parametre
			selectQuery.setParameter("pLogin", pLogin);
			selectQuery.setParameter("pMdp", pMotDePasse);
			
			//5. exec +recup de resultat de la requete
			
			Utilisateur utilisateur=(Utilisateur) selectQuery.getSingleResult();

			
			return utilisateur;
			
		}catch (PersistenceException e){
			System.out.println("... Erreur lors de la recuperation de l'utilisateur ....");
			e.printStackTrace();
		}
		return null;
	}//end getUserByMailMDP

}//end class
