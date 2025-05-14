package com.turan.service.impl;

import com.turan.dto.DtoUser;
import com.turan.jwt.AutRequest;
import com.turan.jwt.AuthResponse;
import com.turan.jwt.JwtService;
import com.turan.model.User;
import com.turan.repository.UserRepository;
import com.turan.service.IAuthService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService implements IAuthService {

      @Autowired
      private UserRepository userRepository;

      @Autowired
      private BCryptPasswordEncoder passwordEncoder;

      @Autowired
      private AuthenticationProvider authenticationProvider;

      @Autowired
       private JwtService jwtService;



    @Override
    public DtoUser register(AutRequest request) {
           DtoUser dtoUser = new DtoUser();
           User user = new User();
            user.setUsername(request.getUsername());
            user.setPassword(passwordEncoder.encode(request.getPassword()));

                 User saveUser =   userRepository.save(user);
                 BeanUtils.copyProperties(saveUser,dtoUser);
                 return dtoUser;
    }

    @Override
    public AuthResponse authenticate(AutRequest request) {

         try {
             UsernamePasswordAuthenticationToken authenticationToken =
                     new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword());
             authenticationProvider.authenticate(authenticationToken);

                    Optional<User> optional = userRepository.findByUsername(request.getUsername());
                         User user = optional.get();
             boolean isPasswordMatch = passwordEncoder.matches(request.getPassword(), user.getPassword());
             if (!isPasswordMatch) {
                 throw new RuntimeException("Username or Password is incorrect");
             }

                    String token =   jwtService.generateToken(user);

              return new AuthResponse(token);

         }catch (Exception e){
             throw new RuntimeException("Username or Password is wrong: " + e.getMessage());

         }


    }
}
