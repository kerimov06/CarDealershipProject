package com.turan.service.impl;

import com.turan.dto.DtoAddress;
import com.turan.dto.DtoAddressIU;
import com.turan.exception.BaseException;
import com.turan.exception.ErrorMessage;
import com.turan.exception.MessageType;
import com.turan.model.Address;
import com.turan.repository.AddressRepository;
import com.turan.service.IAddressService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressServiceImpl implements IAddressService {


    @Autowired
    private AddressRepository addressRepository;

    @Override
    public DtoAddress saveAddress(DtoAddressIU address) {
         DtoAddress dtoAddress = new DtoAddress();
         Address dataAddress = new Address();
        BeanUtils.copyProperties(address,dataAddress);

              Address saveAddress =  addressRepository.save(dataAddress);
              BeanUtils.copyProperties(saveAddress,dtoAddress);

              return dtoAddress;
    }

    @Override
    public DtoAddress getAddressById(Long id) {
        DtoAddress dtoAddress = new DtoAddress();
        Optional<Address>  optionalAddress = addressRepository.findById(id);

        if (optionalAddress.isEmpty()){
            throw new  BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST));
        }

          Address saveAddress = optionalAddress.get();
         BeanUtils.copyProperties(saveAddress,dtoAddress);

         return dtoAddress;
    }

    @Override
    public DtoAddress updateAddress(Long id , DtoAddressIU address) {
         DtoAddress dtoAddress = new DtoAddress();
         Optional<Address> optionalAddress =  addressRepository.findById(id);

               if (optionalAddress.isEmpty()){
                   throw  new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST));
               }

                Address dbAddress = optionalAddress.get();
                BeanUtils.copyProperties(address,dbAddress);
                Address saveAddress = addressRepository.save(dbAddress);
                 BeanUtils.copyProperties(saveAddress,dtoAddress);
                 return dtoAddress;

    }

    @Override
    public void deleteAddress(Long id) {
        try {
            Optional<Address> optionalAddress =   addressRepository.findById(id);

            if (optionalAddress.isPresent()){
                addressRepository.delete(optionalAddress.get());
            }
        }catch (Exception e){
             throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST));
        }

    }
}
