package com.spring.web.test.tests;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.web.dao.Message;
import com.spring.web.dao.MessageDAO;
import com.spring.web.dao.User;
import com.spring.web.dao.UserDAO;

@ActiveProfiles("development")
@ContextConfiguration(locations = { 
		"classpath:com/spring/web/config/dao-context.xml",
		"classpath:com/spring/web/config/security-context.xml", 
		"classpath:com/spring/web/test/config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class MessageDaoTests extends DaoTest {
	
	@Autowired
	private MessageDAO messageDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	User user = new User("test", "test", "password", "test@email.com", true, "ROLE_USER");
	User user2 = new User("test2", "test", "password", "test@email.com", true, "ROLE_USER");
	User userDisabled = new User("testDisabled", "teste", "password", "test@email.com", false, "ROLE_USER");
	
	@Test
	public void testCreateAndGetMessages() {
		userDAO.create(user);
		userDAO.create(user2);
		userDAO.create(userDisabled);
		
		Message message = new Message("subject", "content", user.getName(), user.getEmail(), user2.getUsername());
		Message message2 = new Message("subject2", "content2", user.getName(), user.getEmail(), userDisabled.getUsername());
		messageDAO.saveOrUpdate(message);
		messageDAO.saveOrUpdate(message2);
		
		List<Message> messages = messageDAO.getMessages();
		assertEquals("Number of messages should be 2", 2, messages.size());
		
		messages = messageDAO.getMessages(user2.getUsername());
		assertEquals("Number of messages should be 1", 1, messages.size());
		assertEquals("Should be the message 1 ", message, messages.get(0));
	}
	
	
	@Test
	public void testSaveOrUpdate() {
		userDAO.create(user);
		userDAO.create(user2);
		Message message = new Message("subject", "content", user.getName(), user.getEmail(), user2.getUsername());
		messageDAO.saveOrUpdate(message);
		List<Message> messages = messageDAO.getMessages();
		assertEquals("Number of messages should be 1", 1, messages.size());
		
		message.setContent("Edited Content");
		messageDAO.saveOrUpdate(message);
		message = messageDAO.getMessage(message.getId());
		assertEquals("Content should be the edited one.", "Edited Content", message.getContent());
	}
	
	@Test
	public void testDelete() {
		userDAO.create(user);
		userDAO.create(user2);
		Message message = new Message("subject", "content", user.getName(), user.getEmail(), user2.getUsername());
		messageDAO.saveOrUpdate(message);
		
		List<Message> messages = messageDAO.getMessages();
		assertEquals("Number of messages should be 1", 1, messages.size());
		
		messageDAO.delete(message.getId());
		messages = messageDAO.getMessages();
		assertEquals("Number of messages should be 0", 0, messages.size());
	}
}
