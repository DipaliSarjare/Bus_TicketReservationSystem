package com.masai.bus.mainclass;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.masai.bus.models.Customer;
import com.masai.bus.usecases.AddBusDetailsUseCase;
import com.masai.bus.usecases.AdminLoginUseCase;
import com.masai.bus.usecases.BookTicketSourseToDestinationUseCase;
import com.masai.bus.usecases.ConfirmationSeatsStatusUseCase;
import com.masai.bus.usecases.CustomerLoginUseCase;
import com.masai.bus.usecases.CustomerRegisterationUseCase;
import com.masai.bus.usecases.TicketCancellationUseCase;
import com.masai.bus.usecases.ViewAllTicketsUseCase;
import com.masai.bus.usecases.ViewTicketUseCase;

public class Main {
	
	static void AdminOrCustomer() {
		System.out.println("+---------------------------+" + "\n"
						 						   + "| 1. Login As Administrator |" + "\n"
						 						   + "| 2. Login As Customer      |" + "\n"
						 						   + "| 3. Exit                   |" + "\n"
						 						   + "+---------------------------+" );
		choice();
	}
	
	static void choice() {
		Scanner sc = new Scanner(System.in);
		
		int choice = 0;
		try {
			choice = sc.nextInt();
		}
		catch (InputMismatchException e) {
			System.out.println("Input type should be number");
			AdminOrCustomer();
		}
		
		if (choice == 1) {
			System.out.println("Welcome Admin ! Please Login to your account");
			AdminLogin();
		}
		else if (choice == 2) {
			System.out.println("Welcome Customer !");
			customerLoginOrRegister();
		}
		else if (choice == 3) {
			System.out.println("Thank you ! Visit again");
			System.exit(0);
		}
		else {
			System.out.println("Please choose a number from below options");
			AdminOrCustomer();
		}
	}
	
	static void AdminLogin() {
		
		Boolean result = AdminLoginUseCase.AdminLogin();

		if (result) adminMethods();
		else {
			AdminLogin();
		}
	}
	
	static void adminMethods() {
		System.out.println("+--------------------------------+" + "\n"
						 + "| Welcome Admin                  |" + "\n"
						 + "| 1. Add Bus                     |" + "\n"
						 + "| 2. Confirm Tickets of Customer |" + "\n"
						 + "| 3. View All Bookings           |" + "\n"
						 + "| 4. Back                        |" + "\n"
						 + "| 5. Exit                        |" + "\n"
						 + "+--------------------------------+" );
		
		Scanner sc = new Scanner(System.in);
		
		int choice = 0;
		try {
			choice = sc.nextInt();
			if (choice != 1 && choice != 2 && choice != 3 && choice != 4 && choice != 5) {
				System.out.println("Please choose a number from below options");
				adminMethods();
			}
			else adminChoice(choice);
		}
		catch (InputMismatchException e) {
			
			System.out.println("Input type should be number");
			adminMethods();
		}
	}
	
	static void adminChoice(int choice) {
		
			switch(choice) {
				case 1 : {
					AddBusDetailsUseCase.AddBusInfo();
					adminMethods();
				}
				break;
				case 2 : {
					ConfirmationSeatsStatusUseCase.updateSeatStatus();
					adminMethods();
				}
				break;
				case 3 : {
					ViewAllTicketsUseCase.viewAllTicket();
					adminMethods();
				}
				break;
				case 4 : AdminOrCustomer();
				break;	
				case 5 : {
					System.out.println("Thank you ! Visit again");
					System.exit(0);
				}
			}
	}
	
	static void customerLoginOrRegister() {
		System.out.println( "+--------------------------------+" + "\n"
				                                + "| 1. Login to your Account       |" + "\n"
				                                + "| 2. Don't have Account? Sign Up |" + "\n"
				                                + "| 3. Back                        |" + "\n"
				                                + "| 4. Exit                        |" + "\n"
				                                + "+--------------------------------+");
		try {
			Scanner sc = new Scanner(System.in);
			int choice = sc.nextInt();
			
			if (choice == 1) {
				customerLogin();
			}
			else if (choice == 2) {
				customerSignup();
			}
			else if (choice == 3) {
				AdminOrCustomer();
			}
			else if (choice == 4) {
				System.out.println("Thank you ! Visit again");
				System.exit(0);
			}
			else {
				System.out.println("Please choose a number from below options");
				customerLoginOrRegister();
			}
		}
		catch (InputMismatchException e) {
			System.out.println("Input type should be number");
			customerLoginOrRegister();
		}
		
	}
	
	static void customerLogin() {
		Customer customer = CustomerLoginUseCase.CustomerLogin();
		
		if (customer == null) {
			customerLogin();
		}
		else {
			System.out.println("Login Successfull");
			customerMethods(customer);
		}
		
	}
	
	static void customerSignup() {
		boolean flag = CustomerRegisterationUseCase.customerRegistration();
		
		if (flag) {
			System.out.println("Login to your Account");
			customerLogin();
		}
		else {
			customerSignup();
		}
	}
	
	static void customerMethods(Customer customer) {
		System.out.println("+--------------------------------+" + "\n"
				 		 + "| 1. Book Bus Ticket             |" + "\n"
				         + "| 2. Cancel Bus Ticket           |" + "\n"
				         + "| 3. View Status of your Tickets |" + "\n"
				         + "| 4. Back                        |" + "\n"
				         + "| 5. Exit                        |" + "\n"
				         + "+--------------------------------+" );
		
		Scanner sc = new Scanner(System.in);
		
		int choice = 0;
		try {
			choice = sc.nextInt();
			if (choice != 1 && choice != 2 && choice != 3 && choice != 4 && choice != 5) {
				System.out.println("Please choose a number from below options");
				customerMethods(customer);
			}
			else customerChoice(choice, customer);
		}
		catch (InputMismatchException e) {
			
			System.out.println("Input type should be number");
			customerMethods(customer);
		}
	}
	
	static void customerChoice(int choice, Customer customer) {
		switch(choice) {
		case 1 : {
			BookTicketSourseToDestinationUseCase.bookTicketSourseToDestination(customer);
			customerMethods(customer);
		}
		break;
		case 2 : {
			TicketCancellationUseCase.cancelTicket(customer);
			customerMethods(customer);
		}
		break;
		case 3 : {
			ViewTicketUseCase.viewTicket(customer);
			customerMethods(customer);
		}
		break;
		case 4 : {
			customerLoginOrRegister();
		}
		case 5 : {
			System.out.println( "Thank you for the visiting  ! Visit again");
			System.exit(0);
		}
	}
	}
	

	public static void main(String[] args) {
		
		AdminOrCustomer();
				
	}	


}
