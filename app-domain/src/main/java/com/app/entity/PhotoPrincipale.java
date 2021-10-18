package com.app.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;

import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;





@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhotoPrincipale {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nom;
	private String dataType;
	@Lob
    private byte[] data;	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(referencedColumnName = "id",name = "voitureId")
	private Voiture voiture;
	
}
