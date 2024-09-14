package com.springboot.bookingms.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.springboot.bookingms.dto.BookingInput;
import com.springboot.bookingms.model.Booking;
import com.springboot.bookingms.model.BusInventory;
import com.springboot.bookingms.model.Payment;
import com.springboot.bookingms.repo.BookingRepository;

@Service
public class BookingService {

	@Autowired
    private RestTemplate restTemplate;
	
	@Autowired
    private StreamBridge streamBridge;
	
	@Autowired
    private BookingRepository bookingRepository;


	public boolean checkSeatAvailability(Long busNumber, int numberOfSeats) {
        ResponseEntity<BusInventory> response = restTemplate.getForEntity(
            "http://inventoryms/inventory/" + busNumber, BusInventory.class);
        BusInventory inventory = response.getBody();
        return inventory != null && inventory.getAvailableSeats() >= numberOfSeats;
    }
    
	public Booking validateInput(BookingInput bookingInput) throws Exception{
		Booking booking=null;
		if (null!=bookingInput.getBookingDate() 
				&& null!=bookingInput.getBusNumber() 
				&& null!=bookingInput.getDestination()
				&& null!=bookingInput.getSource()
				&& bookingInput.getNumberOfSeats()>0) {
			//String string = bookingInput.getBookingDate();
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
			Date date = formatter.parse(bookingInput.getBookingDate());
			System.out.println(date);
			booking=new Booking();
			booking.setBookingDate(date);
			booking.setBusNumber(bookingInput.getBusNumber());
			booking.setDestination(bookingInput.getDestination());
			booking.setNumberOfSeats(bookingInput.getNumberOfSeats());
			booking.setSource(bookingInput.getSource());
			//booking.setStatus(null);
		}
		return booking;
	}
    
    public Booking createBooking(Booking booking) {
    	 booking.setBookingDate(new Date());
         booking.setStatus("PENDING");
         Booking savedBooking = bookingRepository.save(booking);
         streamBridge.send("bookingEvent-out-0", savedBooking);
         return savedBooking;
    }

    public void cancelBooking(Long id) {
        bookingRepository.deleteById(id);
    }
    public Booking getBooking(Long id) {
        return bookingRepository.findById(id).orElse(null);
    }
    @Bean
    public Consumer<BusInventory> processInvEvent() {
        return inventory -> {
            List<Booking> bookings = bookingRepository.findByBusNumberAndStatus(inventory.getBusNumber(), "PENDING");
            for (Booking booking : bookings) {
                booking.setStatus("CONFIRMED");
                bookingRepository.save(booking);
            }
        };
    }
    
    @Bean
    public Consumer<Payment> paymentFailed() {
        return event -> {
            Booking booking = bookingRepository.findById(event.getBookingNumber()).orElse(null);
            if (booking != null) {
                booking.setStatus("FAILED");
                bookingRepository.save(booking);
                // Notify inventory service to cancel the booking
                restTemplate.put("http://inventoryms/inventory/cancel", booking);
            }
        };
    }

    public void testMessage() {
    	Booking booking=new Booking();
    	booking.setBookingDate(new Date());
        booking.setStatus("PENDING");
       // Booking savedBooking = bookingRepository.save(booking);
        streamBridge.send("bookingEvent-out-0", booking);
        
    }
}
