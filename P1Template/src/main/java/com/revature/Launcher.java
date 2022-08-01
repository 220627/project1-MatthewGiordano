package com.revature;

import java.sql.Connection;
import java.sql.SQLException;

import com.revature.controllers.AuthController;
import com.revature.controllers.ReimbursmentController;
import com.revature.utils.ConnectionUtil;

import io.javalin.Javalin;

public class Launcher {

	public static void main(String[] args) {
		
		//Welcome to P1! 
		
		//If you're reading this, you've successfully cloned your repo and imported the template
		
		//Do you coding in this project, and don't forget to save/push your progress with:
		//git add.
		//git commit -m"message"
		//git push
		
		//yes, you WILL need to push to your repo. The clients will want to see your project repos in your portfolios.
		
		//here's a dog to help you on your way. Have fun!
		
//               __
//          (___()'`;
//          /,    /`
//          \\"--\\
			
			System.out.println("========Welcome to the Employee Reimbursement Systemm========");
			
			//this is a try-with-resources block. it will test whether our Connection works.
			//try-with-resources works by trying to open a certain resource (Connection in this case)
			//and catch an exception if the resource fails to open. 
			//So in this case, if we fail to open a connection, it will throw an exception
			try(Connection conn = ConnectionUtil.getConnection()){
				System.out.println("CONNECTION SUCCESSFUL :)");
			} catch (SQLException e) {
				//if the connection fails to open, catch the resulting SQLException and print the stack trace (error log)
				System.out.println("connection failed...");
				e.printStackTrace();
			} //end of the connection test
				
			
			//Typical Javalin syntax to CREATE a javalin object 
			Javalin app = Javalin.create(
					
					//the config lambda lets us specify certain configurations for our Javalin app.
					config -> {
						config.enableCorsForAllOrigins(); //this lets us process HTTP requests from any origin
					}
					
					).start(3000); //we need .start() to start our Java server on port 3000.
					//This port is important, because this is where we need to send requests to.
			
			ReimbursmentController rc = new ReimbursmentController();
			
			AuthController ac = new AuthController();
			
			app.get("/reimbursements", rc.getReimbursementsHandler);
			
			app.post("/reimbursements", rc.insertReimbursementHandler);
			
			app.post("/login", ac.loginHandler);
			
	}
	
}
