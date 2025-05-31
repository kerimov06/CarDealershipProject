package com.turan.dto;

import com.turan.model.Address;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoGallerist {

    private Long id;

    private String firstName;

    private String lastName;

    private DtoAddress address;

}
