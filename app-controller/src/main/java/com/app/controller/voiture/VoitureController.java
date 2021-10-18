package com.app.controller.voiture;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.entity.AppUser;
import com.app.entity.PhotoPrincipale;
import com.app.entity.Voiture;
import com.app.entity.VoiturePhoto;

import com.app.repository.PhotoPrincipaleRepository;
import com.app.repository.UserRepository;
import com.app.repository.VoiturePhotoRepository;
import com.app.repository.VoitureRepository;

import com.app.services.ServicePhotoVoiture;
import com.app.services.ServiceVoiture;



@RestController
@RequestMapping("/voiture")
//@CrossOrigin("http://localhost:4200")
public class VoitureController {

	@Autowired
	private VoiturePhotoRepository repoPhoto;
	@Autowired
	private ServiceVoiture serviceVoiture;
	@Autowired
	private VoitureRepository repo;
	@Autowired
	private ServicePhotoVoiture  servicePhoto;
	@Autowired
    private UserRepository repoUser;
	@Autowired
    private PhotoPrincipaleRepository repoPhotoPrincipale;
	
	//@PreAuthorize("hasRole('ROLE_USER')")
	@PostMapping("/ajoutVoiture")
	public String ajoutVoiture(@RequestBody Voiture voiture ) {
		 String username = SecurityContextHolder.getContext().getAuthentication().getName();
		   AppUser user = repoUser.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("username invalid"));
		   voiture.setUser(user);
		serviceVoiture.ajoutVoiture(voiture);
	  	   return String.format("la voiture est ajouté par le user %s", username);
	}
	
	@PostMapping("/ajoutPhoto")
	public String ajoutPhotoVoiture(@RequestParam("file") MultipartFile file) {
		 String username = SecurityContextHolder.getContext().getAuthentication().getName();
		   AppUser user = repoUser.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("username invalid"));
		   List<Voiture> voitures = user.getVoiture();
		   int i = 0;
		   long[] tab = new long[voitures.size()];
		 for(Voiture v : voitures) {
			 tab[i] = v.getId();
			 i++;
		 }
		 
		 Long id = numberMax(tab);
		 Voiture voiture = repo.findById(id).orElseThrow(()-> new RuntimeException("la voiture est introuvable"));
		 String marque = voiture.getMarque();
		 
		 VoiturePhoto photo = new VoiturePhoto();
		     photo.setVoiture(voiture);
		     photo.setNom(file.getOriginalFilename());
		     photo.setDataType(file.getContentType());
		    try {
				photo.setData(file.getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		    repoPhoto.save(photo);
	
		
		return String.format("la photo du %s est ajoutée avec succés", marque);
	}
	
	
	@PostMapping("/ajoutPhotoPrincipale")
	public String ajoutPhotoPricipalVoiture(@RequestParam("file") MultipartFile file) {
		 String username = SecurityContextHolder.getContext().getAuthentication().getName();
		   AppUser user = repoUser.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("username invalid"));
		   List<Voiture> voitures = user.getVoiture();
		   int i = 0;
		   long[] tab = new long[voitures.size()];
		 for(Voiture v : voitures) {
			 tab[i] = v.getId();
			 i++;
		 }
		 
		 Long id = numberMax(tab);
		 Voiture voiture = repo.findById(id).orElseThrow(()-> new RuntimeException("la voiture est introuvable"));
		 String marque = voiture.getMarque();
		 
		PhotoPrincipale photo = new PhotoPrincipale();
		     photo.setVoiture(voiture);
		     photo.setNom(file.getOriginalFilename());
		     photo.setDataType(file.getContentType());
		    try {
				photo.setData(file.getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		    repoPhotoPrincipale.save(photo);
	
		
		return String.format("la photo principale du %s est ajoutée avec succés", marque);
	}
	
	
	private Long numberMax(long[] tab) {
		Long max = 0L;
		for(int i = 0 ; i < tab.length;i++) {
		if(max < tab[i])
			max = tab[i];
		}
		return max;
	}


	/*
	@GetMapping("/photos/{id}")
	public ResponseEntity<List<ByteArrayResource>> getPhotosOfVoiture(@PathVariable Long id){
		Voiture voiture = repo.findById(id).get();
		List<VoiturePhoto> photos = voiture.getPhotos();
		List<String> mediaTypes = photos.stream().map(p -> p.getDataType()).collect(Collectors.toList());
		HttpHeaders headers = new HttpHeaders();
		photos.forEach(p -> headers.add("accepted", p.getNom()));
		List<ByteArrayResource> resource = java.util.Arrays.asList();
		photos.forEach(p -> resource.add(new ByteArrayResource(p.getData())));
		return ResponseEntity.ok().contentType((MediaType) MediaType.parseMediaTypes(mediaTypes)).headers(headers)
		.body(resource);
	
	} 
	
		*/
	
	@GetMapping("/photo/{id}")
	public ResponseEntity<Resource>  getphotoById( @PathVariable Long id) {
		VoiturePhoto photo = repoPhoto.getOne(id);
		HttpHeaders headers = new HttpHeaders();
		headers.add("accept", photo.getNom());
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(photo.getDataType()))
				.headers(headers).body(new ByteArrayResource(photo.getData())) ;
	}
	
	
	
	
	@GetMapping("/idOfPhotoByVoiture/{id}")
	public List<Long>  listOfIdOfPhotosOfVoiture(@PathVariable Long id){
		Voiture voiture = repo.getOne(id);
		List<Long> listOfId = new ArrayList<Long>();
		voiture.getPhotos().forEach(vp -> listOfId.add(vp.getId()));
		return listOfId;
	} 
	
	public Long idOfPhotoPrincipaleOfVoiture(Long id) {
		Voiture voiture = repo.getOne(id);
		return voiture.getPhotoPrincipale().getId();
	}
	
	// get voitures en utilisant criteria builder
	
	
	
	
	
	
	
	// nombre des voitures par catacterestiques
	
	
	@GetMapping("/nbrToutVoiture")
	public long countAll() {
		return serviceVoiture.countAll();
	}

	
	
	@GetMapping("/marques")
	public List<String> findAllMarque(){
		return serviceVoiture.findAllMarque();
	}
	
	@GetMapping("/modelesByMarque")
	public List<String> findModelesByMarque( @RequestParam String marque){
		return serviceVoiture.findModeleByMarque(marque);
	}
	
	@GetMapping("/immatricul")
	public List<Long> findAllImmatricul(){
		
		return  serviceVoiture.findAllImmatricul();
		
	}
	
	
	
	

	
	
	
	
	
	// suppression voiture par id 
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/deleteVoiture/{id}")
	public String deleteVoiture(@PathVariable Long id) {
		Voiture voiture = repo.getOne(id);
		String marque = voiture.getMarque();
		String modele = voiture.getModele();
		String user = voiture.getUser().getUsername();
		serviceVoiture.deleteVoiture(id);
		return String.format("la voiture de marque %s et du modele %s ajouter par le user %s a été supprimé", marque,modele,user);
	}
	@PreAuthorize("hasRole('ROLE_USER')")
	@DeleteMapping("/deletePhoto/{id}")
	public String deleltePhoto(@PathVariable Long id) {
		VoiturePhoto photo = repoPhoto.getOne(id);
		String marque = photo.getVoiture().getMarque();
		String modele = photo.getVoiture().getModele();
		repoPhoto.deleteById(id);
		return String.format("la photo de la voiture du marque %s et modele %s a été supprimé avec succés", marque,modele);
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@PutMapping("/editVoiture/{id}")
	public String editVoiture(@RequestBody Voiture voiture,@PathVariable Long id) {
		serviceVoiture.editVoiture(voiture, id);
		return "l'édition est effectué avec succés";
	}
	
}
