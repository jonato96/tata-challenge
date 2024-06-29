package com.tcs.transaction.repository;

import com.tcs.transaction.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {

    @Modifying
    @Query("UPDATE Account a SET a.status = false WHERE a.id = :id")
    void inactivateAccount(@Param("id") Long id);

    @Modifying
    @Query("UPDATE Account a SET a.balance = :balance WHERE a.id = :id")
    void updateBalance(@Param("balance") BigDecimal balance, @Param("id") Long id);

    boolean existsByIdAndStatusTrue(Long id);

    List<Account> findByClientId(Long clientId);

}
