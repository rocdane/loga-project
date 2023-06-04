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
@Table(name = "order")
public class Order implements Serializable
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "created_at")
    private Date createdAt;

    @OneToMany(targetEntity = Item.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "order", referencedColumnName = "id")
    private List<Item> items = new ArrayList<>();

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "amount")
    private BigDecimal amount;

    @ManyToOne(targetEntity = Furnisher.class, fetch = FetchType.LAZY)
    @JoinColumn(name="furnisher", referencedColumnName="id" )
    private Furnisher furnisher;
}
