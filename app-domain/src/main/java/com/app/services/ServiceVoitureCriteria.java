package com.app.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.app.criteria.SearchCriteria;
import com.app.entity.Voiture;


public interface ServiceVoitureCriteria {

	public Page<Voiture> searchVoiture(List<SearchCriteria> params , int page , int size);
}
