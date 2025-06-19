package com.turan.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "gallerist")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class    Gallerist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
     private String firstName;
     @Column(name = "last_name")
     private String lastName;

     @OneToOne(cascade = CascadeType.ALL , orphanRemoval = true)
     private Address address;
}
