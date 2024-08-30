package org.omni.bank.account.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/account")
public class AccountController {

    @PostMapping(value = "/")
    public void createAccount() {
//        TODO
    }

    @GetMapping(value = "/{accountId}")
    public void findByAccountId() {
//        TODO
    }

    @GetMapping(value = "/{customerId}")
    public void findByCustomerId() {
//        TODO
    }

    @PutMapping(value = "/{accountId}")
    public void updateAccount() {
//        TODO
    }
    @DeleteMapping(value = "/{accountId}")
    public  void deleteAccount(){
//        TODO
    }
    @GetMapping(value = "/")
    public void allAccount(){
//        TODO
    }
    @GetMapping(value = "/bal/{accountId}")
    public void fetchAccountBalance(){
//        TODO
    }

    @PostMapping(value = "/fund-transfer")
    public void fundTransfer(){
//        TODO
    }

}
