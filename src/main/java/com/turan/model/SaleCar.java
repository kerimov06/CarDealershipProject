package com.turan.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.action.internal.OrphanRemovalAction;

@Entity
@Table(name = "sale_car", uniqueConstraints =
        {@UniqueConstraint(columnNames = {"gallerist_id" , "car_id" , "customer_id"}, name = "uq_gallerist_car_customer")})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleCar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Gallerist gallerist;
     @ManyToOne(cascade = CascadeType.ALL)
     private Car car;
     @ManyToOne(cascade = CascadeType.ALL)
     private Customer customer;
}
