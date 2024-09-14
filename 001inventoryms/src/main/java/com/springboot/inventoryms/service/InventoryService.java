package com.springboot.inventoryms.service;

import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import com.springboot.inventoryms.model.Booking;
import com.springboot.inventoryms.model.BusInventory;
import com.springboot.inventoryms.model.Payment;
import com.springboot.inventoryms.repo.BusInventoryRepository;

@Service
public class InventoryService {

    @Autowired
    private BusInventoryRepository busInventoryRepository;
    
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private StreamBridge streamBridge;
    public BusInventory getInventory(Long busNumber) {
        return busInventoryRepository.findById(busNumber).orElse(null);
    }

    public BusInventory updateInventory(Long busNumber, BusInventory busInventory) {
        if (busInventoryRepository.existsById(busNumber)) {
            busInventory.setBusNumber(busNumber);
            return busInventoryRepository.save(busInventory);
        }
        return null;
    }
    
    // Function to handle the incoming payment event
    @Bean
    public Consumer<Payment> processPaymentEvent() {
        return payment -> {
            // Fetch the corresponding booking
            Booking booking = restTemplate.getForObject("http://bookingms/bookings/" + payment.getBookingNumber(), Booking.class);
            if (booking != null) {
                // Update the bus inventory based on the booking details
                BusInventory inventory = busInventoryRepository.findById(booking.getBusNumber()).orElse(null);
                if (inventory != null) {
                    inventory.setAvailableSeats(inventory.getAvailableSeats() - booking.getNumberOfSeats());
                    inventory=busInventoryRepository.save(inventory);
                    
                    streamBridge.send("inventoryEvent-out-0", inventory);
                }
            }
        };
    }
    
    @PutMapping("/inventory/cancel")
    public void cancelInventory(@RequestBody Booking booking) {
        BusInventory inventory = busInventoryRepository.findById(booking.getBusNumber()).orElse(null);
        if (inventory != null) {
            inventory.setAvailableSeats(inventory.getAvailableSeats() + booking.getNumberOfSeats());
            busInventoryRepository.save(inventory);
        }
    }
}
