package com.turan.controller.impl;

import com.turan.controller.IRestAuthController;
import com.turan.dto.DtoUser;
import com.turan.jwt.AutRequest;
import com.turan.jwt.AuthResponse;
import com.turan.model.ResponseEntity;
import com.turan.service.IAuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestAuthControllerImpl implements IRestAuthController {

    @Autowired
    private IAuthService authService;

    @PostMapping("/register")
    @Override
    public ResponseEntity<DtoUser> register(@Valid @RequestBody AutRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/authenticate")
    @Override
    public ResponseEntity<AuthResponse> authenticate(@RequestBody AutRequest request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }
}
