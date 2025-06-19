package com.turan.service.impl;

import com.turan.dto.*;
import com.turan.model.*;
import com.turan.repository.CarRepository;
import com.turan.repository.CustomerRepository;
import com.turan.repository.GalleristRepository;
import com.turan.repository.SaleCarRepository;
import com.turan.service.ISaleCarService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class SaleCarServiceImpl implements ISaleCarService {

    @Autowired
    private SaleCarRepository saleCarRepository;

    @Autowired
    private GalleristRepository galleristRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CustomerRepository customerRepository;


    @Override
    public DtoSaleCar saveSaleCar(DtoSaleCarIU saveSaleCar) {

        DtoSaleCar dtoSaleCar = new DtoSaleCar();
         SaleCar saleCar = new SaleCar();

        if(saveSaleCar.getGallerist() != null) {
            Gallerist gallerist = galleristRepository.findById(saveSaleCar.getGallerist().getId()).get();
            saleCar.setGallerist(gallerist);
        }

         if (saveSaleCar.getCar()!=null){
             Car car = carRepository.findById(saveSaleCar.getCar().getId()).get();
              saleCar.setCar(car);

         }

         if (saveSaleCar.getCustomer()!=null){
             Customer customer = customerRepository.findById(saveSaleCar.getCustomer().getId()).get();
             saleCar.setCustomer(customer);
         }

               SaleCar saveDataSaleCar = saleCarRepository.save(saleCar);

             if (saveDataSaleCar.getGallerist()!=null){
                 DtoGallerist dtoGallerist = new DtoGallerist();
                 BeanUtils.copyProperties(saveDataSaleCar.getGallerist(),dtoGallerist);
                 dtoSaleCar.setGallerist(dtoGallerist);
             }

             if (saveDataSaleCar.getCar()!=null){
                 DtoCar dtoCar = new DtoCar();
                 BeanUtils.copyProperties(saveDataSaleCar.getCar(),dtoCar);
                 dtoSaleCar.setCar(dtoCar);
             }

             if (saveDataSaleCar.getCustomer()!=null){
                 DtoCustomer dtoCustomer = new DtoCustomer();
                 BeanUtils.copyProperties(saveDataSaleCar.getCustomer(),dtoCustomer);
                 dtoSaleCar.setCustomer(dtoCustomer);
             }
        return dtoSaleCar;
    }
}
