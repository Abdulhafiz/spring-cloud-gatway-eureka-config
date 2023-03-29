package com.abdulhafiz.os.api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Entity
@Table(name ="ORDER_TB")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @jakarta.persistence.Id
    @Id
    private int id;
    private String name;
    private int qty;
    private double price;
}
