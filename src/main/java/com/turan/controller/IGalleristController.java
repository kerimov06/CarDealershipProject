package com.turan.controller;

import com.turan.dto.DtoGallerist;
import com.turan.dto.DtoGalleristIU;
import com.turan.model.ResponseEntity;

public interface IGalleristController {

     public ResponseEntity<DtoGallerist> saveGallerist(DtoGalleristIU saveGallerist);
     public ResponseEntity<DtoGallerist> getGalleristById(Long id);
     public ResponseEntity<DtoGallerist> updateGallerist(Long id , DtoGalleristIU updateGallerist);
}
