package com.turan.service.impl;

import com.turan.dto.DtoAccount;
import com.turan.dto.DtoAddress;
import com.turan.dto.DtoCustomer;
import com.turan.dto.DtoCustomerIU;
import com.turan.exception.BaseException;
import com.turan.exception.ErrorMessage;
import com.turan.exception.MessageType;
import com.turan.model.Account;
import com.turan.model.Address;
import com.turan.model.Customer;
import com.turan.repository.CustomerRepository;
import com.turan.service.ICustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;



    @Override
    public DtoCustomer saveCustomer(DtoCustomerIU saveCustomer) {
         DtoCustomer dtoCustomer = new DtoCustomer();
         Customer customer = new Customer();

         BeanUtils.copyProperties(saveCustomer,customer,"account" , "address");


         if (saveCustomer.getAccount()!=null) {
             Account account = new Account();
             BeanUtils.copyProperties(saveCustomer.getAccount(), account);
             customer.setAccount(account);
         }
        if (saveCustomer.getAddress()!=null) {
            Address address = new Address();
            BeanUtils.copyProperties(saveCustomer.getAddress(), address);
            customer.setAddress(address);
        }

              Customer savedCustomer = customerRepository.save(customer);

            BeanUtils.copyProperties(savedCustomer,dtoCustomer,"account","address");

            if (customer.getAccount()!=null) {
                DtoAccount dtoAccount = new DtoAccount();
                BeanUtils.copyProperties(customer.getAccount(), dtoAccount);
                dtoCustomer.setAccount(dtoAccount);
            }

             if (customer.getAddress()!=null){
                 DtoAddress dtoAddress = new DtoAddress();
                 BeanUtils.copyProperties(customer.getAddress(),dtoAddress);
                 dtoCustomer.setAddress(dtoAddress);
             }


        return dtoCustomer;
    }

    @Override
    public DtoCustomer getCustomerById(Long id) {
          DtoCustomer dtoCustomer = new DtoCustomer();
            Optional<Customer> optional = customerRepository.findById(id);

            if (optional.isEmpty()){
                throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST));
            }

                   Customer dataCustomer = optional.get();
                BeanUtils.copyProperties(dataCustomer,dtoCustomer,"address" , "account");

                if (dataCustomer.getAccount()!=null) {
                    DtoAccount dtoAccount = new DtoAccount();
                    BeanUtils.copyProperties(dataCustomer.getAccount(),dtoAccount);
                    dtoCustomer.setAccount(dtoAccount);
                }

                if (dataCustomer.getAddress()!=null){
                    DtoAddress dtoAddress = new DtoAddress();
                    BeanUtils.copyProperties(dataCustomer.getAddress(),dtoAddress);
                    dtoCustomer.setAddress(dtoAddress);
                }

        return dtoCustomer;
    }

    @Override
    public DtoCustomer updateCustomerById(Long id, DtoCustomerIU updateCustomer) {
         DtoCustomer dtoCustomer = new DtoCustomer();
         Optional<Customer> optional =  customerRepository.findById(id);

          if (optional.isEmpty()){
              throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST));
          }
                 Customer dataCustomer =  optional.get();
               BeanUtils.copyProperties(updateCustomer,dataCustomer,"account","address");
               if (updateCustomer.getAccount()!=null) {
                   Account account = new Account();
                   BeanUtils.copyProperties(updateCustomer.getAccount(), account);
                   dataCustomer.setAccount(account);
               }

                if(updateCustomer.getAddress()!=null){
                     Address address = new Address();
                     BeanUtils.copyProperties(updateCustomer.getAddress(),address);
                     dataCustomer.setAddress(address);
                }

                  Customer saveCustomer =   customerRepository.save(dataCustomer);
                BeanUtils.copyProperties(saveCustomer,dtoCustomer,"account","address");

                if (dataCustomer.getAccount()!=null) {
                    DtoAccount dtoAccount = new DtoAccount();
                    BeanUtils.copyProperties(dataCustomer.getAccount(),dtoAccount);
                    dtoCustomer.setAccount(dtoAccount);

                }

                 if (dataCustomer.getAddress()!=null){
                      DtoAddress dtoAddress = new DtoAddress();
                      BeanUtils.copyProperties(dataCustomer.getAddress(),dtoAddress);
                      dtoCustomer.setAddress(dtoAddress);
                 }

        return dtoCustomer;
    }

    @Override
    public void deleteCustomerById(Long id) {
        Optional<Customer> optional =  customerRepository.findById(id);
        try {
            if (optional.isPresent()) {
                Customer dataCustomer = optional.get();
                customerRepository.delete(dataCustomer);
            }
        }catch (Exception e){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST));
        }

    }
}
