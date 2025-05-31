package com.turan.controller;

import com.turan.dto.DtoCar;
import com.turan.dto.DtoCarIU;
import com.turan.model.ResponseEntity;

public interface ICarController {
    public ResponseEntity<DtoCar> saveCar(DtoCarIU saveCar);
    public ResponseEntity<DtoCar> getCar(Long id);
    public ResponseEntity<DtoCar>updateCar(Long id , DtoCarIU updateCar);
    public void deleteCar(Long id);
}
