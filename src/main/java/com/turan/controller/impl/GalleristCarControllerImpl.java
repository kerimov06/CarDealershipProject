package com.turan.controller.impl;

import com.turan.controller.IGalleristCarController;
import com.turan.dto.DtoGalleristCar;
import com.turan.dto.DtoGalleristCarIU;
import com.turan.service.IGalleristCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}
