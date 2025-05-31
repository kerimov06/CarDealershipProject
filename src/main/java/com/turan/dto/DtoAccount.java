package com.turan.dto;

import com.turan.model.CurrencyType;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoAccount {


    private Long id;
    private String accountNo;
    private String iban;
    private BigDecimal amount;
    private CurrencyType currencyType;


}
