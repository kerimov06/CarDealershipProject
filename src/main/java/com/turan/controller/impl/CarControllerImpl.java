package com.turan.controller.impl;

import com.turan.controller.ICarController;
import com.turan.dto.DtoCar;
import com.turan.dto.DtoCarIU;
import com.turan.model.ResponseEntity;
import com.turan.service.ICarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/api/car")
public class CarControllerImpl implements ICarController {

    @Autowired
    private ICarService carService;

    @PostMapping("/saveCar")
    @Override
    public ResponseEntity<DtoCar> saveCar(@RequestBody DtoCarIU saveCar) {
        return ResponseEntity.ok(carService.saveCar(saveCar));
    }

    @GetMapping("/getCar/{id}")
    @Override
    public ResponseEntity<DtoCar> getCar(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(carService.getCar(id));
    }

    @PostMapping("/updateCar/{id}")
    @Override
    public ResponseEntity<DtoCar> updateCar(@PathVariable(name = "id") Long id, @RequestBody DtoCarIU updateCar) {
        return ResponseEntity.ok(carService.updateCar(id,updateCar));
    }
    @DeleteMapping("deleteCar/{id}")
    @Override
    public void deleteCar(@PathVariable(name = "id") Long id) {
         carService.deleteCar(id);

    }
}
