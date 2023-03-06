package com.edubridge.hms.model;

public class Hotel {
	@Override
	public String toString() {
		return "Hotel [hotelid=" + hotelid + ", hotelName=" + hotelName + ", numberOfRooms=" + numberOfRooms
				+ ", AvailableRooms=" + AvailableRooms + "]";
	}
	int hotelid;
	String hotelName;
	int numberOfRooms;
	int AvailableRooms;
	public Hotel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getHotelid() {
		return hotelid;
	}
	public void setHotelid(int hotelid) {
		this.hotelid = hotelid;
	}
	public String getHotelName() {
		return hotelName;
	}
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	public int getNumberOfRooms() {
		return numberOfRooms;
	}
	public void setNumberOfRooms(int numberOfRooms) {
		this.numberOfRooms = numberOfRooms;
	}
	public int getAvailableRooms() {
		return AvailableRooms;
	}
	public void setAvailableRooms(int availableRooms) {
		AvailableRooms = availableRooms;
	}
}
