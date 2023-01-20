package com.masai.bus.usecases;

import com.masai.bus.Dao.CustomerDao;
import com.masai.bus.Dao.CustomerDaoImpl;
import com.masai.bus.models.Customer;

public class ViewTicketUseCase {
public static void viewTicket(Customer customer) {
		
		CustomerDao dao = new CustomerDaoImpl();
		
		dao.viewTickets(customer.getcId());
	}

}
