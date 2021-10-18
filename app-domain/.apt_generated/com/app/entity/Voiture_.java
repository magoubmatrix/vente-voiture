package com.app.entity;

import java.util.Calendar;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Voiture.class)
public abstract class Voiture_ {

	public static volatile SingularAttribute<Voiture, Integer> anneeImmatricul;
	public static volatile SingularAttribute<Voiture, Double> prix;
	public static volatile SingularAttribute<Voiture, String> couleur;
	public static volatile SingularAttribute<Voiture, String> carburant;
	public static volatile ListAttribute<Voiture, VoiturePhoto> photos;
	public static volatile SingularAttribute<Voiture, String> marque;
	public static volatile SingularAttribute<Voiture, String> transmission;
	public static volatile SingularAttribute<Voiture, String> modele;
	public static volatile SingularAttribute<Voiture, Long> id;
	public static volatile SingularAttribute<Voiture, PhotoPrincipale> photoPrincipale;
	public static volatile SingularAttribute<Voiture, AppUser> user;
	public static volatile SingularAttribute<Voiture, Calendar> immatriculation;
	public static volatile SingularAttribute<Voiture, Integer> portes;
	public static volatile SingularAttribute<Voiture, Integer> vitesse;

	public static final String ANNEE_IMMATRICUL = "anneeImmatricul";
	public static final String PRIX = "prix";
	public static final String COULEUR = "couleur";
	public static final String CARBURANT = "carburant";
	public static final String PHOTOS = "photos";
	public static final String MARQUE = "marque";
	public static final String TRANSMISSION = "transmission";
	public static final String MODELE = "modele";
	public static final String ID = "id";
	public static final String PHOTO_PRINCIPALE = "photoPrincipale";
	public static final String USER = "user";
	public static final String IMMATRICULATION = "immatriculation";
	public static final String PORTES = "portes";
	public static final String VITESSE = "vitesse";

}

