package com.tcs.challenge.repository;

import com.tcs.challenge.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface AccountRepository extends JpaRepository<Account, Long> {

    @Modifying
    @Query("UPDATE Account a SET a.status = false WHERE a.id = :id")
    void inactivateAccount(@Param("id") Long id);

    @Modifying
    @Query("UPDATE Account a SET a.initialBalance = :balance WHERE a.id = :id")
    void updateBalance(@Param("balance") BigDecimal balance, @Param("id") Long id);

}
