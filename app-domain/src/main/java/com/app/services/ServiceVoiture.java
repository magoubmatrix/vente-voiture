package com.app.services;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.app.entity.Voiture;


public interface ServiceVoiture {

	public void ajoutVoiture(Voiture voiture);
	
	

	
	 
	/*
	 * @Query("select count(v) from Voiture v where v.marque = ?1") public int
	 * nombreDesVoitureParMarque( String marque);
	 */
	
	public long countAll();
	
	
	// other type of code 
	
	public List<String> findAllMarque();
	
	public List<String> findModeleByMarque(String marque);

	public List<Long> findAllImmatricul();
	
	
	public void deleteVoiture(Long id);
	
	public void editVoiture(Voiture voiture , Long id);
}
