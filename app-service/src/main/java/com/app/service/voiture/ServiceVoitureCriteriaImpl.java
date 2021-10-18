package com.app.service.voiture;


import java.util.Calendar;
import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;


import com.app.criteria.SearchCriteria;
import com.app.criteria.VoiturePage;
import com.app.entity.Voiture;
import com.app.services.ServiceVoitureCriteria;


@Component
public class ServiceVoitureCriteriaImpl implements ServiceVoitureCriteria {
	@PersistenceContext
	private EntityManager entityManager;
	private CriteriaBuilder builder ;

	@Override
	public Page<Voiture> searchVoiture(List<SearchCriteria> params , int page , int size){
		builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Voiture>  query = builder.createQuery(Voiture.class);
		Root<Voiture> root = query.from(Voiture.class);
        Predicate predicate = builder.conjunction();
        SearchVoitureByCriteriaConsumer consumer = new SearchVoitureByCriteriaConsumer(predicate, builder, root);
        params.forEach(consumer);
        predicate = consumer.getPredicate();
        query.where(predicate);
        TypedQuery<Voiture> typeQuery= entityManager.createQuery(query); 
        long voitureCount = getVoitureCount(predicate);
        VoiturePage pages = new VoiturePage(page,size);
        Pageable pageable = getPageable(pages);
        typeQuery.setFirstResult(page*size);
        typeQuery.setMaxResults(size);
        return new PageImpl<>(typeQuery.getResultList(),pageable,voitureCount);
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
	
	
	
}
