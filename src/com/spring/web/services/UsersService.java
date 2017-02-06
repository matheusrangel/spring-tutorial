package com.spring.web.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.spring.web.dao.Message;
import com.spring.web.dao.MessageDAO;
import com.spring.web.dao.User;
import com.spring.web.dao.UserDAO;

@Service("usersService")
public class UsersService {
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private MessageDAO messageDAO;

	public void create(User user) {
		userDAO.create(user);
	}

	public boolean exists(String username) {
		return userDAO.exists(username);
	}
	
	@Secured("ROLE_ADMIN")
	public List<User> getAllUsers() {
		return userDAO.getAllUsers();
	}
	
	public void sendMessage(Message message) {
		messageDAO.saveOrUpdate(message);
	}
	
	public User getUser(String username) {
		return userDAO.getUser(username);
	}

	public List<Message> getMessages(String username) {
		return messageDAO.getMessages(username);
	}
	
	public List<User> getUsersByEmail(String email) {
		return userDAO.getUsersByEmail(email);
	}
}
