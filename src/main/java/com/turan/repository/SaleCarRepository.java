package com.turan.repository;

import com.turan.model.SaleCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleCarRepository extends JpaRepository<SaleCar,Long> {
}
