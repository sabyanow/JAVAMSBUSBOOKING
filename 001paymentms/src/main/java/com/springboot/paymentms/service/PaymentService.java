package com.springboot.paymentms.service;



import java.util.Date;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.springboot.paymentms.model.Booking;
import com.springboot.paymentms.model.Payment;
import com.springboot.paymentms.repo.PaymentRepository;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private StreamBridge streamBridge;

    //@StreamListener("bookingEvent-in-0")
   

    public Payment processPayment(Payment payment) {
        return paymentRepository.save(payment);
    }
    @Bean
    public Consumer<Booking> processBookingEvent() {
        return booking -> {
            // Create and save the payment entity
            Payment payment = new Payment();
            payment.setBookingNumber(booking.getBookingNumber());
            payment.setDateOfPayment(new Date());
            payment=paymentRepository.save(payment);
            // Send the payment event to the output destination
            streamBridge.send("paymentEvent-out-0", payment);
        };
    }
    
   
    
    
}
