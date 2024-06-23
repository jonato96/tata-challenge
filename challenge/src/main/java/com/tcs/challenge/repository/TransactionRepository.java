package com.tcs.challenge.repository;

import com.tcs.challenge.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Modifying
    @Query("UPDATE Transaction t SET t.status = false WHERE t.id = :id")
    void inactivateTransaction(@Param("id") Long id);

    boolean existsByIdAndStatusTrue(Long id);

    @Query("select t from Transaction t where t.account.accountNumber = :accountNumber")
    Optional<List<Transaction>> findAllByAccount(@Param("accountNumber") String accountNumber);

}
