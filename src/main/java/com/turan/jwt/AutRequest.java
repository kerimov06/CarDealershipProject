package com.turan.jwt;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AutRequest {

    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
}
