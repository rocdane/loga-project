package com.loga.microservices.wms.entity;

import lombok.*;
import jakarta.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "criteria")
public class Criteria implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "reference")
    private String reference;

    @Column(name = "designation")
    private String designation;

    @Column(name = "marking")
    private Integer marking;

    @Column(name = "mark")
    private Integer mark;

    @ManyToOne(targetEntity = Evaluation.class,fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST})
    @JoinColumn(name="evaluation",referencedColumnName="id")
    private Evaluation evaluation;
}
