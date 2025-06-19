package com.turan.service;

import com.turan.dto.DtoSaleCar;
import com.turan.dto.DtoSaleCarIU;

public interface ISaleCarService {

     public DtoSaleCar saveSaleCar(DtoSaleCarIU saveSaleCar);
     public DtoSaleCar getSaleCarById(Long id);
     public DtoSaleCar updateSaleCar(Long id , DtoSaleCarIU updateSaleCar);
     public void deleteSaleCar(Long id);
}
