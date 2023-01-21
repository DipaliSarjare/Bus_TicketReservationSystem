package com.masai.bus.usecases;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.masai.bus.Dao.AdminDao;
import com.masai.bus.Dao.AdminDaoImpl;

import com.masai.bus.models.BusDetails;

public class AddBusDetailsUseCase {

	public static void AddBusInfo() {

		Scanner sc = new Scanner(System.in);
		
		try {
			
			System.out.println("Enter Bus Number:");

			int busNo = sc.nextInt();
			
			System.out.println("Enter Bus Name:");
			String bName = sc.next();
			
			System.out.println("Enter Route from");
			String routeFrom = sc.next();
			
			System.out.println("Enter Routo To");
			String routeTo = sc.next();
			
			System.out.println("Enter Bus Type - AC / NonAC");
			String bType = sc.next();
			
			sc.nextLine();
			System.out.println("Enter Departure date and time in format (YYYY-MM-DD HH:MI:SS)");
			String departure = sc.nextLine();
			
			System.out.println("Enter Arrival date and time in format (YYYY-MM-DD HH:MI:SS)");
			String arrival = sc.nextLine();
			
			System.out.println("Enter Total Seats :");
			int totalSeats = sc.nextInt();
			
			System.out.println("Enter Available Seats :");
			int availSeats = sc.nextInt();
			
		    System.out.println("Enter Ticket Charge: ");
			int ticketCharge = sc.nextInt();
			
			BusDetails bus = new BusDetails(busNo, bName, routeFrom, routeTo, bType, departure, arrival, totalSeats, availSeats, ticketCharge);
			
			AdminDao dao = new AdminDaoImpl();
			
			String result = dao.addBusDetails(bus);
			
			if (result.equals("Bus added Successfully")) {
				System.out.println(result);
			}
			else 
				System.out.println(result);
		}
		catch (InputMismatchException e) {
			System.out.println("Invalid Input");
		}
	}
	
}
