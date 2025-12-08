package com.main.repository;

import com.main.core.repository.BaseRepository;
import com.main.entity.Account;
import com.main.entity.Transaction;
import com.main.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends BaseRepository<Account> {

}
