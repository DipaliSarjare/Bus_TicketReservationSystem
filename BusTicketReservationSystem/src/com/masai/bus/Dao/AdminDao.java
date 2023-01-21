package com.masai.bus.Dao;

import com.masai.bus.models.BusDetails;

public interface AdminDao {
    public final String username = "Admin";
	
	public final String password = "Ad1234";

	public String adminLogin(String username, String password);
	
    public String addBusDetails(BusDetails bus);
    
    public String confirmationSeatsStatus(int cId);
	
	public void viewAllTickets();


}
