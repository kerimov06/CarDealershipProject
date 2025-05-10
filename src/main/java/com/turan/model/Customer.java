package com.turan.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "customer")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "birth_of_date")
    private Date birthOfDate;
    @Column(name = "tckn")
    private String tckn;
    @OneToOne(cascade = CascadeType.ALL , orphanRemoval = true)
    private Account account;
    @OneToOne(cascade = CascadeType.ALL , orphanRemoval = true)
    private Address address;
}
