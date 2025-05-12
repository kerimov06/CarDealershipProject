package com.turan.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseEntity<T> {


     private String errorMessage;
     private boolean result;
     private T data;


     public static <T> ResponseEntity<T> ok(T data){
          ResponseEntity<T> responseEntity = new ResponseEntity<>();
            responseEntity.setData(data);
            responseEntity.setResult(true);
            responseEntity.setErrorMessage(null);

            return  responseEntity;
     }

     public static <T> ResponseEntity<T> error(String errorMessage){
         ResponseEntity<T> responseEntity = new ResponseEntity<>();
          responseEntity.setErrorMessage(errorMessage);
          responseEntity.setData(null);
          responseEntity.setResult(false);
          return responseEntity;
     }

}
