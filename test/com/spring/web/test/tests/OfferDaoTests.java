package com.spring.web.test.tests;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.web.dao.Offer;
import com.spring.web.dao.OfferDAO;
import com.spring.web.dao.User;
import com.spring.web.dao.UserDAO;

@ActiveProfiles("development")
@ContextConfiguration(locations = { 
		"classpath:com/spring/web/config/dao-context.xml",
		"classpath:com/spring/web/config/security-context.xml", 
		"classpath:com/spring/web/test/config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class OfferDaoTests extends DaoTest {
	
	@Autowired
	private OfferDAO offerDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	User user = new User("test", "test", "password", "test@email.com", true, "ROLE_USER");
	User user2 = new User("test2", "test", "password", "test@email.com", true, "ROLE_USER");
	User userDisabled = new User("testDisabled", "teste", "password", "test@email.com", false, "ROLE_USER");
	
	@Test
	public void testCreateAndGetOffers() {
		userDAO.create(user);
		userDAO.create(user2);
		userDAO.create(userDisabled);
		
		Offer offer = new Offer("Teste teste", user);
		Offer offer2 = new Offer("Teste teste", userDisabled);
		Offer offer3 = new Offer("Teste teste", user2);
		offerDAO.create(offer);
		offerDAO.create(offer2);
		offerDAO.create(offer3);
		
		List<Offer> offers = offerDAO.getOffers();
		assertEquals("Number of offers should be 2", 2, offers.size());
		assertEquals("Offer should be the one with enabled user", offer, offers.get(0));
		
		offers = offerDAO.getOffers(user2.getUsername());
		assertEquals("Number of offers should be 1", 1, offers.size());
		assertEquals("Should be the offer 3 ", offer3, offers.get(0));
	}
	
	@Test
	public void testUpdateOffer() {
		userDAO.create(user);
		Offer offer = new Offer("Teste teste", user);
		offerDAO.create(offer);
		offer.setText("Edited Text");
		offerDAO.update(offer);
		offer = offerDAO.getOffer(offer.getId());
		assertEquals("Text should be the edited one.", "Edited Text", offer.getText());
	}
	
	@Test
	public void testSaveOrUpdate() {
		userDAO.create(user);
		Offer offer = new Offer("Teste teste", user);
		offerDAO.saveOrUpdate(offer);
		List<Offer> offers = offerDAO.getOffers();
		assertEquals("Number of offers should be 1", 1, offers.size());
		
		offer.setText("Edited Text");
		offerDAO.saveOrUpdate(offer);
		offer = offerDAO.getOffer(offer.getId());
		assertEquals("Text should be the edited one.", "Edited Text", offer.getText());
	}
	
	@Test
	public void testDelete() {
		userDAO.create(user);
		Offer offer = new Offer("Teste teste", user);
		offerDAO.saveOrUpdate(offer);
		
		List<Offer> offers = offerDAO.getOffers();
		assertEquals("Number of offers should be 1", 1, offers.size());
		
		offerDAO.delete(offer.getId());
		offers = offerDAO.getOffers();
		assertEquals("Number of offers should be 0", 0, offers.size());
	}
}
