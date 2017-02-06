package com.spring.web.test.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.web.dao.User;
import com.spring.web.dao.UserDAO;

@ActiveProfiles("development")
@ContextConfiguration(locations = { 
		"classpath:com/spring/web/config/dao-context.xml",
		"classpath:com/spring/web/config/security-context.xml", 
		"classpath:com/spring/web/test/config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class UserDaoTests extends DaoTest {
	
	@Autowired
	private UserDAO userDAO;
	
	@Test
	public void testUsers() {
		User user = new User("matheusrangel", "Matheus", "password", "test@teste.com", true, "ROLE_ADMIN");
		userDAO.create(user);
		
		List<User> users = userDAO.getAllUsers();
		assertEquals("Number of users should be 1.", 1, users.size());
		assertTrue("User should exist", userDAO.exists(user.getUsername()) == true);
		assertEquals("Created user should be identical to retrieved user.", user, users.get(0));
		
		User user2 = new User("testuser2", "Test User 2", "password", "test@teste.com", true, "ROLE_USER");
		User user3 = new User("testuser3", "Test User 3", "password", "test@teste.com", true, "ROLE_USER");
		userDAO.create(user2);
		userDAO.create(user3);
		users = userDAO.getAllUsers();
		
		assertEquals("Should be 3 retrieved users", 3, users.size());
	}
	
}
