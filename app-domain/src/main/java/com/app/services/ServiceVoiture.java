package com.app.services;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.app.entity.Voiture;
import com.app.entity.VoitureRecherche;

public interface ServiceVoiture {

	public void ajoutVoiture(Voiture voiture);
	
	public Page<Voiture> findAll(int page , int size);
	
	public Page<Voiture> findByAll( String marque, String modele, int immatriculation,double prix,int page , int size);
	
	public Page<Voiture>findByMarque(String marque ,int page , int size);
	
	public Page<Voiture> findByImmatriculation(String immatriculation ,int page , int size);
	
	public Page<Voiture> findByPrixMax(double prixmax ,int page , int size);
	
	public Page<Voiture>findByMarqueAndModele(String marque,String modele ,int page , int size);
	
	public Page<Voiture> findByMarqueAndImmatriculation(String marque,int immatriculation ,int page , int size);
	
	public Page<Voiture>  findByMarqueAndPrix(String marque, double prixmax ,int page , int size);
	
	public Page<Voiture> findByMarqueAndModeleAndImmatriculation(String marque,String modele,int immatriculation ,int page , int size);

	public Page<Voiture> findByMarqueAndModeleAndPrixMax(String marque,String modele, double prixMax ,int page , int size);
	
	public Page<Voiture> findByMarqueAndImmatriculationAndPrixMax(String marque,int immatriculation,double prix,int page , int size); 
	
	public Page<Voiture> findByImmatriculationAndPrixMax( int immatriculation, double prix ,int page , int size);
	
	
	
	 
	/*
	 * @Query("select count(v) from Voiture v where v.marque = ?1") public int
	 * nombreDesVoitureParMarque( String marque);
	 */
	
	public long countAll();
	
	public long countByMarque(String marque);
	
	public long countByMarqueAndModele(String marque, String modele);
	
	public long countByMarqueAndImmatriculation(String marque,int immatriculation);
	
	
	public long countByMarqueAndPrixmax(String marque,double prixmax);
	
	public long countByMarqueAndModeleAndImmatriculation(String marque , String modele , int immatriculation);
	
	
	public long countBymarqueAndImmatriculationAndPrixmax(String marque, int immatriculation , double prixmax);
	

	public long countByAll(String marque , String modele, int immatriculation , double prixmax );
	
	public long countByMarqueAndModeleAndPrix(String marque , String modele,double prixmax);
	
	public long countByimmatriculationAndPrix(int immatriculation , double prix);
	
	public long countByImmatriculation(int immatriculation);
	

	public long countByPrixmax(double prixmax);
	
	// other type of code 
	
	public List<String> findAllMarque();
	
	public List<String> findModeleByMarque(String marque);

	public List<Long> findAllImmatricul();
	
	// la methode qui retourne la liste des voitures de recherche
	
	public List<VoitureRecherche> mapToVoitureRecheche();
	public void deleteVoiture(Long id);
	
	public void editVoiture(Voiture voiture , Long id);
}
