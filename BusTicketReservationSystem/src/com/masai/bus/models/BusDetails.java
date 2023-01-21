package com.masai.bus.models;

public class BusDetails {

 private int busNo;
	 
	 private String bName;
	 
	 private String routeFrom;
	 
	 private String routeTo;
	 
	 private String bType;
	 
	 private String arrival;
	 
	 private String departure;
	 
	 private int totalSeats;
	 
	 private int availSeats;
	 
	 private int TicketCharge;

	public BusDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BusDetails(int busNo, String bName, String routeFrom, String routeTo, String bType, String arrival,
			String departure, int totalSeats, int availSeats, int ticketCharge) {
		super();
		this.busNo = busNo;
		this.bName = bName;
		this.routeFrom = routeFrom;
		this.routeTo = routeTo;
		this.bType = bType;
		this.arrival = arrival;
		this.departure = departure;
		this.totalSeats = totalSeats;
		this.availSeats = availSeats;
		TicketCharge = ticketCharge;
	}

	public int getBusNo() {
		return busNo;
	}

	public void setBusNo(int busNo) {
		this.busNo = busNo;
	}

	public String getbName() {
		return bName;
	}

	public void setbName(String bName) {
		this.bName = bName;
	}

	public String getRouteFrom() {
		return routeFrom;
	}

	public void setRouteFrom(String routeFrom) {
		this.routeFrom = routeFrom;
	}

	public String getRouteTo() {
		return routeTo;
	}

	public void setRouteTo(String routeTo) {
		this.routeTo = routeTo;
	}

	public String getbType() {
		return bType;
	}

	public void setbType(String bType) {
		this.bType = bType;
	}

	public String getArrival() {
		return arrival;
	}

	public void setArrival(String arrival) {
		this.arrival = arrival;
	}

	public String getDeparture() {
		return departure;
	}

	public void setDeparture(String departure) {
		this.departure = departure;
	}

	public int getTotalSeats() {
		return totalSeats;
	}

	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}

	public int getAvailSeats() {
		return availSeats;
	}

	public void setAvailSeats(int availSeats) {
		this.availSeats = availSeats;
	}

	public int getTicketCharge() {
		return TicketCharge;
	}

	public void setTicketCharge(int ticketCharge) {
		TicketCharge = ticketCharge;
	}

	@Override
	public String toString() {
		return "BusDetails [busNo=" + busNo + ", bName=" + bName + ", routeFrom=" + routeFrom + ", routeTo=" + routeTo
				+ ", bType=" + bType + ", arrival=" + arrival + ", departure=" + departure + ", totalSeats="
				+ totalSeats + ", availSeats=" + availSeats + ", TicketCharge=" + TicketCharge + "]";
	}
	 
	 

}
