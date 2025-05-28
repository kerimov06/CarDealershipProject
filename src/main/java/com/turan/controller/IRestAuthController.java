package com.turan.controller;

import com.turan.dto.DtoUser;
import com.turan.jwt.AutRequest;
import com.turan.jwt.AuthResponse;
import com.turan.model.ResponseEntity;

public interface IRestAuthController {

    public ResponseEntity<DtoUser> register(AutRequest request);
    public ResponseEntity<AuthResponse> authenticate(AutRequest request);
}
