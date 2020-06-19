package com.centralerrosapi.repository.log;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.util.StringUtils;

import com.centralerrosapi.model.Log;
import com.centralerrosapi.model.Log_;
import com.centralerrosapi.repository.filter.LogFilter;

public class LogRepositoryImpl implements LogRepositoryQuery{
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Log> filtrar(LogFilter logFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Log> criteria = builder.createQuery(Log.class);
		Root<Log> root = criteria.from(Log.class);
		
		// Restrictions
		Predicate[] predicates = createRestrictions(logFilter, builder, root); 
		criteria.where(predicates);
		
		
		TypedQuery<Log> query = manager.createQuery(criteria);
		return query.getResultList();
	}

	private Predicate[] createRestrictions(LogFilter logFilter, CriteriaBuilder builder, Root<Log> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		if(!StringUtils.isEmpty(logFilter.getTitle())) {
			predicates.add(builder.like(builder.lower(root.get(Log_.title)), "%" + logFilter.getTitle().toLowerCase() + "%"));
		}
		
		if (!StringUtils.isEmpty(logFilter.getDetail())) {
			predicates.add(builder.like(builder.lower(root.get(Log_.detail)), "%" + logFilter.getDetail().toLowerCase() + "%"));
		}
		
		if (logFilter.getCreatedAtDe() !=null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get(Log_.createdAt), logFilter.getCreatedAtDe()));
		}
		
		if (logFilter.getCreatedAtAte() !=null) {
			predicates.add(builder.lessThanOrEqualTo(root.get(Log_.createdAt), logFilter.getCreatedAtAte()));
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
