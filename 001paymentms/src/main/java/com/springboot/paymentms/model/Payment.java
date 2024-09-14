package com.springboot.paymentms.model;


import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentNumber;
    private Long bookingNumber;
    private Date dateOfPayment;
    // getters and setters
	public Long getPaymentNumber() {
		return paymentNumber;
	}
	public void setPaymentNumber(Long paymentNumber) {
		this.paymentNumber = paymentNumber;
	}
	public Long getBookingNumber() {
		return bookingNumber;
	}
	public void setBookingNumber(Long bookingNumber) {
		this.bookingNumber = bookingNumber;
	}
	public Date getDateOfPayment() {
		return dateOfPayment;
	}
	public void setDateOfPayment(Date dateOfPayment) {
		this.dateOfPayment = dateOfPayment;
	}
    
}
