package com.springboot.paymentms.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;



public class Booking {
    private Long bookingNumber;
    private Long busNumber;
    private Date bookingDate;
    private String source;
    private String destination;
    private int numberOfSeats;
    private String status;

    

	public Long getBookingNumber() {
		return bookingNumber;
	}

	public void setBookingNumber(Long bookingNumber) {
		this.bookingNumber = bookingNumber;
	}

	public Long getBusNumber() {
		return busNumber;
	}

	public void setBusNumber(Long busNumber) {
		this.busNumber = busNumber;
	}

	public Date getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public int getNumberOfSeats() {
		return numberOfSeats;
	}

	public void setNumberOfSeats(int numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Booking [bookingNumber=" + bookingNumber + ", busNumber=" + busNumber + ", bookingDate=" + bookingDate
				+ ", source=" + source + ", destination=" + destination + ", numberOfSeats=" + numberOfSeats
				+ ", status=" + status + "]";
	}
	
}
