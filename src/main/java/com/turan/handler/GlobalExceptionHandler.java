package com.turan.handler;

import com.turan.exception.BaseException;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {BaseException.class})
    public ResponseEntity<ApiError<?>> exceptionHandler(BaseException exception, WebRequest request){
         return ResponseEntity.badRequest().body(apiError(exception.getMessage(),request));
    }


    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
public ResponseEntity<ApiError<Map<String,List<String>>>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception , WebRequest request){
        Map<String, List<String>> map = new HashMap<>();
         for (ObjectError obj : exception.getBindingResult().getAllErrors()){
             String fieldName = ((FieldError)obj).getField();
             if (map.containsKey(fieldName)){
               map.put(fieldName,addValue(map.get(fieldName),obj.getDefaultMessage()));
             }else{
                 map.put(fieldName, addValue(new ArrayList<>(),obj.getDefaultMessage()));
             }
         }
         return ResponseEntity.badRequest().body(apiError(null,request));
    }


private List<String> addValue(List<String> list , String newValue){
        list.add(newValue);
        return list;
}


    public <E>ApiError<E> apiError(E message, WebRequest request) {
         ApiError<E> apiError = new ApiError<>();
         apiError.setStatus(HttpStatus.BAD_REQUEST.value());


         Exception<E> exception = new Exception<>();
          exception.setHostName(getHostname());
          exception.setMessage(message);
          exception.setCreateTime(new Date());
          exception.setPath(request.getDescription(false).substring(4));

           apiError.setException(exception);
           return apiError;
    }

     public String getHostname(){
         try {
             return InetAddress.getLocalHost().getHostName();
         } catch (UnknownHostException e) {
             System.out.println("Have some problems" + e.getMessage());
         }
         return null;
     }
}
