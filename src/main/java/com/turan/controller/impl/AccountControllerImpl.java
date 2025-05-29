package com.turan.controller.impl;

import com.turan.controller.IAccountController;
import com.turan.dto.DtoAccount;
import com.turan.dto.DtoAccountIU;
import com.turan.dto.DtoAddressIU;
import com.turan.service.IAccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/api/account")
public class AccountControllerImpl implements IAccountController {

    @Autowired
    private IAccountService accountService;


    @PostMapping("/saveAccount")
    @Override
    public DtoAccount saveAccount(@RequestBody DtoAccountIU account) {
        return accountService.saveAccount(account);
    }

    @GetMapping("/getAccountById/{id}")
    @Override
    public DtoAccount getAccountById(@PathVariable(name = "id") Long id) {
        return accountService.getAccountById(id);
    }

    @PutMapping("/updateAccount/{id}")
    @Override
    public DtoAccount updateAccount(@PathVariable(name = "id") Long id, @RequestBody DtoAccountIU updateAccount) {
        return accountService.updateAccount(id,updateAccount);
    }
    @DeleteMapping("/deleteAccount/{id}")
    @Override
    public void deleteAccount(@PathVariable(name = "id") Long id) {
         accountService.deleteAccount(id);

    }

    @GetMapping("/getAllAccounts")
    @Override
    public List<DtoAccount> getAllAccount() {
        return accountService.getAllAccount();
    }


}
