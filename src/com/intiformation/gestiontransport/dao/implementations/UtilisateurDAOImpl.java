package com.intiformation.gestiontransport.dao.implementations;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.intiformation.gestiontransport.dao.interfaces.IUtilisateurDAO;
import com.intiformation.gestiontransport.entity.Marchandise;
import com.intiformation.gestiontransport.entity.Utilisateur;
import com.intiformation.gestiontransport.tool.JPAUtil;

public class UtilisateurDAOImpl extends JpaDAO<Utilisateur> implements IUtilisateurDAO {

	public UtilisateurDAOImpl() {
		super(Utilisateur.class);
		// TODO Auto-generated constructor stub
	}

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
