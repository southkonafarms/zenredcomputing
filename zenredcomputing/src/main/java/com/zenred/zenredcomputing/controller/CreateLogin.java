package com.zenred.zenredcomputing.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.routines.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.zenred.zenredcomputing.controller.json.CreateLoginView;
import com.zenred.zenredcomputing.domain.User;
import com.zenred.zenredcomputing.domain.UserDao;
import com.zenred.zenredcomputing.domain.UserStatus;
import com.zenred.zenredcomputing.vizualization.CreateLoginResponse;



public class CreateLogin implements Controller, StateIF {
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.addHeader("Access-Control-Allow-Origin", "*");
		
		System.out.println("CREATING_LOGIN:"+request.getParameter("email"));
		String emailAddress = request.getParameter("email");
		String password1 = request.getParameter("Password1");
		String password2 = request.getParameter("Password2");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		CreateLoginResponse createLoginResponse = new CreateLoginResponse();
		ModelAndView modelAndView = new ModelAndView(new CreateLoginView());
		EmailValidator emailValidator = EmailValidator.getInstance();
		if(!emailValidator.isValid(emailAddress)){
			createLoginResponse.setTheMessage("The e-mail address is non-standard:"+emailAddress);
			modelAndView.addObject(CreateLoginView.JSON_ROOT, createLoginResponse);
			return modelAndView;
		}
		else if(!password1.equals(password2)){
			createLoginResponse.setTheMessage("Passwords do not match");
			modelAndView.addObject(CreateLoginView.JSON_ROOT, createLoginResponse);
			return modelAndView;
		}
		User user = new User();
		user.setEmailAddress(emailAddress);
		user.setFirstName(anonymousName(firstName));
		user.setLastName(anonymousName(lastName));
		user.setPassword(password1);
		user.setUser_Status(UserStatus.registered);
		UserDao userDao = new UserDao();
		System.out.println("CREATING_USER:"+user);
		userDao.createUser(user);
		createLoginResponse.setTheMessage("SUCCESS");
		createLoginResponse.setEmailAddress(emailAddress);
		modelAndView.addObject(CreateLoginView.JSON_ROOT, createLoginResponse);
		return modelAndView;

	}
	
	private String anonymousName(String name){
		if(null == name || name.isEmpty()){
			return "NONE";
		}
		else{
			return name;
		}
	}

}
