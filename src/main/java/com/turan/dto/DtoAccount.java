package com.turan.dto;

import com.turan.model.CurrencyType;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class DtoAccount {


    private Long id;
    private String accountNo;
    private String iban;
    private BigDecimal amount;
    private CurrencyType currencyType;


}
