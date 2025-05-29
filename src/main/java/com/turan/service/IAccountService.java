package com.turan.service;

import com.turan.dto.DtoAccount;
import com.turan.dto.DtoAccountIU;

import java.util.List;

public interface IAccountService {

     public DtoAccount saveAccount(DtoAccountIU account);
     public DtoAccount getAccountById(Long id);
     public DtoAccount updateAccount(Long id , DtoAccountIU updateAccount);
     public void deleteAccount(Long id);
     public List<DtoAccount> getAllAccount();
}
