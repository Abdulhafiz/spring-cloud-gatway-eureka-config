package com.abdulhafiz.os.api.service;

import com.abdulhafiz.os.api.common.Payment;
import com.abdulhafiz.os.api.common.TransactionRequest;
import com.abdulhafiz.os.api.common.TransactionResponse;
import com.abdulhafiz.os.api.entity.Order;
import com.abdulhafiz.os.api.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RefreshScope
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    @Lazy
    private RestTemplate restTemplate;

    @Value("${microservice.payment-service.endpoints.endpoint.uri}")
    private String END_POINT_URL;

    public TransactionResponse saveOrder(TransactionRequest request) {

        Order order = request.getOrder();

        Payment payment = request.getPayment();
        payment.setOrderId(order.getId());
        payment.setAmount(order.getPrice());
        Payment paymentResponse = restTemplate.postForObject(END_POINT_URL, payment, Payment.class);
        String response = paymentResponse.getPaymentStatus().equals("success") ? "payment processing successful and order placed" : "there is failure in payment api";

        order = orderRepository.save(order);

        return new TransactionResponse(order, paymentResponse.getAmount(), paymentResponse.getTransactionId(), response);
    }
}
