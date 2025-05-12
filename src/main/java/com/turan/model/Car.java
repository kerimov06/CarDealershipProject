package com.turan.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "car")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "license_plate")
    private String licensePlate;

     @Column(name = "brand")
     private String brand;

     @Column(name = "model")
     private String model;

     @Column(name = "price")
     private BigDecimal price;
     @Column(name = "currency_type")
     private CurrencyType currencyType;

     @Column(name = "damage_price")
     private BigDecimal damagePrice;

     @Column(name = "car_status_type")
     @Enumerated(EnumType.STRING)
     private CarStatusType carStatusType;
}
