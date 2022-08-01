package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.models.Users;
import com.revature.utils.ConnectionUtil;

public class AuthDAO {

	public Users login(String user_username, String user_password) {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			
		String sql = "select * from users where user_username = ? and user_password = ?;";
			
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ps.setString(1, user_username);
		ps.setString(2, user_password);
		
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()) {
			
			Users u = new Users(
					rs.getInt("user_id"),
					rs.getString("user_username"),
					rs.getString("user_password"),
					rs.getString("user_first_name"),
					rs.getString("user_last_name"),
					rs.getString("user_email"),
					rs.getInt("user_role_id")
					);
			
			return u;
			
		}
			
		} catch (SQLException e) {
			System.out.println("LOGIN FAILED");
			e.printStackTrace();
		}
		
		return null;
		
	}
	
}