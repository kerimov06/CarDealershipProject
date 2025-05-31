package com.turan.service.impl;

import com.turan.dto.DtoCar;
import com.turan.dto.DtoCarIU;
import com.turan.exception.BaseException;
import com.turan.exception.ErrorMessage;
import com.turan.exception.MessageType;
import com.turan.model.Car;
import com.turan.repository.CarRepository;
import com.turan.service.ICarService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.awt.dnd.DropTarget;
import java.util.Optional;

@Service
public class CarServiceImpl implements ICarService {

    @Autowired
    private CarRepository carRepository;
    @Override
    public DtoCar saveCar(DtoCarIU saveCar) {
        DtoCar dtoCar = new DtoCar();
        Car car  = new Car();
        BeanUtils.copyProperties(saveCar,car);

            Car savedCar =  carRepository.save(car);
            BeanUtils.copyProperties(savedCar,dtoCar);

        return dtoCar;
    }

    @Override
    public DtoCar getCar(Long id) {
        DtoCar dtoCar = new DtoCar();
        Optional<Car> optionalCar = carRepository.findById(id);

         if (optionalCar.isEmpty()){
              throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST));
         }

              Car dataCar =  optionalCar.get();
              BeanUtils.copyProperties(dataCar,dtoCar);

        return dtoCar;
    }

    @Override
    public DtoCar updateCar(Long id, DtoCarIU updateCar) {
         DtoCar dtoCar = new DtoCar();
         Optional<Car> optCar =  carRepository.findById(id);

          if (optCar.isEmpty()){
              throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST));
          }
             Car dataCar = optCar.get();
             BeanUtils.copyProperties(updateCar,dataCar);
             Car saveCar = carRepository.save(dataCar);
             BeanUtils.copyProperties(saveCar,dtoCar);

        return dtoCar;
    }

    @Override
    public void deleteCar(Long id) {
          Optional<Car> optCar = carRepository.findById(id);

           if (optCar.isPresent()){
                 carRepository.delete(optCar.get());
           }
    }
}
