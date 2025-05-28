package com.turan.exception;

import lombok.Getter;

@Getter
public enum MessageType {

    NO_RECORD_EXIST("404" , "This Data not found"),
    TOKEN_IS_EXPIRED("303" , "Token is expired"),
    GENERAL_ERROR("202" , "Sorry!Have some problems , Please! Try again"),
    USERNAME_OR_PASSWORD_INVALID("101" , "Sorry! Try again! Enter username or password correctly");


    private String code;
    private String message;

    MessageType(String code, String message){
        this.code = code;
        this.message = message;
    }
}
