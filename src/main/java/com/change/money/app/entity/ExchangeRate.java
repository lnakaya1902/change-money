package com.change.money.app.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
    private String id;

    @Column(name = "originCurrency")
    private String originCurrency;

    @Column(name = "destinationCurrency")
    private String destinationCurrency;

    @Column(name = "value")
    private String value;
}
