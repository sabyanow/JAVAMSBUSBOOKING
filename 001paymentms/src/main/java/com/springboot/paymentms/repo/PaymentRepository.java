package com.springboot.paymentms.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.paymentms.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}