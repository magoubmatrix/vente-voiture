package com.app.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(VerificationTokenEmail.class)
public abstract class VerificationTokenEmail_ {

	public static volatile SingularAttribute<VerificationTokenEmail, Long> id;
	public static volatile SingularAttribute<VerificationTokenEmail, AppUser> user;
	public static volatile SingularAttribute<VerificationTokenEmail, String> token;

	public static final String ID = "id";
	public static final String USER = "user";
	public static final String TOKEN = "token";

}

