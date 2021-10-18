package com.app.entity;


import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity


public class Voiture {

	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	
	@ManyToOne
	@JoinColumn(name = "userId" , referencedColumnName = "id")
	private AppUser user;
	
	@Lob
	@OneToMany(mappedBy = "voiture")
	private List<VoiturePhoto> photos;
	
	@OneToOne
	private PhotoPrincipale photoPrincipale;

	private String marque;

	private String modele;

	
	private Calendar immatriculation ;
    
	private Integer anneeImmatricul  ;
	
	private String couleur;
	
	
	private int portes;
	
	
	private String transmission;
	
	private int vitesse;
	
	private String carburant;
	
	
	private double prix;
	
	
	
	public Voiture(String marque, String modele, Calendar immatriculation, double prix) {
		super();
		this.marque = marque;
		this.modele = modele;
		this.immatriculation = immatriculation;
		this.prix = prix;
	}

	

	public Voiture() {
		super();
	}



	public List<VoiturePhoto> getPhotos() {
		return photos;
	}


	@JsonIgnore
	public void setPhotos(List<VoiturePhoto> photos) {
		this.photos = photos;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	@JsonIgnore
	public AppUser getUser() {
		return user;
	}


	public void setUser(AppUser user) {
		this.user = user;
	}


	public String getMarque() {
		return marque;
	}


	public void setMarque(String marque) {
		this.marque = marque;
	}


	public String getModele() {
		return modele;
	}


	public void setModele(String modele) {
		this.modele = modele;
	}


	public Calendar getImmatriculation() {
		return immatriculation;
	}


	public void setImmatriculation(Calendar immatriculation) {
		this.immatriculation = immatriculation;
	}


	public String getCouleur() {
		return couleur;
	}


	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}


	public int getPortes() {
		return portes;
	}


	public void setPortes(int portes) {
		this.portes = portes;
	}


	public String getTransmission() {
		return transmission;
	}


	public void setTransmission(String transmission) {
		this.transmission = transmission;
	}


	public int getVitesse() {
		return vitesse;
	}


	public void setVitesse(int vitesse) {
		this.vitesse = vitesse;
	}


	public String getCarburant() {
		return carburant;
	}


	public void setCarburant(String carburant) {
		this.carburant = carburant;
	}


	public double getPrix() {
		return prix;
	}


	public void setPrix(double prix) {
		this.prix = prix;
	}


	public PhotoPrincipale getPhotoPrincipale() {
		return photoPrincipale;
	}

	@JsonIgnore
	public void setPhotoPrincipale(PhotoPrincipale photoPrincipale) {
		this.photoPrincipale = photoPrincipale;
	}

   
	
	public void setAnneeImmatricul(Integer anneeImmatricul) {
		this.anneeImmatricul = anneeImmatricul;
	}



	public Integer getAnneeImmatricul() {
		return  anneeImmatricul ;
	}


	
	
	

	
	
}
