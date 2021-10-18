package com.app.service.voiture;

import java.time.Year;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;

import com.app.criteria.SearchCriteria;
import com.app.entity.Voiture;
import com.app.repository.VoitureRepository;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data

public class SearchVoitureByCriteriaConsumer implements Consumer<SearchCriteria> {

	private Predicate predicate;
	private CriteriaBuilder builder;
	private Root r ;
	@Autowired
	private VoitureRepository voitureRepository;
	

	public SearchVoitureByCriteriaConsumer(Predicate predicate, CriteriaBuilder builder, Root r) {
		super();
		this.predicate = predicate;
		this.builder = builder;
		this.r = r;
	}
	
	
	
	@Override
	public void accept(SearchCriteria param) {
	
		
 if(param.getKey().equalsIgnoreCase("immatriculation")) {
			
			 
			 predicate = builder.and(predicate
						,builder.equal(r.get("anneeImmatricul"), param.getValue().toString() ));
		}
 
	else if(param.getOperation().equalsIgnoreCase(">")) {
			predicate = builder.and(predicate
					,builder.greaterThanOrEqualTo(r.get(param.getKey()), param.getValue().toString()));
		}
 
	else if (param.getOperation().equalsIgnoreCase("<")) {
			predicate = builder.and(predicate
					,builder.lessThanOrEqualTo(r.get(param.getKey()), param.getValue().toString()));
		}
		
		
	
		else {
			predicate = builder.and(predicate,builder.equal(r.get(param.getKey()),param.getValue().toString() ));
		}
		
	
		
	}




	//List<Voiture> getListByImmatriculation(int immatriculation){
		//return (List<Voiture>) voitureRepository.findAll().stream().filter(v -> v.getImmatriculation().get(Calendar.YEAR) == immatriculation).collect(Collectors.toList());
	//}

}
