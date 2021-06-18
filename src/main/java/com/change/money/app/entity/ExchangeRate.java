package com.change.money.app.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeRate {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "originCurrency")
    private String originCurrency;

    @Column(name = "destinationCurrency")
    private String destinationCurrency;

    @Column(name = "value")
    private String value;
}
