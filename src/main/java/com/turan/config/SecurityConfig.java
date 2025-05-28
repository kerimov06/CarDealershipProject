package com.turan.config;

import com.turan.jwt.JwtAuthenticationFilter;
import org.hibernate.annotations.Bag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Autowired
    private AuthenticationProvider authenticationProvider;


    @Autowired
    private JwtAuthenticationFilter authenticationFilter;

    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;


     public static final  String AUTHENTICATE = "/authenticate";
     public static final String REGISTER = "/register";


     @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
             http.csrf().disable()
                     .authorizeHttpRequests(request->
                             request.requestMatchers(AUTHENTICATE,REGISTER)
                                     .permitAll()
                                     .anyRequest()
                                     .authenticated())
                     .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint).and()
                     .sessionManagement(session->
                             session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                     .authenticationProvider(authenticationProvider)
                     .addFilterBefore(authenticationFilter,UsernamePasswordAuthenticationFilter.class);
              return http.build();
    }
}
