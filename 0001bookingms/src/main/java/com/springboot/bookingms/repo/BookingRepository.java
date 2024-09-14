package com.springboot.bookingms.repo;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.bookingms.model.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {
	
	List<Booking> findByBusNumberAndStatus(Long busNumber,String status);
}

