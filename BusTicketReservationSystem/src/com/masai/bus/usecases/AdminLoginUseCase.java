package com.masai.bus.usecases;


import java.util.Scanner;

import com.masai.bus.Dao.AdminDao;
import com.masai.bus.Dao.AdminDaoImpl;


public class AdminLoginUseCase {
	
	public static boolean AdminLogin() {
		
      Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter username");
		String username = sc.next();
		
		System.out.println("Enter password");
		String password = sc.next();
		
		AdminDao dao = new AdminDaoImpl();
		String result =  dao.adminLogin(username, password);
		
		if (result.equals("Login Successfull")){
			System.out.println(result);
			return true;
		}
		else {
			System.out.println(result);
			return false;
		}

	}

}
