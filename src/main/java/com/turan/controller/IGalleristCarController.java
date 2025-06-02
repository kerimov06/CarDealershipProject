package com.turan.controller;

import com.turan.dto.DtoGalleristCar;
import com.turan.dto.DtoGalleristCarIU;

public interface IGalleristCarController {

    public DtoGalleristCar saveGalleristCar(DtoGalleristCarIU saveGalleristCar);
    public DtoGalleristCar getGalleristCarById(Long id);
}
