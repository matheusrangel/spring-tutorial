package com.spring.web.controllers;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.web.dao.FormValidationGroup;
import com.spring.web.dao.Offer;
import com.spring.web.services.OffersService;

@Controller
public class OffersController {
	
	@Autowired
	private OffersService offersService;
	
	@RequestMapping("/create")
	public String createOffer(Model model, Principal principal) {
		
		Offer offer = null;
		if (principal != null) {
			offer = offersService.getOffer(principal.getName());
		}
		offer = (offer != null ? offer : new Offer());
		model.addAttribute("offer", offer);
		return "createoffer";
	}
	
	@RequestMapping(value="/docreate", method=RequestMethod.POST)
	public String doCreate(Model model, @Validated(value=FormValidationGroup.class) Offer offer, 
			BindingResult result, Principal principal, 
			@RequestParam(value="delete", required=false) String delete) {
		
		if (result.hasErrors()) {
			return "createoffer";
		} 
		
		if (delete == null) {
			offer.getUser().setUsername(principal.getName());
			offersService.save(offer);
			return "offercreated";
		} else {
			offersService.delete(offer.getId());
			return "offerdeleted";
		}
	}
	
}
