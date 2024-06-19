package com.tcs.challenge.entity;

import com.tcs.challenge.helper.MovementType;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "movement")
public class Movement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "movement_type")
    @Enumerated(EnumType.STRING)
    private MovementType movementType;

    @Column(name= "amount")
    private BigDecimal amount;

    @Column(name="balance")
    private BigDecimal balance;

    @Column(name = "account_id")
    private Long accountId;

    @Column(name = "status")
    private boolean status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", insertable = false, updatable = false)
    private Account account;

}
