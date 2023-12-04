package com.nagarro.account.service.services;

import com.nagarro.account.service.models.Account;
import com.nagarro.account.service.models.Balance;
import com.nagarro.account.service.models.BalanceModification;

public interface AccountService {

    Account getAccount(String accountId);

    Account createAccount(Account account);

    Balance addMoney(BalanceModification bm);

    Balance withdrawMoney(BalanceModification bm);

    Account deleteAccount(String accountId);
}
