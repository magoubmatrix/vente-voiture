package com.app.service.voiture;

import java.util.ArrayList;
import java.util.Calendar;

import java.util.List;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import com.app.entity.AppUser;
import com.app.entity.Voiture;

import com.app.repository.UserRepository;
import com.app.repository.VoitureRepository;
import com.app.services.ServiceVoiture;


@Service
public class ServiceVoitureImpl implements ServiceVoiture {
	
	@Autowired
	 VoitureRepository repo ;
	@Autowired
	UserRepository repoUser;
	
	

	@Override
	public void ajoutVoiture(Voiture voiture) {
		if(voiture.getImmatriculation() != null)
		voiture.setAnneeImmatricul(voiture.getImmatriculation().get(Calendar.YEAR));
	   repo.save(voiture);
	}
	
	

	// les methodes qui return les nombres des voitures
	
	/*
	
	@Override
	public long countByMarque(String marque) {
		
		return repo.countByMarque(marque);
	}

	@Override
	public long countByMarqueAndModele(String marque, String modele) {
		
		return repo.countByMarqueAndModele(marque, modele);
	}

	@Override
	public long countByMarqueAndImmatriculation(String marque, int immatriculation) {
	     List<Voiture> voitures = repo.findAll();
	  List<Voiture> result =    voitures.stream()
	     .filter(v -> v.getMarque().equals(marque))
	     .filter(v -> v.getImmatriculation().get(Calendar.YEAR)==immatriculation)
	     .collect(Collectors.toList());
		return result.size();
	}

	@Override
	public long countByMarqueAndPrixmax(String marque, double prixmax) {
		
		return repo.countByMarqueAndPrixmax(marque, prixmax);
	}

	@Override
	public long countByMarqueAndModeleAndImmatriculation(String marque, String modele, int immatriculation) {
		
		 List<Voiture> voitures = repo.findAll();
		  List<Voiture> result =    voitures.stream()
		     .filter(v -> v.getMarque().equals(marque))
		     .filter(v -> v.getModele().equals(modele))
		     .filter(v -> v.getImmatriculation().get(Calendar.YEAR)==immatriculation)
		     .collect(Collectors.toList());
			return result.size();
		//return repo.countByMarqueAndModeleAndImmatriculation(marque, modele, immatriculation);
	}

	@Override
	public long countBymarqueAndImmatriculationAndPrixmax(String marque, int immatriculation, double prixmax) {
		 
		List<Voiture> voitures = repo.findAll();
		  List<Voiture> result =    voitures.stream()
		     .filter(v -> v.getMarque().equals(marque))
		     .filter(v -> v.getImmatriculation().get(Calendar.YEAR)==immatriculation)
		     .filter(v -> v.getPrix()<prixmax)
		     .collect(Collectors.toList());
			return result.size();
		
		//return repo.countBymarqueAndImmatriculationAndPrixmax(marque, immatriculation, prixmax);
	}

	@Override
	public long countByAll(String marque, String modele, int immatriculation, double prixmax) {
		List<Voiture> voitures = repo.findAll();
		  List<Voiture> result =    voitures.stream()
		     .filter(v -> v.getMarque().equals(marque))
		     .filter(v -> v.getModele().equals(modele))
		     .filter(v -> v.getImmatriculation().get(Calendar.YEAR)==immatriculation)
		     .filter(v -> v.getPrix()<prixmax)
		     .collect(Collectors.toList());
			return result.size();
		//return repo.countByAll(marque, modele, immatriculation, prixmax);
	}

	@Override
	public long countByimmatriculationAndPrix(int immatriculation, double prix) {
		List<Voiture> voitures = repo.findAll();
		  List<Voiture> result =    voitures.stream()
		     .filter(v -> v.getImmatriculation().get(Calendar.YEAR)==immatriculation)
		     .filter(v -> v.getPrix()<prix)
		     .collect(Collectors.toList());
			return result.size();
		
		//return repo.countByimmatriculationAndPrix(immatriculation, prix);
	}

	@Override
	public long countByImmatriculation(int immatriculation) {
		List<Voiture> voitures = repo.findAll();
		  List<Voiture> result =    voitures.stream()
		     .filter(v -> v.getImmatriculation().get(Calendar.YEAR)==immatriculation)
		     .collect(Collectors.toList());
			return result.size();
		//return repo.countByImmatriculation(immatriculation);
	}

	@Override
	public long countByPrixmax(double prixmax) {
		
		return repo.countByPrixmax(prixmax);
	}
	
	@Override
	public long countByMarqueAndModeleAndPrix(String marque , String modele,double prixmax) {
		List<Voiture> voitures = repo.findAll();
		  List<Voiture> result =    voitures.stream()
		     .filter(v -> v.getMarque().equals(marque))
		     .filter(v -> v.getModele().equals(modele))
		     .filter(v -> v.getPrix()<prixmax)
		     .collect(Collectors.toList());
			return result.size();
	}
	
	
	*/
	
	
	
	// methode qui retourne toutes les marques

	@Override
	public List<String> findAllMarque() {

		return repo.findAllMarque();
	}

	// methode qui retourne les modeles selon la marque indiqués comme parametre
	
	@Override
	public List<String> findModeleByMarque(String marque) {
		
		return repo.findModeleByMarque(marque);
	}

	// la methode qui retourne tout les immarticules
	
	@Override
	public List<Long> findAllImmatricul() {
		List <Voiture> voitures = repo.findAll();
		List<Calendar> calendar =  new ArrayList<Calendar>();
		voitures.forEach(v -> calendar.add(v.getImmatriculation()));
		List<Long> immatricul = new ArrayList<Long>();
		immatricul =  calendar.stream().map( c -> (long)c.get(Calendar.YEAR))
				.distinct().sorted().collect(Collectors.toList());
				immatricul.forEach(System.out::println);
		
		return immatricul ;
	}

	
	// methode qui retourne le nombres tolat des voitures
	
	@Override
	public long countAll() {
		
		return repo.countAll();
	}


	 
	 
	 @Override
	 public void editVoiture(Voiture voiture,Long id) {
		 String username = SecurityContextHolder.getContext().getAuthentication().getName();
		 AppUser user = repoUser.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("username not found"));
		 List<Voiture> voitures = user.getVoiture();
		 Voiture voitureEdit = repo.getOne(id);
		 if(voitures.contains(voitureEdit)) {
			 voiture.setId(id);
			 voitureEdit = voiture;
			 repo.save(voitureEdit);
		 }
		
		 else {
			 throw new RuntimeException("desolé vous n'etes pas authorisé d'éditer cette voiture");
		 }
		  
	 }

	
	 @Override
	 public void deleteVoiture(Long id) {
		  repo.deleteById(id);
	 }

	
	
	



	
}
