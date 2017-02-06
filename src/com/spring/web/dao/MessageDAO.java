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
@Component("messageDao")
public class MessageDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	public List<Message> getMessages() {
		Session session = sessionFactory.getCurrentSession();
		TypedQuery<Message> query = session.createQuery("from Message", Message.class);
		return query.getResultList();
	}
	
	public List<Message> getMessages(String username) {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Message> cq = cb.createQuery(Message.class);
		Root<Message> root = cq.from(Message.class);
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		predicates.add(cb.like((Expression) root.get("username"), username));
		
		cq.where(predicates.toArray(new Predicate[predicates.size()]));
		TypedQuery<Message> query = session.createQuery(cq);
		return query.getResultList();
	}

	public Message getMessage(int id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(Message.class, id);
	}
	
	public boolean delete(int id) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("delete from Message where id = :id");
		query.setParameter("id", id);
		return query.executeUpdate() == 1;
	}
	
	
	public void saveOrUpdate(Message message) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(message);
	}
}
