package com.loga.microservices.wms.entity;

import jakarta.persistence.*;
import lombok.*;

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
@Table(name = "invoice")
public class Invoice {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "reference", length = 50, unique = true)
    private String reference;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "dossier")
    private String dossier;

    @OneToMany(targetEntity = Item.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "invoice", referencedColumnName = "id")
    private List<Item> items = new ArrayList<>();

    @ManyToOne(targetEntity = Workshop.class)
    @JoinColumn(name = "workshop", referencedColumnName = "id")
    private Workshop workshop;

    @OneToMany(targetEntity = Payment.class, cascade=CascadeType.ALL)
    @JoinColumn(name = "invoice", referencedColumnName = "id")
    private List<Payment> payments = new ArrayList<>();
}
