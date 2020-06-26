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

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import com.centralerrosapi.model.Log;
import com.centralerrosapi.repository.filter.LogFilter;
import com.centralerrosapi.repository.projection.LogResume;

public class LogRepositoryImpl implements LogRepositoryQuery{
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<Log> filtrar(LogFilter logFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Log> criteria = builder.createQuery(Log.class);
		Root<Log> root = criteria.from(Log.class);
		
		// Restrictions
		Predicate[] predicates = createRestrictions(logFilter, builder, root); 
		criteria.where(predicates);
		
		
		TypedQuery<Log> query = manager.createQuery(criteria);
		addPaginationRestriction(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(logFilter));
	}

	@Override
	public Page<LogResume> resume(LogFilter logFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<LogResume> criteria = builder.createQuery(LogResume.class);
		Root<Log> root = criteria.from(Log.class);
		
		criteria.select(
				builder.construct(LogResume.class
						, root.get("id")
						, root.get("title")
						, root.get("createdAt")
						, root.get("category")
						, root.get("level")
						, root.get("system").get("name")));

		// Restrictions
		Predicate[] predicates = createRestrictions(logFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<LogResume> query = manager.createQuery(criteria);
		addPaginationRestriction(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(logFilter));
	}

	private Predicate[] createRestrictions(LogFilter logFilter, CriteriaBuilder builder, Root<Log> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		if(!StringUtils.isEmpty(logFilter.getTitle())) {
			predicates.add(builder.like(builder.lower(root.get("title")), "%" + logFilter.getTitle().toLowerCase() + "%"));
		}
		
		if (!StringUtils.isEmpty(logFilter.getDetail())) {
			predicates.add(builder.like(builder.lower(root.get("detail")), "%" + logFilter.getDetail().toLowerCase() + "%"));
		}
		
		if (logFilter.getCreatedAtDe() !=null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get("createdAt"), logFilter.getCreatedAtDe()));
		}
		
		if (logFilter.getCreatedAtAte() !=null) {
			predicates.add(builder.lessThanOrEqualTo(root.get("createdAt"), logFilter.getCreatedAtAte()));
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
	private void addPaginationRestriction(TypedQuery<?> query, Pageable pageable) {
		int currentPage = pageable.getPageNumber();
		int totalRecordsPerPage = pageable.getPageSize();
		int firstPageRecord = currentPage * totalRecordsPerPage;
		
		query.setFirstResult(firstPageRecord);
		query.setMaxResults(totalRecordsPerPage);
	}
	
	private Long total(LogFilter logFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Log> root = criteria.from(Log.class);
		
		Predicate[] predicates = createRestrictions(logFilter, builder, root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
		
	}

}
