package com.turan.service;

import com.turan.dto.DtoCar;
import com.turan.dto.DtoCarIU;

public interface ICarService {

     public DtoCar saveCar(DtoCarIU saveCar);
     public DtoCar getCar(Long id);
     public DtoCar updateCar(Long id , DtoCarIU updateCar);
     public void deleteCar(Long id);
}
