package com.tcs.challenge.entity;

import jakarta.persistence.*;
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private Long id;

    @Column(name = "password")
    private String password;

    @Column(name = "status")
    private boolean status;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private List<Account> accountList;

}
