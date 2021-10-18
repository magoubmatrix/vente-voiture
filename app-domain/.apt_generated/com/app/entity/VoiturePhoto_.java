package com.app.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(VoiturePhoto.class)
public abstract class VoiturePhoto_ {

	public static volatile SingularAttribute<VoiturePhoto, Voiture> voiture;
	public static volatile SingularAttribute<VoiturePhoto, byte[]> data;
	public static volatile SingularAttribute<VoiturePhoto, String> dataType;
	public static volatile SingularAttribute<VoiturePhoto, Long> id;
	public static volatile SingularAttribute<VoiturePhoto, String> nom;

	public static final String VOITURE = "voiture";
	public static final String DATA = "data";
	public static final String DATA_TYPE = "dataType";
	public static final String ID = "id";
	public static final String NOM = "nom";

}

