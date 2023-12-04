package com.nagarro.account.service.services.impl;

import com.nagarro.account.service.dao.AccountDao;
import com.nagarro.account.service.exceptions.ResourceNotFoundException;
import com.nagarro.account.service.models.Account;
import com.nagarro.account.service.models.Balance;
import com.nagarro.account.service.models.BalanceModification;
import com.nagarro.account.service.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountDao accountDao;

    @Override
    public Account getAccount(String accountId){
        return accountDao.findById(accountId).orElseThrow(() -> new ResourceNotFoundException("Account with id " + accountId + " is not available on server!!"));
    }

    @Override
    public Account createAccount(Account account){
        return accountDao.save(account);
    }

    @Override
    public Balance addMoney(BalanceModification bm) {
        Account acc = getAccount(bm.getAccountId());
        int balance = acc.getBalance() + bm.getAmount();
        acc.setBalance(balance);
        accountDao.save(acc);
        return new Balance(bm.getAccountId(),balance);
    }

    @Override
    public Balance withdrawMoney(BalanceModification bm) {
        Account acc = getAccount(bm.getAccountId());
        int balance = acc.getBalance() - bm.getAmount();
        acc.setBalance(balance);
        accountDao.save(acc);
        return new Balance(bm.getAccountId(),balance);
    }

    @Override
    public Account deleteAccount(String accountId) {
        Account acc = getAccount(accountId);
        accountDao.deleteById(accountId);
        return acc;
    }
}
