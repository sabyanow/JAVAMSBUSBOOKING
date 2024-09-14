package com.springboot.bookingms.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;


public class BusInventory {

    private Long busNumber;
    private int availableSeats;
    private Date lastUpdatedDate;
    // getters and setters
	public Long getBusNumber() {
		return busNumber;
	}
	public void setBusNumber(Long busNumber) {
		this.busNumber = busNumber;
	}
	public int getAvailableSeats() {
		return availableSeats;
	}
	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}
	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}
	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}
    
    
}
