package com.intiformation.gestiontransport.dao.implementations;

import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.intiformation.gestiontransport.dao.interfaces.IUtilisateurDAO;
import com.intiformation.gestiontransport.entity.Utilisateur;

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
	}

}
