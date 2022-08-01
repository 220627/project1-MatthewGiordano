package com.revature.controllers;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.revature.daos.ReimbursementDAO;
import com.revature.models.Reimbursement;

import io.javalin.http.Handler;

public class ReimbursmentController {

		ReimbursementDAO rDAO = new ReimbursementDAO();
	//This Handler will get the HTTP GET Request for all reimbursements, then collect the data and send it back in an HTTP Response 
		public Handler getReimbursementsHandler = (ctx) -> {
			
			if(AuthController.ses != null) {
			
			//We need an ArrayList of Reimbursements, courtesy of our ReimbursementDAO
			ArrayList<Reimbursement> reimbursements = rDAO.getReimbursements();
			
			//create a GSON object, which has methods to convert our Java object into JSON
			Gson gson = new Gson();
			
			//use the GSON .toJson() method to turn our Java into JSON String (JSON is always in String format on the Java side)
			String JSONreimbursements = gson.toJson(reimbursements); 
			
			ctx.result(JSONreimbursements); //ctx.result() sends a response back (this is where our data goes)
			
			ctx.status(200); //ctx.status() sets the HTTP status code. 200 stands for "OK", the generic success code.
			
			} else { //if the user is NOT logged in (aka AuthController.ses wil be null)
				ctx.result("YOU ARE NOT LOGGED IN!! *SMACK*");
				ctx.status(401); //"forbidden" access code
			}
			
		};
		
		public Handler insertReimbursementHandler = (ctx) -> {
			
			String body = ctx.body();
			
			Gson gson = new Gson();
			
			Reimbursement newReimb = gson.fromJson(body, Reimbursement.class);
			
			if(rDAO.insertReimbursement(newReimb)) {
				ctx.status(202);
			} else {
				ctx.status(406);
			}
			
		};
}
