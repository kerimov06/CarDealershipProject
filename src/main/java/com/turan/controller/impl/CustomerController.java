package com.turan.controller.impl;

import com.turan.controller.ICustomerController;
import com.turan.dto.DtoCustomer;
import com.turan.dto.DtoCustomerIU;
import com.turan.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/api/customer")
public class CustomerController implements ICustomerController {

    @Autowired
    private ICustomerService customerService ;

    @PostMapping("/saveCustomer")
    @Override
    public DtoCustomer saveCustomer(@RequestBody DtoCustomerIU saveCustomer) {
        return customerService.saveCustomer(saveCustomer);
    }

    @GetMapping("/getCustomerById/{id}")
    @Override
    public DtoCustomer getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }
    @PutMapping("/updateCustomer/{id}")
    @Override
    public DtoCustomer updateCustomerById(@PathVariable(name = "id") Long id, @RequestBody DtoCustomerIU updateCustomer) {
        return customerService.updateCustomerById(id,updateCustomer);
    }

    @DeleteMapping("/deleteCustomerById/{id}")
    @Override
    public void deleteCustomerById(@PathVariable(name = "id") Long id) {
         customerService.deleteCustomerById(id);

    }
}
