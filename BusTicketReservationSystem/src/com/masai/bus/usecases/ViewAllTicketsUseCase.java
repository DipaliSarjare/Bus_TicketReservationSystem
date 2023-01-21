package com.masai.bus.usecases;

import com.masai.bus.Dao.AdminDao;
import com.masai.bus.Dao.AdminDaoImpl;

public class ViewAllTicketsUseCase {
public static void viewAllTicket() {
		
		AdminDao dao = new AdminDaoImpl();
		dao.viewAllTickets();
	}
}
