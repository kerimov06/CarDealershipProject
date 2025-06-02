package com.turan.controller.impl;

import com.turan.controller.IGalleristCarController;
import com.turan.dto.DtoGalleristCar;
import com.turan.dto.DtoGalleristCarIU;
import com.turan.service.IGalleristCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/api/galleristCar")
public class GalleristCarControllerImpl implements IGalleristCarController {

    @Autowired
    private IGalleristCarService galleristCarService;

    @PostMapping("/saveGalleristCar")
    @Override
    public DtoGalleristCar saveGalleristCar(@RequestBody DtoGalleristCarIU saveGalleristCar) {
        return galleristCarService.saveGalleristCar(saveGalleristCar);
    }

    @GetMapping("/getGalleristById/{id}")
    @Override
    public DtoGalleristCar getGalleristCarById(@PathVariable(name = "id") Long id) {
        return galleristCarService.getGalleristCarById(id);
    }

    @PutMapping("/updateGalleristCar/{id}")
    @Override
    public DtoGalleristCar updateGalleristCar(@PathVariable(name = "id") Long id, @RequestBody DtoGalleristCarIU updateGalleristCar) {
        return galleristCarService.updateGalleristCar(id,updateGalleristCar);
    }

    @DeleteMapping("/deleteGalleristCarById/{id}")
    @Override
    public void deleteGalleristCar(@PathVariable(name = "id") Long id) {
         galleristCarService.deleteGalleristCar(id);
    }

    @GetMapping("/getAllGalleristCar")
    @Override
    public List<DtoGalleristCar> getAllGalleristCar() {
        return galleristCarService.getAllGalleristCar();
    }
}
