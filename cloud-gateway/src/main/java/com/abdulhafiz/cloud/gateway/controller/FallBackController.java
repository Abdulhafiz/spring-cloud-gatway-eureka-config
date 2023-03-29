package com.abdulhafiz.cloud.gateway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(("/fallback"))
public class FallBackController {

  /*  @PostMapping("/order")
    public ResponseEntity<String> orderServiceFallBack() {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Order Service is broken. Please try again later...");
    }*/

    @RequestMapping("/orderFallBack")
    public Mono<String> orderServiceFallBack() {
        return Mono.just("Order Service is taking too long to respond or is down. Please try again later");
    }
    @GetMapping("/paymentFallBack")
    public ResponseEntity<String> paymentServiceFallBack() {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Payment Service is broken. Please try again later...");
    }
}
