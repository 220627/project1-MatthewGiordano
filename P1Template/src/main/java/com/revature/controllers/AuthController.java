package com.revature.controllers;

import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.revature.models.LoginDTO;
import com.revature.models.Users;
import com.revature.services.AuthService;

import io.javalin.http.Handler;

public class AuthController {

	//we need an AuthService, remember the Service layer sits between the Controllers and DAOs
	AuthService as = new AuthService();
	
	public static HttpSession ses;
	
	public Handler loginHandler = (ctx) -> {
		
		String body = ctx.body();
		
		Gson gson = new Gson();
		
		LoginDTO lDTO = gson.fromJson(body, LoginDTO.class);
		
		Users user = as.login(lDTO.getUser_username(), lDTO.getUser_password()); //this will either be a User or Null.
		
		if(user != null) { 
			
			ses = ctx.req.getSession(); 
			
			String userJSON = gson.toJson(user);
			
			ctx.result(userJSON);
			ctx.status(202); //202 stands for "accepted"
			
		} else {
			ctx.status(401); //401 stands for "unauthorized"
		}
		
	};
	
}
