package com.spring.web.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.spring.web.dao.Offer;
import com.spring.web.dao.OfferDAO;

@Service("offersService")
public class OffersService {
	
	@Autowired
	private OfferDAO offerDAO;
	
	public List<Offer> getCurrent() {
		return offerDAO.getOffers();
	}
	
	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	public void create(Offer offer) {
		offerDAO.create(offer);
	}

	public boolean hasOffer(String name) {
		if (name == null) {
			return false;
		}
		List<Offer> offers = offerDAO.getOffers(name);
		return offers.size() > 0;
	}

	public Offer getOffer(String username) {
		Offer offer = null;
		if (username != null ) {
			List<Offer> offers = offerDAO.getOffers(username);
			offer = offers.size() > 0 ? offers.get(0) : null;
		}
		return offer;
	}

	public void save(Offer offer) {
		offerDAO.saveOrUpdate(offer);
	}

	public void delete(int id) {
		offerDAO.delete(id);
	}

}
