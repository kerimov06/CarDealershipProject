package com.turan.handler;

import lombok.Data;

import java.util.Date;

@Data
public class Exception<E> {

     private String hostName;
     private E message;
     private String path;
     private Date createTime;
}
