package com.turan.controller.impl;

import com.turan.controller.ISaleCarController;
import com.turan.dto.DtoSaleCar;
import com.turan.dto.DtoSaleCarIU;
import com.turan.service.ISaleCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
