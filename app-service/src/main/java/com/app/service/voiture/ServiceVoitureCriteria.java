package com.app.service.voiture;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.logging.log4j.util.PerformanceSensitive;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.app.entity.AppUser;
import com.app.entity.Voiture;
import com.app.entity.VoitureRecherche;

@Component
public class ServiceVoitureCriteria {
	@PersistenceContext
	private EntityManager entityManager;
	private CriteriaBuilder builder ;

	public Page<Voiture> searchVoiture(List<SearchCriteria> params , int page , int size){
		List<Voiture> voitures = new ArrayList<Voiture>();
	    builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Voiture>  query = builder.createQuery(Voiture.class);
		Root<Voiture> root = query.from(Voiture.class);
        Predicate predicate = builder.conjunction();
        SearchVoitureByCriteriaConsumer consumer = new SearchVoitureByCriteriaConsumer(predicate, builder, root);
        params.stream().forEach(consumer);
        predicate = consumer.getPredicate();
        query.where(predicate);
        TypedQuery<Voiture> typeQuery= entityManager.createQuery(query); 
      
        voitures = entityManager.createQuery(query).getResultList();
        //List<VoitureRecherche> voitureRecherches = mapToVoitureRecherche(voitures);
        long voitureCount = getVoitureCount(predicate);
        VoiturePage pages = new VoiturePage(page,size);
        Pageable pageable = getPageable(pages);
        typeQuery.setFirstResult(page*size);
        typeQuery.setMaxResults(size);
        return new PageImpl<>(typeQuery.getResultList(),pageable,voitureCount);
	}
	
	
	private Page<Voiture> maptedToVoitureRecherche(VoiturePage page,TypedQuery<Voiture> type,Pageable pageable,List<Voiture> voitures,Predicate predicate){
		type.setFirstResult(page.getPageNumber()*page.getPageSize());
		type.setMaxResults(page.getPageSize());
		pageable = getPageable(page);
		long voitureCount = getVoitureCount(predicate); 
		
		return new PageImpl<>(voitures,pageable,voitureCount);
	}
	
	private long getVoitureCount(Predicate predicate) {
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<Voiture> root = query.from(Voiture.class);
		query.select(builder.count(root)).where(predicate);
		return entityManager.createQuery(query).getSingleResult();
		}
	
	private Pageable getPageable(VoiturePage page) {
		Sort sort = Sort.by(page.getDirectionSort(),page.getSortMape());
		return PageRequest.of(page.getPageNumber(), page.getPageSize(),sort);
	}
	
	private List<VoitureRecherche> mapToVoitureRecherche(List<Voiture> voitures){
		List<VoitureRecherche> voitureRecherche = new ArrayList<VoitureRecherche>();
		voitures.forEach(v -> {voitureRecherche.add(new VoitureRecherche(v.getMarque(),v.getModele(),v.getImmatriculation().get(Calendar.YEAR),v.getPrix())); });
		return voitureRecherche;
	}
	
}
