package com.publicissapient.lloyds.orderservice.doaminmodel.repository.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.publicissapient.lloyds.orderservice.doaminmodel.repository.CustomOrderRepository;
import com.publicissapient.lloyds.orderservice.domainmodel.entity.OrderEntity;

@Repository
public class CustomOrderRepositoryImpl implements CustomOrderRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<OrderEntity> searchOrder(Long orderId, String customerId, LocalDate date, String productCode,
			String sortBy) {

		CriteriaBuilder qb = entityManager.getCriteriaBuilder();
		CriteriaQuery cq = qb.createQuery();
		Root<OrderEntity> order = cq.from(OrderEntity.class);
		if(null != sortBy) {
			cq.orderBy(qb.asc(order.get(sortBy)));
		}
		List<Predicate> predicates = new ArrayList<>();

		if (null != orderId) {
			predicates.add(qb.equal(order.get("orderId"), orderId));
		}
		if (null != customerId) {
			predicates.add(qb.equal(order.get("customerId"), customerId));
		}
		if (null != date) {
			predicates.add(qb.equal(order.<LocalDate>get("date"), date));
		}
		if (null != productCode) {
			predicates.add(qb.equal(order.get("productCode"),productCode));
		}

		cq.select(order).where(predicates.toArray(new Predicate[] {}));
		return entityManager.createQuery(cq).getResultList();
	}

}
