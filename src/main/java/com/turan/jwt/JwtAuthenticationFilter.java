package com.turan.jwt;

import com.turan.exception.BaseException;
import com.turan.exception.ErrorMessage;
import com.turan.exception.MessageType;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserDetailsService userDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String username;
        String header;
        String token;


            header = request.getHeader("Authorization");


            if (header==null){
                filterChain.doFilter(request,response);
                return;
            }

           token = header.substring(7);

                try {
                     username = jwtService.getUsernameByToken(token);

                     if (username!=null&& SecurityContextHolder.getContext().getAuthentication()==null){
                         UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                          if (userDetails!=null && jwtService.isTokenValid(token)){
                              UsernamePasswordAuthenticationToken authentication =
                                      new UsernamePasswordAuthenticationToken(username,null ,userDetails.getAuthorities());

                              authentication.setDetails(userDetails);

                              SecurityContextHolder.getContext().setAuthentication(authentication);
                          }
                     }


                }catch (ExpiredJwtException e){
                    throw new BaseException(new ErrorMessage(e.getMessage(), MessageType.TOKEN_IS_EXPIRED));
                }catch (Exception e){
                     throw new BaseException(new ErrorMessage(e.getMessage(),MessageType.GENERAL_ERROR));
                }
               filterChain.doFilter(request,response);
    }
}
