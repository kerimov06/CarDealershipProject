package com.turan.service;

import com.turan.dto.DtoAddress;
import com.turan.dto.DtoAddressIU;

public interface IAddressService {

     public DtoAddress saveAddress(DtoAddressIU address);
     public DtoAddress getAddressById(Long id);
     public DtoAddress updateAddress(Long id , DtoAddressIU address);
     public void deleteAddress(Long id);
}
