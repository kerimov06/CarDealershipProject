package com.turan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoCustomerIU {

    private String firstName;
    private String lastName;
    private Date birthOfDate;
    private String tckn;
    private DtoAccount account;
    private DtoAddress address;

}
