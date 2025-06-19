package com.turan.controller.impl;

import com.turan.controller.ISaleCarController;
import com.turan.dto.DtoSaleCar;
import com.turan.dto.DtoSaleCarIU;
import com.turan.service.ISaleCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/api/saleCar")
public class SaleCarController implements ISaleCarController {

    @Autowired
    private ISaleCarService saleCarService;

    @PostMapping("/saveSaleCar")
    @Override
    public DtoSaleCar saveSaleCar(@RequestBody DtoSaleCarIU saveSaleCar) {
        return saleCarService.saveSaleCar(saveSaleCar);
    }

    @GetMapping("/getSaleCarById/{id}")
    @Override
    public DtoSaleCar getSaleCarById(@PathVariable(name = "id") Long id) {
        return saleCarService.getSaleCarById(id);
    }

    @PutMapping("/updateSaleCarById/{id}")
    @Override
    public DtoSaleCar updateSaleCar(@PathVariable(name = "id") Long id, @RequestBody DtoSaleCarIU updateSaleCar) {
        return saleCarService.updateSaleCar(id,updateSaleCar);
    }

    @DeleteMapping("/deleteSaleCarById/{id}")
    @Override
    public void deleteSaleCar(@PathVariable(name = "id") Long id) {
        saleCarService.deleteSaleCar(id);

    }


}
