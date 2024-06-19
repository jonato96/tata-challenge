package com.tcs.challenge.repository;

import com.tcs.challenge.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClientRepository extends JpaRepository<Client, Long> {

    @Modifying
    @Query("UPDATE Client c SET c.status = false WHERE c.id = :id")
    void inactivateClient(@Param("id") Long id);

}
