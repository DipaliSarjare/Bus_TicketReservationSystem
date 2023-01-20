package com.masai.bus.usecases;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.masai.bus.Dao.CustomerDao;
import com.masai.bus.Dao.CustomerDaoImpl;
import com.masai.bus.exception.AdminException;
import com.masai.bus.models.Customer;

public class BookTicketSourseToDestinationUseCase {

public static void bookTicketSourseToDestination(Customer customer) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter Bus Name: ");
		String bName = sc.next();
		
		CustomerDao dao = new CustomerDaoImpl();
		try {
			System.out.println("Enter no. of Tickets to Book");
			int noseat = sc.nextInt();
			
			int cId = customer.getcId();
			String message = dao.bookTicketSourseToDestination(bName, cId, noseat);
			
			if (message.equals("Ticket Booked Successfully")) {
				System.out.println(message);
			}
			else {
				System.out.println(message);
			}
			
		} catch (AdminException e) {
			System.out.println(e.getMessage());
		}
		catch (InputMismatchException e) {
			System.out.println("Invalid input");
		}
		
	}
}
