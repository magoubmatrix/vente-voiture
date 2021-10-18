package com.app.controller.voiture;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.criteria.SearchCriteria;
import com.app.entity.Voiture;
import com.app.services.ServiceVoitureCriteria;


@RestController
@RequestMapping("/criteria")
public class CriteriaController {

	@Autowired
	ServiceVoitureCriteria serviceVoiture;
	
	@GetMapping()
	public Page<Voiture> getVoitureUseCriteria(@RequestParam(value = "search", required = false) String search,@RequestParam int page, @RequestParam int size){
		  List<SearchCriteria> params = new ArrayList<SearchCriteria>();
	        if (search != null) {
	            Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
	            Matcher matcher = pattern.matcher(search + ",");
	            while (matcher.find()) {
	                params.add(new SearchCriteria(matcher.group(1), 
	                  matcher.group(2), matcher.group(3)));
	            }
	            
	          params.forEach(System.out::println);
	                  
	           
	        }
	        return serviceVoiture.searchVoiture(params , page,size);
		
		
	}
}
