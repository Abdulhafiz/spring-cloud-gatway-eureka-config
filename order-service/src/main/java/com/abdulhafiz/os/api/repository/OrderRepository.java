package com.abdulhafiz.os.api.repository;

import com.abdulhafiz.os.api.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}
