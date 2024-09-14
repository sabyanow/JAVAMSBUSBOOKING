package com.springboot.bookingms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.bookingms.dto.BookingInput;
import com.springboot.bookingms.model.Booking;
import com.springboot.bookingms.service.BookingService;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;
    @GetMapping("/")
    public String hello() {
    	System.out.println("hello from booking ms");
    	return "hello";
    }

    @PostMapping("/book")
    public Booking createBooking(@RequestBody BookingInput bookingInput) throws Exception{
      //  return bookingService.createBooking(booking);

    	Booking booking=bookingService.validateInput(bookingInput);
    	if (bookingService.checkSeatAvailability(booking.getBusNumber(), booking.getNumberOfSeats())) {
      //  if (true) { 
    	booking.setStatus("PENDING");
            return bookingService.createBooking(booking);
        } else {
            throw new RuntimeException("Not enough seats available");
        }
    }
    
  
    @DeleteMapping("/{id}")
    public void cancelBooking(@PathVariable Long id) {
        bookingService.cancelBooking(id);
    }
    @GetMapping("/{id}")
    public Booking getBooking(@PathVariable Long id) {
        return bookingService.getBooking(id);
    }
    @GetMapping("/test")
    public void test() {
    	bookingService.testMessage();
    }
}

