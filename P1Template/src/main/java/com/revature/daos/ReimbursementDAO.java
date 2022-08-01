package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.revature.models.Reimbursement;
import com.revature.utils.ConnectionUtil;

public class ReimbursementDAO implements ReimbursementDAOInterface {

	@Override
	public boolean insertReimbursement(Reimbursement reimbursement) {
		try(Connection conn = ConnectionUtil.getConnection()) {
			
			String sql = "insert into reimbursements (reimb_amount, reimb_submitted, reimb_description, reimb_author, reimb_resolver, reimb_status_id, reimb_type_id) values (?, ?, ?, ?, ?, ?, ?, ?);";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setBigDecimal(1, reimbursement.getReimb_amount());
			ps.setString(2, reimbursement.getReimb_submitted());
			ps.setString(3, reimbursement.getReimb_description());
			ps.setInt(4,  reimbursement.getReimb_author());
			ps.setInt(5,  reimbursement.getReimb_type_id());
			
			System.out.println(ps);
			
			ps.executeUpdate();
			
			System.out.println("Your reimbursement request was sent.");
			
			return true;
			
		} catch (SQLException e) {
			System.out.println("Get Reimbursements Failed");
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public ArrayList<Reimbursement> getReimbursements() {
		try(Connection conn = ConnectionUtil.getConnection()) {
			
			String sql = "Select * from reimbursements;";
			
			Statement s = conn.createStatement();
			
			ResultSet rs = s.executeQuery(sql);
			
			ArrayList<Reimbursement> reimbursementList = new ArrayList<>();
			
			while(rs.next())  {
				Reimbursement r = new Reimbursement(
					rs.getInt("reimb_id"),
					rs.getBigDecimal("reimb_amount"),
					rs.getString("reimb_submitted"),
					rs.getString("reimb_resolved"),
					rs.getString("reimb_description"),
					rs.getInt("reimb_author"),
					rs.getInt("reimb_resolver"),
					rs.getInt("reimb_status_id"),
					rs.getInt("reimb_type_id")
				);
			reimbursementList.add(r);	
				
			}
			
		return reimbursementList;
			
		} catch (SQLException e) {
			System.out.println("Get Reimbursements Failed");
			e.printStackTrace();
		}
		
		return null;
		
	} // end of get all

	@Override
	public boolean updateReimbursementStatus(String reimb_resolved, int reimb_status_id) {
		// TODO Auto-generated method stub
		return false;
	}

}
