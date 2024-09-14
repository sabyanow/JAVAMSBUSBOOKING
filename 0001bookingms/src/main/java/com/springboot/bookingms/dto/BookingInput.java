package com.springboot.bookingms.dto;

public class BookingInput {

	    private Long busNumber;
	    private String bookingDate;
	    private String source;
	    private String destination;
	    private int numberOfSeats;
		public Long getBusNumber() {
			return busNumber;
		}
		public void setBusNumber(Long busNumber) {
			this.busNumber = busNumber;
		}
		public String getBookingDate() {
			return bookingDate;
		}
		public void setBookingDate(String bookingDate) {
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
	    
	
}
