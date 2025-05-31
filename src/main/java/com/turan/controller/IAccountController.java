package com.turan.controller;

import com.turan.dto.DtoAccount;
import com.turan.dto.DtoAccountIU;
import com.turan.dto.DtoAddressIU;
import com.turan.model.ResponseEntity;

import java.util.List;

public interface IAccountController {

     public ResponseEntity<DtoAccount> saveAccount(DtoAccountIU account);
     public ResponseEntity<DtoAccount> getAccountById(Long id);
     public ResponseEntity<DtoAccount> updateAccount(Long id , DtoAccountIU updateAccount);
     public void deleteAccount(Long id);
     public ResponseEntity<List<DtoAccount>> getAllAccount();
}
