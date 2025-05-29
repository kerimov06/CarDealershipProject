package com.turan.dto;

import com.turan.model.CurrencyType;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.math.BigDecimal;
@Data
public class DtoAccountIU {


    private String accountNo;

    private String iban;

    private BigDecimal amount;

    private CurrencyType currencyType;

}
