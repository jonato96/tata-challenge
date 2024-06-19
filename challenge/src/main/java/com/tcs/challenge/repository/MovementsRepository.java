package com.tcs.challenge.repository;

import com.tcs.challenge.entity.Movement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MovementsRepository extends JpaRepository<Movement, Long> {
    @Modifying
    @Query("UPDATE Movement m SET m.status = false WHERE m.id = :id")
    void inactivateMovement(@Param("id") Long id);


}
