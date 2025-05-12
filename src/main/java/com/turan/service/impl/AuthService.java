package com.turan.service.impl;

import com.turan.dto.DtoUser;
import com.turan.jwt.AutRequest;
import com.turan.model.User;
import com.turan.repository.UserRepository;
import com.turan.service.IAuthService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements IAuthService {

      @Autowired
      private UserRepository userRepository;

      @Autowired
      private BCryptPasswordEncoder passwordEncoder;



    @Override
    public DtoUser register(AutRequest request) {
           DtoUser dtoUser = new DtoUser();
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

         User dbUser = userRepository.save(user);
        BeanUtils.copyProperties(dbUser,dtoUser);
        return dtoUser;
    }
}
