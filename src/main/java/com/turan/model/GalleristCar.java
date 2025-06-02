package com.turan.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "gallerist_car", uniqueConstraints =
        {@UniqueConstraint(columnNames = {"gallerist_id", "car_id"},name = "uq_gallerist_car")})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GalleristCar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Gallerist gallerist;
    @ManyToOne(cascade = CascadeType.ALL)
    private Car car;
}
