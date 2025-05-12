package com.turan.exception;

import lombok.*;

public class BaseException extends RuntimeException {

   public BaseException(ErrorMessage errorMessage){
        super(errorMessage.prepareException());

    }

}