package com.tcs.challenge.repository;

import com.tcs.challenge.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Modifying
    @Query("UPDATE Transaction t SET t.state = false WHERE t.id = :id")
    void inactivateMovement(@Param("id") Long id);


}
