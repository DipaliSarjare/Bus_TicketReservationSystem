package com.masai.bus.Dao;

import com.masai.bus.exception.AdminException;
import com.masai.bus.exception.CustomerException;
import com.masai.bus.models.Customer;

public interface CustomerDao {
	
    public String customerRegister (Customer customer);
	
	public Customer customerLogin (String username, String password) throws CustomerException;
	
	public String bookTicketSourseToDestination (String bName, int cId, int noseat) throws AdminException;
	
	public String cancelTicket(String bName, int cId) throws AdminException;
	
	public void viewTickets(int cId);

}
