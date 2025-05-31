package com.turan.controller;

import com.turan.dto.DtoAddress;
import com.turan.dto.DtoAddressIU;
import com.turan.model.ResponseEntity;

public interface IAddressController {

    public ResponseEntity<DtoAddress> saveAddress(DtoAddressIU address);
    public ResponseEntity<DtoAddress> getAddressById(Long id);
    public ResponseEntity<DtoAddress> updateAddress(Long id , DtoAddressIU address);
    public void deleteAddress(Long id);
}
