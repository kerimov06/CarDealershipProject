package com.turan.service.impl;

import com.turan.dto.*;
import com.turan.exception.BaseException;
import com.turan.exception.ErrorMessage;
import com.turan.exception.MessageType;
import com.turan.model.Address;
import com.turan.model.Car;
import com.turan.model.Gallerist;
import com.turan.model.GalleristCar;
import com.turan.repository.GalleristCarRepository;
import com.turan.service.IGalleristCarService;
import org.aspectj.lang.reflect.DeclareErrorOrWarning;
import org.hibernate.metamodel.mapping.EntityRowIdMapping;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GalleristCarServiceImpl implements IGalleristCarService {

    @Autowired
    private GalleristCarRepository galleristCarRepository;


    @Override
    public DtoGalleristCar saveGalleristCar(DtoGalleristCarIU saveGalleristCar) {

        DtoGalleristCar dtoGalleristCar = new DtoGalleristCar();
        GalleristCar galleristCar = new GalleristCar();
        BeanUtils.copyProperties(saveGalleristCar, galleristCar, "gallerist", "car");

        try {
            if (saveGalleristCar.getGallerist() != null) {
                Gallerist gallerist = new Gallerist();
                BeanUtils.copyProperties(saveGalleristCar.getGallerist(), gallerist);

                if (saveGalleristCar.getGallerist().getAddress() != null) {
                    Address address = new Address();
                    BeanUtils.copyProperties(saveGalleristCar.getGallerist().getAddress(), address);
                    gallerist.setAddress(address);
                }
                galleristCar.setGallerist(gallerist);
            }
        } catch (Exception e) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST));
        }


        try {

            if (saveGalleristCar.getCar() != null) {
                Car car = new Car();
                BeanUtils.copyProperties(saveGalleristCar.getCar(), car);
                galleristCar.setCar(car);
            }
        } catch (Exception e) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST));
        }

        GalleristCar saveGalCar = galleristCarRepository.save(galleristCar);

        if (saveGalCar.getGallerist() != null) {
            DtoGallerist dtoGallerist = new DtoGallerist();
            DtoAddress dtoAddress = new DtoAddress();
            BeanUtils.copyProperties(saveGalCar.getGallerist(), dtoGallerist);
            BeanUtils.copyProperties(saveGalCar.getGallerist().getAddress(), dtoAddress);
            dtoGallerist.setAddress(dtoAddress);
            dtoGalleristCar.setGallerist(dtoGallerist);
        }

        if (saveGalCar.getCar() != null) {
            DtoCar dtoCar = new DtoCar();
            BeanUtils.copyProperties(saveGalCar.getCar(), dtoCar);
            dtoGalleristCar.setCar(dtoCar);
        }

        BeanUtils.copyProperties(saveGalCar, dtoGalleristCar, "gallerist", "car");


        return dtoGalleristCar;
    }

    @Override
    public DtoGalleristCar getGalleristCarById(Long id) {
        DtoGalleristCar dtoGalleristCar = new DtoGalleristCar();
        Optional<GalleristCar> optional = galleristCarRepository.findById(id);

        if (optional.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST));
        }

        GalleristCar galleristCar = optional.get();
        BeanUtils.copyProperties(galleristCar, dtoGalleristCar, "gallerist", "car");

        if (galleristCar.getGallerist() != null) {
            DtoGallerist dtoGallerist = new DtoGallerist();
            BeanUtils.copyProperties(galleristCar.getGallerist(), dtoGallerist);

            if (galleristCar.getGallerist().getAddress() != null) {
                DtoAddress dtoAddress = new DtoAddress();
                BeanUtils.copyProperties(galleristCar.getGallerist().getAddress(), dtoAddress);
                dtoGallerist.setAddress(dtoAddress);
            }
            dtoGalleristCar.setGallerist(dtoGallerist);
        }

        if (galleristCar.getCar() != null) {
            DtoCar dtoCar = new DtoCar();
            BeanUtils.copyProperties(galleristCar.getCar(), dtoCar);
            dtoGalleristCar.setCar(dtoCar);
        }

        return dtoGalleristCar;
    }

    @Override
    public DtoGalleristCar updateGalleristCar(Long id, DtoGalleristCarIU updateGalleristCar) {
        DtoGalleristCar dtoGalleristCar = new DtoGalleristCar();
        Optional<GalleristCar> optional = galleristCarRepository.findById(id);

        if (optional.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST));
        }

        GalleristCar galleristCar = optional.get();
        BeanUtils.copyProperties(updateGalleristCar, galleristCar, "gallerist", "car");

        if (updateGalleristCar.getGallerist() != null) {
            Gallerist gallerist = new Gallerist();
            BeanUtils.copyProperties(updateGalleristCar.getGallerist(), gallerist);

            if (updateGalleristCar.getGallerist().getAddress() != null) {
                Address address = new Address();
                BeanUtils.copyProperties(updateGalleristCar.getGallerist().getAddress(), address);
                gallerist.setAddress(address);
            }

            galleristCar.setGallerist(gallerist);

            if (updateGalleristCar.getCar() != null) {
                Car car = new Car();
                BeanUtils.copyProperties(updateGalleristCar.getCar(), car);
                galleristCar.setCar(car);
            }


            GalleristCar saveGalleristCar = galleristCarRepository.save(galleristCar);

            BeanUtils.copyProperties(saveGalleristCar, dtoGalleristCar, "gallerist", "car");

            if (galleristCar.getGallerist() != null) {
                DtoGallerist dtoGallerist = new DtoGallerist();
                BeanUtils.copyProperties(galleristCar.getGallerist(), dtoGallerist);
                if (galleristCar.getGallerist().getAddress() != null) {
                    DtoAddress dtoAddress = new DtoAddress();
                    BeanUtils.copyProperties(galleristCar.getGallerist().getAddress(), dtoAddress);
                    dtoGallerist.setAddress(dtoAddress);
                }

                dtoGalleristCar.setGallerist(dtoGallerist);

                if (galleristCar.getCar()!=null){
                DtoCar dtoCar = new DtoCar();
                BeanUtils.copyProperties(galleristCar.getCar(), dtoCar);
                dtoGalleristCar.setCar(dtoCar);
                }




            }
        }
        return dtoGalleristCar;
    }

    @Override
    public void deleteGalleristCar(Long id) {
        try {
            Optional<GalleristCar> optional =  galleristCarRepository.findById(id);

            if (optional.isPresent()){
                 galleristCarRepository.delete(optional.get());
            }

        }catch (Exception e){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST));
        }

    }

    @Override
    public List<DtoGalleristCar> getAllGalleristCar() {
            List<DtoGalleristCar> dtoGalleristCars = new ArrayList<>();
            List<GalleristCar> galleristCarList = galleristCarRepository.findAll();

             if (galleristCarList.isEmpty()){
                  throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST));
             }

                for (GalleristCar galleristCar : galleristCarList){
                    DtoGalleristCar dto = new DtoGalleristCar();
                     BeanUtils.copyProperties(galleristCar,dto, "gallerist" , "car");

                      if (galleristCar.getGallerist()!=null){
                           DtoGallerist dtoGallerist = new DtoGallerist();
                           BeanUtils.copyProperties(galleristCar.getGallerist(),dtoGallerist);

                           if (galleristCar.getGallerist().getAddress()!=null){
                                DtoAddress dtoAddress = new DtoAddress();
                                 BeanUtils.copyProperties(galleristCar.getGallerist().getAddress(),dtoAddress);
                                 dtoGallerist.setAddress(dtoAddress);
                           }

                           dto.setGallerist(dtoGallerist);
                      }

                      if (galleristCar.getCar()!=null){
                           DtoCar dtoCar = new DtoCar();
                           BeanUtils.copyProperties(galleristCar.getCar(),dtoCar);
                           dto.setCar(dtoCar);
                      }
                    dtoGalleristCars.add(dto);

    }
               return dtoGalleristCars;
}
    }


