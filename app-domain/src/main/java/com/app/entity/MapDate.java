package com.app.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class MapDate {
	
	//@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	// private Long id;
	 private  String marque ;
	 private String modele;
	 private String[] strDate = new String[3];
	 private double prix;
	

}
