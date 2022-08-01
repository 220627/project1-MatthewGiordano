package com.revature.daos;

import java.util.ArrayList;

import com.revature.models.Reimbursement;

public interface ReimbursementDAOInterface {

		
		boolean insertReimbursement(Reimbursement reimbursement);
		
		ArrayList<Reimbursement> getReimbursements();
		
		boolean updateReimbursementStatus(String reimb_resolved, int reimb_status_id);

}
	
