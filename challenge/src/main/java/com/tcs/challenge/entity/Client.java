package com.tcs.challenge.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "client")
@PrimaryKeyJoinColumn(name = "client_id")
public class Client extends Person {

    @Column(name = "password")
    private String password;

    @Column(name = "status")
    private boolean status;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private List<Account> accountList;

}
