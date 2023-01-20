package com.masai.bus.usecases;

import java.util.Scanner;

import com.masai.bus.Dao.CustomerDao;
import com.masai.bus.Dao.CustomerDaoImpl;
import com.masai.bus.exception.AdminException;
import com.masai.bus.models.Customer;

public class TicketCancellationUseCase {
	
	public  static void cancelTicket(Customer customer) {

		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter Bus Name");
		String bName = sc.nextLine();
		
		CustomerDao dao = new CustomerDaoImpl();
		try {
			
			int cId = customer.getcId();
			String message = dao.cancelTicket(bName, cId);
			
			if (message.equals("Ticket cancelled Successfully")) {
				System.out.println(message);
			}
			else {
				System.out.println(message);
			}
			
		} catch (AdminException e) {
			System.out.println(e.getMessage());
		}
	
	}

}
