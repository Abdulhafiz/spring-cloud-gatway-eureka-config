package com.abdulhafiz.cloud.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@SpringBootApplication(scanBasePackages = {"com.abdulhafiz.cloud.gateway"})
@EnableDiscoveryClient
public class CloudGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudGatewayApplication.class, args);
    }


   @Bean
    public RouteLocator fallbackCircuitBreaker(RouteLocatorBuilder builder) {

        return builder.routes()
                .route(r -> r.path("/order/**")
                        .filters(f -> f.circuitBreaker(config -> config
                                .setName("orderCircuitBreaker")
                                .setFallbackUri("forward:/fallback/orderFallBack")))
                        .uri("lb://ORDER-SERVICE"))

                .route(r -> r.path("/payment/**")
                        .filters(f -> f.circuitBreaker(config -> config
                                .setName("paymentCircuitBreaker")
                                .setFallbackUri("forward:/fallback/paymentFallBack")))
                        .uri("lb://PAYMENT-SERVICE"))
                .build();

    }

}
