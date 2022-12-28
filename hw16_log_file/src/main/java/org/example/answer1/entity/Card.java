package org.example.answer1.entity;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "Card")
public class Card extends BaseEntity<Long> {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "cvv2")
    private Integer cvv2;

    @Column(name = "expiration_date")
    private Long expirationDate;

    public Card(String cardNumber, Integer cvv2, Long expirationDate) {
        this.cardNumber = cardNumber;
        this.cvv2 = cvv2;
        this.expirationDate = expirationDate;
    }

    public Card() {
    }
}
