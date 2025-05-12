package com.turan.controller;

import com.turan.model.ResponseEntity;

public class ResEController {

     public <T>ResponseEntity<T> ok(T data) {
          return ResponseEntity.ok(data);
     }
     public <T>ResponseEntity<T> error(String errorMessage){
          return ResponseEntity.error(errorMessage);
     }


}
