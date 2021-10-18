package com.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoitureRecherche {

	private String marque;
	private String modele;
	private int immatriculation;
	private double prix;
}
