package com.turan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoSaleCarIU {

    private DtoGallerist gallerist;
    private DtoCar car;
    private DtoCustomer customer;
}
