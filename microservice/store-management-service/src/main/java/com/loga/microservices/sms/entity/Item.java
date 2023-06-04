package com.loga.microservices.sms.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "item")
public class Item implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(targetEntity = Product.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "product", referencedColumnName = "id")
    private Product product;

    @Column(name = "cost")
    private BigDecimal cost;

    @Column(name = "quantity")
    private Float quantity;

    @Column(name = "is_ordered")
    private Boolean ordered;

    @Column(name = "is_delivered")
    private Boolean delivered;
}