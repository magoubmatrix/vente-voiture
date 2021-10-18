package com.app.kafka.config;

import java.util.GregorianCalendar;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.app.entity.MapDate;
import com.app.entity.Voiture;
import com.app.services.ServiceVoiture;
import com.fasterxml.jackson.databind.json.JsonMapper;

import lombok.Data;

@Component
@Data
public class Receiver {

	//private CountDownLatch latch = new CountDownLatch(1);

	private final ServiceVoiture serviceVoiture;

	
	@KafkaListener(topics = "voiture")
	public void receiver(ConsumerRecord<String, String> voitureConsumer) throws Exception {
		MapDate date = getVoiture(voitureConsumer.value());
	    //String year = date.getImmatriculation()[0];
	   // System.out.println("l'année de construction de la voiture est : " +year);
		System.out.println( date.getMarque());
		Voiture voiture = mapMapDateToVoiture(date);
		serviceVoiture.ajoutVoiture(voiture);
	}
	
	
	
	
	
	private Voiture  mapMapDateToVoiture( MapDate date ){
		Voiture voiture = new Voiture();
		voiture.setMarque(date.getMarque());
		voiture.setModele(date.getModele());
		voiture.setImmatriculation(convertArrayToCalendarDate(date.getStrDate()));
		voiture.setPrix(date.getPrix());
		
		return voiture;
	}
	
	private GregorianCalendar convertArrayToCalendarDate(String[] table){
		System.out.println("l'année est : " + table[0] + "le mois est : " + table[1] + "le jour est : " + table[3]);
       GregorianCalendar calendar = new GregorianCalendar(Integer.parseInt(table[0]), Integer.parseInt(table[1]), Integer.parseInt(table[2]));
   
		return calendar ;
	}
	
	public MapDate getVoiture(String jsonVoiture)throws Exception{
		JsonMapper jsonMapper = new JsonMapper();
	   MapDate voiture = jsonMapper.readValue(jsonVoiture, MapDate.class);
		return voiture;
		
	}
	
	/*
	public Voiture mapVoitureMapperToVoiture(VoitureConsumer consumer) {
		Voiture voiture = new Voiture();
		voiture.setMarque(consumer.getMarque());
		voiture.setModele(consumer.getModele());
		voiture.setAnneeImmatricul(consumer.getImmatriculation());
		voiture.setPrix(consumer.getPrix());
		return voiture;
	}
	*/
	
}
