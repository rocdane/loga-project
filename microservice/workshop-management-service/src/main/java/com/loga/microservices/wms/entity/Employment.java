package com.loga.microservices.wms.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employment")
public class Employment {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "reference",unique = true)
    private String reference;

    @Column(name = "position")
    private String position;

    @Column(name = "salary")
    private BigDecimal salary;

    @Column(name = "hire_at")
    private Date hireAt;

    @Column(name = "fire_at")
    private Date fireAt;

    @OneToOne(targetEntity = Employee.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "employee", referencedColumnName = "id")
    private Employee employee;

    @OneToOne(targetEntity = Company.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "company", referencedColumnName = "id")
    private Company company;
}
