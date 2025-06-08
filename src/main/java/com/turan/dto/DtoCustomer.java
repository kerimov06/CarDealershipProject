package com.turan.dto;

import com.turan.model.Account;
import com.turan.model.Address;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoCustomer {

    private Long id;
    private String firstName;
    private String lastName;
    private Date birthOfDate;
    private String tckn;
    private DtoAccount account;
    private DtoAddress address;
}
