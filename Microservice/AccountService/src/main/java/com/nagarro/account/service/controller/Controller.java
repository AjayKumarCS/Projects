package com.nagarro.account.service.controller;

import com.nagarro.account.service.models.Account;
import com.nagarro.account.service.models.Balance;
import com.nagarro.account.service.models.BalanceModification;
import com.nagarro.account.service.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class Controller {

    @Autowired
    private AccountService accountService;

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account account){
        Account account1 = accountService.createAccount(account);
        return ResponseEntity.status(HttpStatus.CREATED).body(account1);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<Account> getAccount(@PathVariable String accountId){
        Account account = accountService.getAccount(accountId);
        return ResponseEntity.ok(account);
    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity<Account> deleteCustomer(@PathVariable String accountId){
        Account account = accountService.deleteAccount(accountId);
        return ResponseEntity.ok(account);
    }

    @PostMapping("/add")
    public ResponseEntity<Balance> addMoney(@RequestBody BalanceModification bm){
        Balance bal = accountService.addMoney(bm);
        return ResponseEntity.ok(bal);
    }

    @PostMapping("/withdraw")
    public ResponseEntity<Balance> withdrawMoney(@RequestBody BalanceModification bm){
        Balance bal = accountService.withdrawMoney(bm);
        return ResponseEntity.ok(bal);
    }
}

