package com.turan.controller.impl;

import com.turan.controller.IAddressController;
import com.turan.dto.DtoAddress;
import com.turan.dto.DtoAddressIU;
import com.turan.model.ResponseEntity;
import com.turan.service.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/api/address")
public class AddressControllerImpl implements IAddressController {

    @Autowired
    private IAddressService addressService;

    @PostMapping("/saveAddress")
    @Override
    public ResponseEntity<DtoAddress> saveAddress(@RequestBody DtoAddressIU address) {
         return ResponseEntity.ok(addressService.saveAddress(address));
    }

    @GetMapping("/getAddressById/{id}")
    @Override
    public ResponseEntity<DtoAddress> getAddressById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(addressService.getAddressById(id));
    }
    @PutMapping("/updateAddress/{id}")
    @Override
    public ResponseEntity<DtoAddress> updateAddress(@PathVariable(name = "id") Long id, @RequestBody DtoAddressIU address) {
        return ResponseEntity.ok(addressService.updateAddress(id,address));
    }

    @DeleteMapping("/deleteAddress/{id}")
    @Override
    public void deleteAddress(@PathVariable(name = "id") Long id) {
        addressService.deleteAddress(id);

    }
}
