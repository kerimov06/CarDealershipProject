package com.turan.jwt;



import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

      private static final  String SECRET_KEY ="YnUgY29rIGd1Y2x1IGJpciBzaWZyZWRpciBtZW5pbSBhZGltIHR1cmFuZGlyCg==";


     public String generateToken(UserDetails userDetails){
         return Jwts.builder()
                  .setSubject(userDetails.getUsername())
                  .setIssuedAt(new Date())
                  .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*2))
                  .signWith(getKey(), SignatureAlgorithm.HS256).compact();

     }


      public <T> T exportToken(String token , Function<Claims,T> claimsTFunction){
                  Claims claims =  Jwts.parserBuilder()
                           .setSigningKey(getKey())
                           .build().parseClaimsJws(token).getBody();

                  return claimsTFunction.apply(claims);
      }

         public String getUsernameByToken(String token){
           return exportToken(token,Claims::getSubject);
         }

          public boolean isTokenValid(String token){
             Date expireDate =exportToken(token,Claims::getExpiration);
              return new Date().before(expireDate);
          }


      private Key getKey(){
          byte[] key =  Decoders.BASE64.decode(SECRET_KEY);
            return Keys.hmacShaKeyFor(key);
      }
}
