package com.intiformation.gestiontransport.dao.interfaces;

import java.util.List;
/**
 * Interface générique de la DAO. Cette interface sera utilisée par les trois classes principales : Cargaison, Utilisateur et Marchandise
 * @author Marie
 *
 * @param <T>
 */
public interface IGenericDAO<T> {
		
		public void ajouter(T entity);

		public void modifier(T entity);

		public void supprimer(Long id);

		public T getById(Long id);

		public List<T> getAll();
		
	
}//end interface
