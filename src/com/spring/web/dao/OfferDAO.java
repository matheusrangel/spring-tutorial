package com.spring.web.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@Component("offerDao")
public class OfferDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	public List<Offer> getOffers() {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Offer> cq = cb.createQuery(Offer.class);
		Root<Offer> offerRoot = cq.from(Offer.class);
		
		cq.where(cb.equal(offerRoot.get("user").get("enabled"), true));
		TypedQuery<Offer> query = session.createQuery(cq);
		return query.getResultList();
	}
	
	public List<Offer> getOffers(String username) {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Offer> cq = cb.createQuery(Offer.class);
		Root<Offer> offerRoot = cq.from(Offer.class);
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		predicates.add(cb.like((Expression) offerRoot.get("user").get("username"), username));
		predicates.add(cb.equal(offerRoot.get("user").get("enabled"), true));
		
		cq.where(predicates.toArray(new Predicate[predicates.size()]));
		TypedQuery<Offer> query = session.createQuery(cq);
		return query.getResultList();
	}

	public Offer getOffer(int id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(Offer.class, id);
	}
	
	public void create(Offer offer) {
		Session session = sessionFactory.getCurrentSession();
		session.save(offer);
	}
	
	public boolean delete(int id) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("delete from Offer where id = :id");
		query.setParameter("id", id);
		return query.executeUpdate() == 1;
	}
	
	public void update(Offer offer) {
		Session session = sessionFactory.getCurrentSession();
		session.update(offer);
	}
	
	public void saveOrUpdate(Offer offer) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(offer);
	}
}
