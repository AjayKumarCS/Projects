package com.nagarro.account.service.dao;

import com.nagarro.account.service.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountDao extends JpaRepository<Account,String> {
}
