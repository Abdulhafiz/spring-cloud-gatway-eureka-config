package com.abdulhafiz.ps.api.repository;

import com.abdulhafiz.ps.api.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    Payment findByOrderId(int orderId);

}
