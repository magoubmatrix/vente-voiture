package com.app.service.voiture;

import java.util.function.Consumer;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchVoitureByCriteriaConsumer implements Consumer<SearchCriteria> {

	private Predicate predicate;
	private CriteriaBuilder builder;
	private Root r ;
	
	@Override
	public void accept(SearchCriteria param) {
	
		if(param.getOperation().equalsIgnoreCase(">")) {
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

}
