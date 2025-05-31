package com.turan.service;

import com.turan.dto.DtoGallerist;
import com.turan.dto.DtoGalleristCar;
import com.turan.dto.DtoGalleristIU;

public interface IGalleristService {

     public DtoGallerist saveGallerist(DtoGalleristIU saveGallerist);
     public DtoGallerist getGalleristById(Long id);
     public DtoGallerist updateGallerist(Long id, DtoGalleristIU updateGallerist);
     public void deleteGallerist(Long id);
}
