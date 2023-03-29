package com.abdulhafiz.ps.api.controller;

import com.abdulhafiz.ps.api.entity.Payment;
import com.abdulhafiz.ps.api.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;


    @PostMapping("/doPayment")
    public Payment doPayment (@RequestBody Payment payment){
        return paymentService.doPayment(payment);
    }

    @GetMapping("/{orderId}")
    public Payment findPaymentHistoryByOrderId (@PathVariable int orderId){
        return paymentService.findPaymentHistoryByOrderId(orderId);
    }

}
