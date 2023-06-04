package com.loga.microservices.sms.entity;

import lombok.*;
import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "delivery")
public class Delivery implements Serializable
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name="order",referencedColumnName="id",nullable=false)
    private Order order;

    @OneToMany(targetEntity = Item.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery", referencedColumnName = "id")
    private List<Item> items = new ArrayList<>();

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "created_at")
    private Date createdAt;
}
