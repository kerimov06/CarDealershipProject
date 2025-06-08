package com.turan.controller;

import com.turan.dto.DtoCustomer;
import com.turan.dto.DtoCustomerIU;

public interface ICustomerController {

    public DtoCustomer saveCustomer(DtoCustomerIU saveCustomer);
    public DtoCustomer getCustomerById(Long id);
    public DtoCustomer updateCustomerById(Long id, DtoCustomerIU updateCustomer);
    public void deleteCustomerById(Long id);

}
