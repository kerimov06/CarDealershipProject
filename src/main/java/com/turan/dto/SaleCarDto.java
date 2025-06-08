package com.turan.dto;

import com.turan.model.Car;
import com.turan.model.Customer;
import com.turan.model.Gallerist;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleCarDto {


    private Long id;

    private DtoGallerist gallerist;

    private DtoCar car;


}
