package com.turan.controller.impl;

import com.turan.controller.IGalleristController;
import com.turan.dto.DtoGallerist;
import com.turan.dto.DtoGalleristIU;
import com.turan.model.ResponseEntity;
import com.turan.service.IGalleristService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/api/gallerist")
public class GalleristControllerImpl implements IGalleristController {

    @Autowired
    private IGalleristService galleristService;

    @PostMapping("/saveGallerist")
    @Override
    public ResponseEntity<DtoGallerist> saveGallerist(@RequestBody DtoGalleristIU saveGallerist) {
        return ResponseEntity.ok(galleristService.saveGallerist(saveGallerist));
    }

    @GetMapping("/getGalleristById/{id}")
    @Override
    public ResponseEntity<DtoGallerist> getGalleristById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(galleristService.getGalleristById(id));
    }

    @Override
    public ResponseEntity<DtoGallerist> updateGallerist(Long id, DtoGalleristIU updateGallerist) {
        return ResponseEntity.ok(galleristService.updateGallerist(id,updateGallerist));
    }
}
