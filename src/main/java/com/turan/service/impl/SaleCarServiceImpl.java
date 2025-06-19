package com.turan.service.impl;

import com.turan.dto.*;
import com.turan.exception.BaseException;
import com.turan.exception.ErrorMessage;
import com.turan.exception.MessageType;
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

import java.util.Optional;

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

    @Override
    public DtoSaleCar getSaleCarById(Long id) {
         DtoSaleCar dtoSaleCar = new DtoSaleCar();
        SaleCar saleCar = new SaleCar();
         Optional<SaleCar> optional = saleCarRepository.findById(id);


             if (optional.isEmpty()){
                 throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST));
             }

               if (optional.get()!=null) {
                   SaleCar dbSaleCar = optional.get();
                   BeanUtils.copyProperties(dbSaleCar,dtoSaleCar);
               }

                if (optional.get().getGallerist()!=null){
                    Gallerist gallerist = new Gallerist();
                    BeanUtils.copyProperties(optional.get().getGallerist(), gallerist);


                    if (optional.get().getGallerist().getAddress()!=null) {
                        Address address = new Address();
                        BeanUtils.copyProperties(optional.get().getGallerist().getAddress(), address);
                        gallerist.setAddress(address);
                    }
                    saleCar.setGallerist(gallerist);
                }

                if (optional.get().getCar()!=null) {
                     Car car = new Car();
                     BeanUtils.copyProperties(optional.get().getCar(),car);
                     saleCar.setCar(car);
                }

                if (optional.get().getCustomer()!=null){
                    Customer customer = new Customer();
                    BeanUtils.copyProperties(optional.get().getCustomer(),customer);



                    if (optional.get().getCustomer().getAccount()!=null) {
                        Account account = new Account();
                        BeanUtils.copyProperties(optional.get().getCustomer().getAccount(), account);
                        customer.setAccount(account);
                    }

                     if (optional.get().getCustomer().getAddress()!=null) {
                         Address address = new Address();
                         BeanUtils.copyProperties(optional.get().getCustomer().getAddress(), address);
                         customer.setAddress(address);
                     }
                     saleCar.setCustomer(customer);
                }

                if (saleCar.getGallerist()!=null) {
                    DtoGallerist dtoGallerist = new DtoGallerist();
                    BeanUtils.copyProperties(saleCar.getGallerist(), dtoGallerist);

                    if (saleCar.getGallerist().getAddress()!=null) {
                        DtoAddress dtoAddress = new DtoAddress();
                        BeanUtils.copyProperties(saleCar.getGallerist().getAddress(), dtoAddress);
                         dtoGallerist.setAddress(dtoAddress);
                    }
                    dtoSaleCar.setGallerist(dtoGallerist);
                }

                if (saleCar.getCar()!=null){
                    DtoCar dtoCar = new DtoCar();
                    BeanUtils.copyProperties(saleCar.getCar(),dtoCar);
                    dtoSaleCar.setCar(dtoCar);
                }
                 if (saleCar.getCustomer()!=null){
                     DtoCustomer dtoCustomer = new DtoCustomer();
                     BeanUtils.copyProperties(saleCar.getCustomer(),dtoCustomer);

                     if (optional.get().getCustomer().getAccount()!=null) {
                         DtoAccount dtoAccount = new DtoAccount();
                         BeanUtils.copyProperties(saleCar.getCustomer().getAccount(),dtoAccount);
                         dtoCustomer.setAccount(dtoAccount);
                     }

                     if (optional.get().getCustomer().getAddress()!=null) {
                         DtoAddress dtoAddress = new DtoAddress();
                         BeanUtils.copyProperties(saleCar.getCustomer().getAddress(),dtoAddress);
                         dtoCustomer.setAddress(dtoAddress);
                     }
                      dtoSaleCar.setCustomer(dtoCustomer);
                 }

        return dtoSaleCar;
    }

    @Override
    public DtoSaleCar updateSaleCar(Long id, DtoSaleCarIU updateSaleCar) {
          DtoSaleCar dtoSaleCar = new DtoSaleCar();
              Optional<SaleCar> optional = saleCarRepository.findById(id);
              if (optional.isEmpty()){
                   throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST));
              }


                SaleCar dbSaleCar = optional.get();
                   if (updateSaleCar.getGallerist()!=null) {
                       Gallerist gallerist = new Gallerist();
                       BeanUtils.copyProperties(updateSaleCar.getGallerist(), gallerist);

                       if (updateSaleCar.getGallerist().getAddress()!= null) {
                           Address address = new Address();
                           BeanUtils.copyProperties(updateSaleCar.getGallerist().getAddress(), address);
                           gallerist.setAddress(address);
                       }
                        dbSaleCar.setGallerist(gallerist);
                   }

                    if (updateSaleCar.getCar()!=null) {
                        Car car = new Car();
                        BeanUtils.copyProperties(updateSaleCar.getCar(),car);
                        dbSaleCar.setCar(car);
                    }

                    if (updateSaleCar.getCustomer()!=null) {
                        Customer customer = new Customer();
                        BeanUtils.copyProperties(updateSaleCar.getCustomer(), customer);
                        if (updateSaleCar.getCustomer().getAccount()!= null) {
                            Account account = new Account();
                            BeanUtils.copyProperties(updateSaleCar.getCustomer().getAccount(), account);
                            customer.setAccount(account);
                        }
                        if (updateSaleCar.getCustomer().getAddress()!=null){
                            Address address = new Address();
                            BeanUtils.copyProperties(updateSaleCar.getCustomer().getAddress(),address);
                            customer.setAddress(address);
                        }
                          dbSaleCar.setCustomer(customer);
                    }

                          SaleCar saveSaleCar = saleCarRepository.save(dbSaleCar);
                       if (saveSaleCar.getGallerist()!=null) {
                           DtoGallerist dtoGallerist = new DtoGallerist();
                           BeanUtils.copyProperties(saveSaleCar.getGallerist(),dtoGallerist);

                           if (saveSaleCar.getGallerist().getAddress()!= null) {
                               DtoAddress dtoAddress = new DtoAddress();
                               BeanUtils.copyProperties(saveSaleCar.getGallerist().getAddress(), dtoAddress);
                               dtoGallerist.setAddress(dtoAddress);
                           }
                             dtoSaleCar.setGallerist(dtoGallerist);
                       }

                         if (saveSaleCar.getCar()!=null){
                             DtoCar dtoCar = new DtoCar();
                             BeanUtils.copyProperties(saveSaleCar.getCar(),dtoCar);
                             dtoSaleCar.setCar(dtoCar);
                         }


                         if (saveSaleCar.getCustomer()!=null){
                              DtoCustomer dtoCustomer = new DtoCustomer();
                              BeanUtils.copyProperties(saveSaleCar.getCustomer(),dtoCustomer);

                                 if (saveSaleCar.getCustomer().getAccount()!=null){
                                     DtoAccount dtoAccount = new DtoAccount();
                                     BeanUtils.copyProperties(saveSaleCar.getCustomer().getAccount(),dtoAccount);
                                     dtoCustomer.setAccount(dtoAccount);
                                 }

                                 if (saveSaleCar.getCustomer().getAddress()!=null){
                                     DtoAddress dtoAddress = new DtoAddress();
                                     BeanUtils.copyProperties(saveSaleCar.getCustomer().getAddress(),dtoAddress);
                                     dtoCustomer.setAddress(dtoAddress);
                                 }
                                    dtoSaleCar.setCustomer(dtoCustomer);
                         }

        return dtoSaleCar;
    }

    @Override
    public void deleteSaleCar(Long id) {
         try {
             Optional<SaleCar> optional =  saleCarRepository.findById(id);

             if (optional.isPresent()){
                 SaleCar dbSaleCar = optional.get();
                 saleCarRepository.delete(dbSaleCar);
             }

         }catch (Exception e){
           throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST));
         }


    }
}
