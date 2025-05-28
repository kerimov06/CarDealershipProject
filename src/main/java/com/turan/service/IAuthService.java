package com.turan.service;

import com.turan.dto.DtoUser;
import com.turan.jwt.AutRequest;
import com.turan.jwt.AuthResponse;
import com.turan.jwt.RefreshTokenRequest;

public interface IAuthService {

   public DtoUser register(AutRequest request);
   public AuthResponse authenticate(AutRequest request);
}
