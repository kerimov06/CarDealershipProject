package com.turan.service;

import com.turan.dto.DtoGalleristCar;
import com.turan.dto.DtoGalleristCarIU;

public interface IGalleristCarService {

    public DtoGalleristCar saveGalleristCar(DtoGalleristCarIU galleristCarIU);
}
