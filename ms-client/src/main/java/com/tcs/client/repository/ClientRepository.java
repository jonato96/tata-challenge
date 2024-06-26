package com.tcs.client.repository;

import com.tcs.client.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {

    @Modifying
    @Query("UPDATE Client c SET c.status = false WHERE c.id = :id")
    void inactivateClient(@Param("id") Long id);

    Optional<Client> findByIdAndStatusTrue(Long id);

    boolean existsByIdAndStatusTrue(Long id);

}
