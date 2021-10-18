package com.app.service.voiture;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.entity.AppUser;
import com.app.entity.Voiture;
import com.app.entity.VoitureRecherche;
import com.app.repository.UserRepository;
import com.app.repository.VoitureRepository;
import com.app.services.ServiceVoiture;

import io.jsonwebtoken.lang.Arrays;

@Service
public class ServiceVoitureImpl implements ServiceVoiture {
	
	@Autowired
	 VoitureRepository repo ;
	@Autowired
	UserRepository repoUser;
	
	

	@Override
	public void ajoutVoiture(Voiture voiture) {
	   repo.save(voiture);
	}
	
	@Override
	public Page<Voiture> findAll(int page , int size) {
		Pageable pageRequest = PageRequest.of(page, size);
		System.out.println(pageRequest.getOffset());
		System.out.println(pageRequest.getPageSize());
		System.out.println(pageRequest.getPageNumber());
		return repo.findAll(pageRequest);
	}
	

	@Override
	public Page<Voiture> findByAll(String marque, String modele, int immatriculation,double prix, int page , int size) {
		List<Voiture> voitures = repo.findAll();
		List<Voiture> result =   voitures.stream()
		     .filter(v -> v.getMarque().equals(marque))
		     .filter(v -> v.getModele().equals(modele))
		     .filter(v -> v.getImmatriculation().get(Calendar.YEAR)==immatriculation)
		     .filter(v -> v.getPrix()<prix)
		     .collect(Collectors.toList());
		 PageRequest pageable = PageRequest.of(page, size);
		 
		return toPage(result,pageable);
	}
	
	

	@Override
	public Page<Voiture> findByMarqueAndModeleAndPrixMax(String marque, String modele, double prixMax , int page , int size  ) {
		 PageRequest pageable = PageRequest.of(page, size);
		return repo.findByMarqueAndModeleAndPrixMax(marque, modele, prixMax,pageable);
	}

	@Override
	public Page<Voiture> findByMarque(String marque  , int page , int size ) {
		 PageRequest pageable = PageRequest.of(page, size);
		return repo.findByMarque(marque,pageable);
	}


	@Override
	public Page<Voiture> findByMarqueAndImmatriculation(String marque, int immatriculation , int page , int size) {
		List <Voiture> voitures = repo.findAll();
		List<Voiture> result = voitures.stream().filter(v -> v.getImmatriculation().get(Calendar.YEAR) == immatriculation)
				.filter(v-> v.getMarque().equals(marque))
				.collect(Collectors.toList());
		 PageRequest pageable = PageRequest.of(page, size);
		 return toPage(result, pageable);
	}

	@Override
	public Page<Voiture> findByMarqueAndModele(String marque, String modele , int page , int size) {
		 PageRequest pageable = PageRequest.of(page, size);
		return repo.findByMarqueAndModele(marque, modele ,pageable);
	}

	@Override
	public Page<Voiture> findByMarqueAndModeleAndImmatriculation(String marque, String modele, int immatriculation , int page , int size) {
		List <Voiture> voitures = repo.findAll();
		List<Voiture> result = voitures.stream().filter(v -> v.getImmatriculation().get(Calendar.YEAR) == immatriculation)
				.filter(v-> v.getMarque().equals(marque)).filter(v -> v.getModele().equals(modele))
				.collect(Collectors.toList());
		 PageRequest pageable = PageRequest.of(page, size);
		 return toPage(result, pageable);
		//return repo.findByMarqueAndModeleAndImmatriculation(marque, modele, immatriculation);
	}

	@Override
	public Page<Voiture> findByMarqueAndImmatriculationAndPrixMax(String marque, int immatriculation,double prix  , int page , int size) {
		List <Voiture> voitures = repo.findAll();
		List<Voiture> result = voitures.stream().filter(v -> v.getImmatriculation().get(Calendar.YEAR) == immatriculation)
				.filter(v-> v.getMarque().equals(marque)).filter(v -> v.getPrix() <= prix)
				.collect(Collectors.toList());
		 PageRequest pageable = PageRequest.of(page, size);
		 return toPage(result, pageable);
	}

	@Override
	public Page<Voiture> findByImmatriculation(String immatriculation , int page , int size) {
		PageRequest pageable = PageRequest.of(page, size);
		return repo.findByImmatriculation(immatriculation,pageable);
	}

	@Override
	public Page<Voiture> findByImmatriculationAndPrixMax(int immatriculation, double prix , int page , int size) {
		List <Voiture> voitures = repo.findAll();
		List<Voiture> result = voitures.stream().filter(v -> v.getImmatriculation().get(Calendar.YEAR) == immatriculation)
				.filter(v -> v.getPrix() <= prix)
				.collect(Collectors.toList());
		 PageRequest pageable = PageRequest.of(page, size);
		 return toPage(result, pageable);
	}
	
	
	@Override
	public Page<Voiture> findByMarqueAndPrix(String marque, double prixmax, int page, int size) {
		 PageRequest pageable = PageRequest.of(page, size);
		return repo.findByMarqueAndPrix(marque, prixmax, pageable);
	}

	@Override
	public Page<Voiture> findByPrixMax(double prixmax , int page , int size) {
		 PageRequest pageable = PageRequest.of(page, size);
		return repo.findByPrixMax(prixmax,pageable);
	}
	
	
	
	
	private Page<Voiture> toPage(List<Voiture> list, PageRequest pageable) {
        int start =  (int) pageable.getOffset();
        System.out.println(start);
        int end = Math.min((start + pageable.getPageSize()), list.size());
        System.out.println(end);
        System.out.println(pageable.getPageSize());
        System.out.println(pageable.getPageNumber());
        if(start > list.size())
            return new PageImpl<>(new ArrayList<>(), pageable, list.size());
        return new PageImpl<>(list.subList(start, end), pageable, list.size());
       
    }

	
	// les methodes qui return les nombres des voitures
	
	
	
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

	@Override
	public List<String> findAllMarque() {

		return repo.findAllMarque();
	}

	@Override
	public List<String> findModeleByMarque(String marque) {
		
		return repo.findModeleByMarque(marque);
	}

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

	@Override
	public long countAll() {
		
		return repo.countAll();
	}


	
	// la methode qui retourne la liste des objets de recherche VoitureRecherche
	
	 @Override
	public List<VoitureRecherche> mapToVoitureRecheche(){
		List<Voiture> voitures = repo.findAll();
		List<VoitureRecherche> recherche = new ArrayList<VoitureRecherche>();
		voitures.forEach(v -> recherche.add(new VoitureRecherche(v.getMarque(),v.getModele(), v.getImmatriculation().get(Calendar.YEAR),v.getPrix())));
		return  recherche;
		
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
