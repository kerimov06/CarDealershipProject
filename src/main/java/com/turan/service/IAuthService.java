package com.turan.service;

import com.turan.dto.DtoUser;
import com.turan.jwt.AutRequest;

public interface IAuthService {

   public DtoUser register(AutRequest request);
}
