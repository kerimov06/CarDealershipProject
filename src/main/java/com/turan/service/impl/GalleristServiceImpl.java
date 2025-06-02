package com.turan.service.impl;

import com.turan.dto.*;
import com.turan.exception.BaseException;
import com.turan.exception.ErrorMessage;
import com.turan.exception.MessageType;
import com.turan.model.Address;
import com.turan.model.Gallerist;
import com.turan.model.GalleristCar;
import com.turan.repository.GalleristRepository;
import com.turan.service.IGalleristService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GalleristServiceImpl implements IGalleristService {

    @Autowired
    private GalleristRepository galleristRepository;


    @Override
    public DtoGallerist saveGallerist(DtoGalleristIU saveGallerist) {
        DtoGallerist dtoGallerist = new DtoGallerist();
        Gallerist gallerist= new Gallerist();
        BeanUtils.copyProperties(saveGallerist,gallerist);

        Address address = new Address();
         if (saveGallerist.getAddress()!=null){
              BeanUtils.copyProperties(saveGallerist.getAddress(),address);
               gallerist.setAddress(address);
         }
        Gallerist dataGallerist =  galleristRepository.save(gallerist);

        BeanUtils.copyProperties(dataGallerist,dtoGallerist);

        return dtoGallerist;
    }

    @Override
    public DtoGallerist getGalleristById(Long id) {
         DtoGallerist dtoGallerist = new DtoGallerist();
                Optional<Gallerist> optional =  galleristRepository.findById(id);

                if (optional.isEmpty()){
                     throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST));
                }

                   Gallerist gallerist =  optional.get();
                   Address address = optional.get().getAddress();
                   gallerist.setAddress(address);
                   BeanUtils.copyProperties(gallerist,dtoGallerist);

        return dtoGallerist;
    }

    @Override
    public DtoGallerist updateGallerist(Long id, DtoGalleristIU updateGallerist) {
        DtoGallerist dtoGallerist = new DtoGallerist();
               Optional<Gallerist> optional =    galleristRepository.findById(id);
               if (optional.isEmpty()){
                   throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST));
               }

                  Gallerist gallerist =  optional.get();
               BeanUtils.copyProperties(updateGallerist,gallerist, "address");

                   if(updateGallerist.getAddress()!=null){
                       if (gallerist.getAddress()!=null){
                           gallerist.setAddress(new Address());

                       }
                       BeanUtils.copyProperties(updateGallerist.getAddress(),gallerist.getAddress());
                   }

                    Gallerist saveGallerist = galleristRepository.save(gallerist);
                    BeanUtils.copyProperties(saveGallerist,dtoGallerist);

        return dtoGallerist;
    }

    @Override
    public void deleteGallerist(Long id) {
       try {
           Optional<Gallerist> optional =  galleristRepository.findById(id);
           if (optional.isPresent()) {
               galleristRepository.delete(optional.get());
           }
       }catch (Exception e){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST));
       }

    }
}
