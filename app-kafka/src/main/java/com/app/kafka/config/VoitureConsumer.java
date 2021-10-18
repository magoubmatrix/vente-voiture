package com.app.kafka.config;

import java.util.Date;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class VoitureConsumer {

	private String marque;
	private String modele;
	private int immatriculation;
	private double prix;
}
