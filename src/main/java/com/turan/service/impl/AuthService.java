package com.turan.service.impl;

import com.turan.dto.DtoUser;
import com.turan.dto.RefreshTokenRequest;
import com.turan.exception.BaseException;
import com.turan.exception.ErrorMessage;
import com.turan.exception.MessageType;
import com.turan.jwt.AutRequest;
import com.turan.jwt.AuthResponse;
import com.turan.jwt.JwtService;
import com.turan.model.RefreshToken;
import com.turan.model.User;
import com.turan.repository.RefreshTokenRepository;
import com.turan.repository.UserRepository;
import com.turan.service.IAuthService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

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

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;


    private RefreshToken createRefreshToken(User user) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setExpireDate(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 4));
        refreshToken.setRefreshToken(UUID.randomUUID().toString());
        refreshToken.setUser(user);
        return refreshToken;
    }


    @Override
    public DtoUser register(AutRequest request) {
        DtoUser dtoUser = new DtoUser();
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        User saveUser = userRepository.save(user);
        BeanUtils.copyProperties(saveUser, dtoUser);
        return dtoUser;
    }

    @Override
    public AuthResponse authenticate(AutRequest request) {

        try {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
            authenticationProvider.authenticate(authenticationToken);

            Optional<User> optUser = userRepository.findByUsername(request.getUsername());
            User user = optUser.get();
            boolean isPasswordMatch = passwordEncoder.matches(request.getPassword(), user.getPassword());
            if (!isPasswordMatch) {
                throw new RuntimeException("Username or Password is incorrect");
            }

            String accessToken = jwtService.generateToken(user);


            RefreshToken saveRefreshToken = refreshTokenRepository.save(createRefreshToken(optUser.get()));


            return new AuthResponse(accessToken, saveRefreshToken.getRefreshToken());

        } catch (Exception e) {
            throw new BaseException(new ErrorMessage(MessageType.USERNAME_OR_PASSWORD_INVALID));

        }
    }

     public boolean isValidRefreshToken(Date expireDate){
        return new Date().before(expireDate);

     }

    @Override
    public AuthResponse refreshToken(RefreshTokenRequest refreshToken) {
        Optional<RefreshToken> optRefreshToken =  refreshTokenRepository.findByRefreshToken(refreshToken.getRefreshToken());

        if (optRefreshToken.isEmpty()){
             throw new BaseException(new ErrorMessage(MessageType.REFRESH_TOKEN_NOT_FOUND));

        }

          if (!isValidRefreshToken(optRefreshToken.get().getExpireDate())){
              throw  new BaseException(new ErrorMessage(MessageType.TOKEN_IS_EXPIRED));
          }

               User user = (optRefreshToken.get().getUser());
                String accessToken = jwtService.generateToken(user);

             RefreshToken savedRefreshToken =  refreshTokenRepository.save(createRefreshToken(user));

        return new AuthResponse(accessToken,savedRefreshToken.getRefreshToken());
    }
}
