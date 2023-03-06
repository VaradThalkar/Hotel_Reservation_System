package com.edubridge.hms.model;

public class Reservation {
	@Override
	public String toString() {
		return "Reservation [ReservationId=" + ReservationId + ", userId=" + userId + ", hotelId=" + hotelId
				+ ", noOfRooms=" + noOfRooms + ", fromDate=" + fromDate + ", toDate=" + toDate + ", status=" + status
				+ "]";
	}
	int ReservationId;
	int userId;
	int hotelId;
	int noOfRooms;
	String fromDate;
	String toDate;
	String status;
	public Reservation() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getReservationId() {
		return ReservationId;
	}
	public void setReservationId(int reservationId) {
		ReservationId = reservationId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getHotelId() {
		return hotelId;
	}
	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
	}
	public int getNoOfRooms() {
		return noOfRooms;
	}
	public void setNoOfRooms(int noOfRooms) {
		this.noOfRooms = noOfRooms;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setHotelId() {
		// TODO Auto-generated method stub
		
	}
	
}
