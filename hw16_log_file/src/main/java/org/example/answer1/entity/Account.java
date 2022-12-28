package org.example.answer1.entity;


import lombok.*;
import org.example.answer1.entity.enumeration.AccountType;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "Account")
public class Account extends BaseEntity<Long> {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "create_date")
    private Long createDate;

    @Column(name = "account_type")
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @Column(name = "balance")
    private Long balance;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Card_id")
    private Card card;

    public Account() {
    }

    public Account(String accountNumber, Long createDate, AccountType accountType, Long balance, Card card) {
        this.accountNumber = accountNumber;
        this.createDate = createDate;
        this.accountType = accountType;
        this.balance = balance;
        this.card = card;
    }
}
