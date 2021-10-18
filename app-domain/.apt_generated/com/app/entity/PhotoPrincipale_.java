package com.app.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PhotoPrincipale.class)
public abstract class PhotoPrincipale_ {

	public static volatile SingularAttribute<PhotoPrincipale, Voiture> voiture;
	public static volatile SingularAttribute<PhotoPrincipale, byte[]> data;
	public static volatile SingularAttribute<PhotoPrincipale, String> dataType;
	public static volatile SingularAttribute<PhotoPrincipale, Long> id;
	public static volatile SingularAttribute<PhotoPrincipale, String> nom;

	public static final String VOITURE = "voiture";
	public static final String DATA = "data";
	public static final String DATA_TYPE = "dataType";
	public static final String ID = "id";
	public static final String NOM = "nom";

}

