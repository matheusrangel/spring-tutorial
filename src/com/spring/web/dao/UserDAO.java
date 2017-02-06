package com.spring.web.dao;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@Component("userDao")
public class UserDAO {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	public void create(User user) {
		Session session = sessionFactory.getCurrentSession();
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		session.save(user);
	}
	
	public boolean exists(String username) {
		return getUser(username) != null;
	}

	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {
		Session session = sessionFactory.getCurrentSession();
		List<User> users = session.createQuery("from User").getResultList();
		return users;
	}

	public User getUser(String username) {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<User> cq = cb.createQuery(User.class);
		Root<User> userRoot = cq.from(User.class);
		
		cq.where(cb.equal(userRoot.get("username"), username));
		TypedQuery<User> query = session.createQuery(cq);
		List<User> users =  query.getResultList();
		if (users.isEmpty()) {
			return null;
		} else {
			return users.get(0);
		}
	}
	
	public List<User> getUsersByEmail(String email) {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<User> cq = cb.createQuery(User.class);
		Root<User> userRoot = cq.from(User.class);
		
		cq.where(cb.equal(userRoot.get("email"), email));
		TypedQuery<User> query = session.createQuery(cq);
		return query.getResultList();
	}
}
