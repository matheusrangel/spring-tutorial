package com.spring.web.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.web.dao.FormValidationGroup;
import com.spring.web.dao.Message;
import com.spring.web.dao.User;
import com.spring.web.services.UsersService;

@Controller
public class LoginController {
	
	@Autowired
	private MailSender mailSender;
	
	@Autowired
	private UsersService usersService;
	
	@RequestMapping("/login")
	public String showLogin() {
		return "login";
	}
	
	@RequestMapping("/loggedout")
	public String showLoggedOut() {
		return "loggedout";
	}
	
	@RequestMapping("/denied")
	public String showDenied() {
		return "denied";
	}
	
	@RequestMapping("/messages")
	public String showMessages() {
		return "messages";
	}
	
	@RequestMapping("/admin")
	public String showAdmin(Model model) {
		List<User> users = usersService.getAllUsers();
		model.addAttribute("users", users);
		return "admin";
	}
	
	@RequestMapping("/newaccount")
	public String showNewAccount(Model model) {
		model.addAttribute("user", new User());
		return "newaccount";
	}
	
	@RequestMapping(value="/createaccount", method=RequestMethod.POST)
	public String createAccount(@Validated(FormValidationGroup.class) User user, BindingResult result) {
		if (result.hasErrors()) {
			return "newaccount";
		} 
		user.setAuthority("ROLE_USER");
		user.setEnabled(true);
		
		if (usersService.exists(user.getUsername())) {
			result.rejectValue("username", "DuplicateKey.user.username");
			return "newaccount";
		}
		
		try {
			usersService.create(user);
		} catch (DuplicateKeyException e) {
			result.rejectValue("username", "DuplicateKey.user.username");
			return "newaccount";
		}
		return "accountcreated";
	}
	
	@RequestMapping(value="/getmessages", method=RequestMethod.GET, produces="application/json")
	@ResponseBody
	public Map<String, Object> getMessages(Principal principal, Model model) {
		List<Message> messages = new ArrayList<Message>();
		
		if (principal != null) {
			messages = usersService.getMessages(principal.getName());
		}
		
		model.addAttribute("messages", messages);
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("messages", messages);
		data.put("number", messages.size());
		return data;
	}
	
	@RequestMapping(value="/sendmessage", method=RequestMethod.POST, consumes="application/json", produces="application/json")
	@ResponseBody
	public Map<String, Object> sendMessage(Principal principal, @RequestBody Map<String, Object> data) {
		
		String text = (String) data.get("text");
		String name = (String) data.get("name");
		String email = (String) data.get("email");
		Integer target = (Integer) data.get("target");
		
//		SimpleMailMessage mail = new SimpleMailMessage();
//		mail.setFrom("email@email.com");
//		mail.setTo(email);
//		mail.setSubject("Re: " + name + ", your message");
//		mail.setText(text);
//		mailSender.send(mail);
		
		Map<String, Object> rval = new HashMap<String, Object>();
		rval.put("success", true);
		rval.put("target", target);
		
		return rval;
	}
}
