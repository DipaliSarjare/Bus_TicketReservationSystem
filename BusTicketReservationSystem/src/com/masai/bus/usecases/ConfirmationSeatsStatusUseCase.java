package com.masai.bus.usecases;

import java.util.Scanner;

import com.masai.bus.Dao.AdminDao;
import com.masai.bus.Dao.AdminDaoImpl;

public class ConfirmationSeatsStatusUseCase {

public static void updateSeatStatus() {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter customer Id :");
		int cusId = sc.nextInt();
		
		AdminDao dao = new AdminDaoImpl();
		
		String result = dao.confirmationSeatsStatus(cusId);
		boolean flag = true;
		
		for (int i = 0; i < result.length(); i++) {
			if (result.charAt(i) == 'n') flag = false;
		}
	
		if (flag) System.out.println(result);
		else System.out.println(result);
		
	}
}
