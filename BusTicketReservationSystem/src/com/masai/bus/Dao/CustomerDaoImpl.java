package com.masai.bus.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

import com.masai.bus.exception.AdminException;
import com.masai.bus.exception.CustomerException;
import com.masai.bus.models.Customer;
import com.masai.bus.utility.DBUtil;


public class CustomerDaoImpl implements CustomerDao {

	@Override
	public String customerRegister(Customer customer) {
      String message = "Customer not Register";
		
		try(Connection conn = DBUtil.provideConnection()){
			
			PreparedStatement ps =  conn.prepareStatement("insert into customer(firstName, lastName, username, password, address, mobile) values (?,?,?,?,?,?)");
			
			
			ps.setString(1, customer.getFirstName());
			ps.setString(2, customer.getLastName());
			ps.setString(3, customer.getUsername());
			ps.setString(4, customer.getPassword());
			ps.setString(5, customer.getAddress());
			ps.setString(6, customer.getMobile());
			
			int x = ps.executeUpdate();
			
			if (x > 0) message = "Customer Register Successfull";
			
		}
		catch (SQLException e) {
			message = e.getMessage();
		}
		
		return message;
	}
//--------------------------------------------------------------------------------------------------------------------------------------------------------
	
	
	@Override
	public Customer customerLogin(String username, String password) throws CustomerException {
       Customer customer = null;
		
		try (Connection conn = DBUtil.provideConnection()){
			
			PreparedStatement ps = conn.prepareStatement("select * from customer where username = ? and password = ?");
			
			ps.setString(1, username);
			ps.setString(2, password);
			
			ResultSet rs =  ps.executeQuery();
			
			if (rs.next()) {
				int cId = rs.getInt("cId");
				String firstName = rs.getString("firstName");		
				String lastName = rs.getString("lastName");	
				String usernamee =  rs.getString("username");
				String passwordd = rs.getString("password");	
				String address = rs.getString("address");			
				String mobile = rs.getString("mobile");
				
				customer = new Customer(cId, firstName, lastName,usernamee, passwordd, address, mobile);
				
			}
			else {
				throw new CustomerException("Invalid username or password");
				
			}
			
		}
		catch (SQLException e) {
			throw new CustomerException(e.getMessage());
		}
		
		return customer;
	}
//-------------------------------------------------------------------------------------------------------------------------------------------------------
	@Override
	public String bookTicketSourseToDestination(String bName, int cId, int noseat) throws AdminException {
       String message = "Ticket Booking fail";
		
		try (Connection conn = DBUtil.provideConnection()){
			
			PreparedStatement ps = conn.prepareStatement("select * from busInfo where bName = ?");
			ps.setString(1, bName);
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				
				int busNo = rs.getInt("busNo");
				int totalSeats = rs.getInt("totalSeats");
				int availSeats = rs.getInt("availSeats");
				Date departure = rs.getDate("departure");
				int ticketCharge = rs.getInt("TicketCharge");
				
				PreparedStatement ps1 = conn.prepareStatement("select datediff(?,current_date()) as date");
				ps1.setDate(1,departure);
				
				ResultSet rs1 = ps1.executeQuery();
				int days = 0;
				if (rs1.next()) {
					days = rs1.getInt("date");
				}
				
				if (days <= 0) {
					throw new AdminException("Booking is not available for this date");
				}
				else if (availSeats >= noseat) {
					int seatFrom = totalSeats - availSeats + 1;
					int seatTo = seatFrom + noseat -1;
					ticketCharge = ticketCharge * noseat;
					
					PreparedStatement ps2 = conn.prepareStatement("insert into booking(cusId, busNo, seatFrom, seatTo) values (?, ?, ?, ?)");
					ps2.setInt(1, cId);
					ps2.setInt(2, busNo);
					ps2.setInt(3, seatFrom);
					ps2.setInt(4, seatTo);
					
					int x = ps2.executeUpdate();

					if (x > 0) {
						
						PreparedStatement ps3 = conn.prepareStatement("update bus set availseats = ? where busNo = ?");
						availSeats = availSeats - noseat;
						ps3.setInt(1, availSeats);
						ps3.setInt(2, busNo);
						int y = ps3.executeUpdate();
						
						if (y <= 0) throw new AdminException("Available Seat is not updated");
						
						
						System.out.println("--------------------------------------------" + "\n"
										                           + "Customer Id is : " + cId + "\n"
																   + "Bus No is : " + busNo + "\n"
																   + "Seat No is from : " + seatFrom + " to " + seatTo + "\n"
																   + "Bus TicketCharge is : " + ticketCharge+ "\n"
																   + "Booking yet to be confirm by Adminstrator" + "\n" 
																   + "---------------------------------------------");
						
						 message = "Ticket Booked Successfully";
					}
				
				}
	
			}
			else {
				throw new AdminException(bName +"--This Bus is not available");
			}
			
		}
		catch (SQLException e) {
			throw new AdminException(e.getMessage());
		}
		
		return message;
	}
//----------------------------------------------------------------------------------------------------------------------------------------------------------
	@Override
	public String cancelTicket(String bName, int cId) throws AdminException {
      String message = "Ticket cancellation failed";
		
		try (Connection conn = DBUtil.provideConnection()){
				
				PreparedStatement ps = conn.prepareStatement("select * from bus where bName = ?");
				ps.setString(1, bName);
				
				ResultSet rs = ps.executeQuery();
				
				if (rs.next()) {
					
					int busNo = rs.getInt("busNo");
					int availSeats = rs.getInt("availSeats");
					
					PreparedStatement ps1 = conn.prepareStatement("select * from TicketBooking where busNo = ? and cId = ?");
					ps1.setInt(1, busNo);
					ps1.setInt(2, cId);
					
					ResultSet rs1 = ps1.executeQuery();
					boolean flag = false;
					int count = 0;
					
					while (rs1.next()) {
						flag = true;
						int seatFrom = rs1.getInt("seatFrom");
						int seatTo = rs1.getInt("seatTo");
						count += seatTo - seatFrom + 1;
					}
					
				    if (flag) {
						
						PreparedStatement ps2 = conn.prepareStatement("delete from TicketBooking where busNo = ? and cId = ?");
						ps2.setInt(1, busNo);
						ps2.setInt(2, cId);
						
						int x = ps2.executeUpdate();
						if (x > 0) {
							
							PreparedStatement ps3 = conn.prepareStatement("update busInfo set availseats = ? where busNo = ?");
							availSeats = availSeats + count;
							ps3.setInt(1, availSeats);
							ps3.setInt(2, busNo);
							int y = ps3.executeUpdate();
							
							if (y <= 0) throw new AdminException("Available Seat is not updated");
							
							 message = "Ticket cancelled Successfully";
						}
					
					}
				    else message = "No booking found";
		
				}
				else {
					throw new AdminException("Bus with " + bName + " is not available");
				}
				
			}
			catch (SQLException e) {
				throw new AdminException(e.getMessage());
			}
		
		return message;
	}

	//-----------------------------------------------------------------------------------------------------------------------------------------------
	@Override
	public void viewTickets(int cId) {
         boolean flag = false;
		
		try(Connection conn = DBUtil.provideConnection()){
			PreparedStatement ps1 = conn.prepareStatement("select * from TicketBooking where cId = ?");
			ps1.setInt(1, cId);
			
			ResultSet rs1 = ps1.executeQuery();
			
			while (rs1.next()) {
				flag = true;
				System.out.println("---------------------------------------------" );
				System.out.println("Bus Id : " + rs1.getInt("bId"));
				System.out.println("Bus No : " + rs1.getInt("busNo"));
				System.out.println("Total tickets : " + (rs1.getByte("seatTo") - rs1.getInt("seatFrom") + 1));
				if (rs1.getBoolean("status")) System.out.println("Status : Booked" );
				else System.out.println("Status : Pending");
				
				System.out.println("----------------------------------------------");
			}
			
			if (flag == false) System.out.println( "No tickets found");
		}
		catch (SQLException s){
			System.out.println(s.getMessage());
		}
		
	}

		
	}


