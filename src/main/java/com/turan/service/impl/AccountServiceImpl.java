package com.turan.service.impl;

import com.turan.dto.DtoAccount;
import com.turan.dto.DtoAccountIU;
import com.turan.exception.BaseException;
import com.turan.exception.ErrorMessage;
import com.turan.exception.MessageType;
import com.turan.model.Account;
import com.turan.repository.AccountRepository;
import com.turan.service.IAccountService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Override
    public DtoAccount saveAccount(DtoAccountIU account) {
        DtoAccount dtoAccount = new DtoAccount();
        Account account1 = new Account();
        BeanUtils.copyProperties(account,account1);

                Account saveAccount =  accountRepository.save(account1);
                BeanUtils.copyProperties(saveAccount,dtoAccount);

                return dtoAccount;

    }

    @Override
    public DtoAccount getAccountById(Long id) {
        DtoAccount dtoAccount = new DtoAccount();
             Optional<Account> optionalAccount =  accountRepository.findById(id);
             if (optionalAccount.isEmpty()){
                 throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST));
             }
                Account dataAccount =  optionalAccount.get();
                BeanUtils.copyProperties(dataAccount,dtoAccount);
                return dtoAccount;
    }

    @Override
    public DtoAccount updateAccount(Long id, DtoAccountIU updateAccount) {
          DtoAccount dtoAccount = new DtoAccount();
                    Optional<Account> optionalAccount =  accountRepository.findById(id);

                    if (optionalAccount.isEmpty()){
                        throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST));
                    }

                     Account dataAccount = optionalAccount.get();
                      BeanUtils.copyProperties(updateAccount,dataAccount);
                      Account saveAccount = accountRepository.save(dataAccount);
                      BeanUtils.copyProperties(saveAccount,dtoAccount);

        return dtoAccount;
    }

    @Override
    public void deleteAccount(Long id) {
          Optional<Account> optionalAccount =  accountRepository.findById(id);
          if (optionalAccount.isPresent()){
                Account dataAccount = optionalAccount.get();
                 accountRepository.delete(dataAccount);
          }
    }

    @Override
    public List<DtoAccount> getAllAccount() {
          List<DtoAccount> dtoAccountsList = new ArrayList<>();
           List<Account> accountList = accountRepository.findAll();

           if (accountList.isEmpty()){
                throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST));
           }
            for (Account account : accountList){
                 DtoAccount dtoAccount = new DtoAccount();
                 BeanUtils.copyProperties(account,dtoAccount);
                 dtoAccountsList.add(dtoAccount);
            }
        return dtoAccountsList;
    }
}
