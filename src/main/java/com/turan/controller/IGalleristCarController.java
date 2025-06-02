package com.turan.controller;

import com.turan.dto.DtoGalleristCar;
import com.turan.dto.DtoGalleristCarIU;

import java.util.List;

public interface IGalleristCarController {

    public DtoGalleristCar saveGalleristCar(DtoGalleristCarIU saveGalleristCar);
    public DtoGalleristCar getGalleristCarById(Long id);
    public DtoGalleristCar updateGalleristCar(Long id,DtoGalleristCarIU updateGalleristCar);
    public void deleteGalleristCar(Long id);
    public List<DtoGalleristCar> getAllGalleristCar();
}
