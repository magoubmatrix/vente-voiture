package com.app.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AppUser.class)
public abstract class AppUser_ {

	public static volatile SingularAttribute<AppUser, String> password;
	public static volatile SingularAttribute<AppUser, AppRole> role;
	public static volatile ListAttribute<AppUser, Voiture> voiture;
	public static volatile SingularAttribute<AppUser, Boolean> active;
	public static volatile SingularAttribute<AppUser, Long> id;
	public static volatile SingularAttribute<AppUser, String> email;
	public static volatile SingularAttribute<AppUser, String> username;

	public static final String PASSWORD = "password";
	public static final String ROLE = "role";
	public static final String VOITURE = "voiture";
	public static final String ACTIVE = "active";
	public static final String ID = "id";
	public static final String EMAIL = "email";
	public static final String USERNAME = "username";

}

