package com.tcs.challenge.entity;

import com.tcs.challenge.helper.TransactionType;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "created_date")
    private LocalDate date;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @Column(name= "amount")
    private BigDecimal amount;

    @Column(name = "after_balance")
    private BigDecimal afterBalance;

    @Column(name = "before_balance")
    private BigDecimal beforeBalance;

    @Column(name = "state")
    private boolean state;

    @Column(name = "account_id")
    private Long accountId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", insertable = false, updatable = false)
    private Account account;

}
