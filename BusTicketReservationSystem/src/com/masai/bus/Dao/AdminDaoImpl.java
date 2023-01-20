package com.masai.bus.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.masai.bus.models.BusDetails;
import com.masai.bus.utility.DBUtil;

public  class AdminDaoImpl implements AdminDao {

	@Override
	public String adminLogin(String username, String password) {
    String message = "Invalid username or password";
		
		if (username.equals(AdminDao.username) && password.equals(AdminDao.password)) {
			 message = "Login Successfull";
		}
		
		return message;
	}

	@Override
	public String addBusDetails(BusDetails bus) {
		String message="Bus Details is not Added";
		
		try (Connection conn=DBUtil.provideConnection()){
			
		PreparedStatement ps=conn.prepareStatement("insert into busInfo values (?,?,?,?,?,?,?,?,?,?)");
		
		ps.setInt(1, bus.getBusNo());
		ps.setString(2, bus.getbName());
		ps.setString(3, bus.getRouteFrom());
		ps.setString(4, bus.getRouteTo());
		ps.setString(5, bus.getbType());
		ps.setString(6, bus.getArrival());
		ps.setString(7, bus.getDeparture());
		ps.setInt(8,bus.getTotalSeats());
		ps.setInt(9, bus.getAvailSeats());
		ps.setInt(10, bus.getTicketCharge());
		
		int x = ps.executeUpdate();
		
		if (x > 0)  message = "Bus Details added Successfully";
		
		} catch (SQLException e) {
			message=e.getMessage();
		}
		
		return message;
	}

	@Override
	public String confirmationSeatsStatus(int cId) {
		
       String message = "Seat not  confirm for customer Id : " + cId;
		
		try(Connection conn = DBUtil.provideConnection()){
			PreparedStatement ps = conn.prepareStatement("update TicketBooking set status = true where cId = ?");
			ps.setInt(1, cId);
			
			int x = ps.executeUpdate();
			if (x > 0) message = "Seat successfully Confirm for customer Id : " + cId;
			
		}
		catch (SQLException e) {
			message = e.getMessage();
		}
		
		return message;
	}

	@Override
	public void viewAllTickets() {
		
	boolean flag = false;
		
		try(Connection conn = DBUtil.provideConnection()){
			PreparedStatement ps1 = conn.prepareStatement("select * from TicketBooking");
			
			ResultSet rs1 = ps1.executeQuery();
			
			while (rs1.next()) {
				flag = true;
				
				System.out.println("----------------------------------------------------");
				System.out.println("Bus Id : " + rs1.getInt("bId"));
				System.out.println("Bus No : " + rs1.getInt("busNo"));
				System.out.println("Total tickets : " + (rs1.getInt("seatTo") - rs1.getInt("seatFrom") + 1));
				if (rs1.getInt("status") == 1) System.out.println("Status : Booked");
				else System.out.println("Status : Pending");
				
				System.out.println("----------------------------------------------------");
			}
			
			if (flag == false) System.out.println("No tickets found");
		}
		catch (SQLException s){
			System.out.println(s.getMessage());
		}
		
	}

		

}
