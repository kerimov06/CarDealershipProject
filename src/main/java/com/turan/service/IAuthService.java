package com.turan.service;

import com.turan.dto.DtoUser;
import com.turan.dto.RefreshTokenRequest;
import com.turan.jwt.AutRequest;
import com.turan.jwt.AuthResponse;

public interface IAuthService {

   public DtoUser register(AutRequest request);
   public AuthResponse authenticate(AutRequest request);
   public AuthResponse refreshToken(RefreshTokenRequest refreshToken);
}
