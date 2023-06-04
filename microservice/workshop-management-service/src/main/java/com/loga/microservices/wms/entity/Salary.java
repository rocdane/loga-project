package com.loga.microservices.wms.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "salary")
public class Salary implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "prime")
    private Integer prime;

    @Column(name = "indemnity")
    private Integer indemnity;

    @Column(name = "additional")
    private Integer additional;

    @Column(name = "tax")
    private Integer tax;

    @Column(name = "mode")
    private String mode;

    @Column(name = "reference", length = 50, unique = true)
    private String reference;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "date_salary")
    private Date dateSalary;

    @OneToOne(targetEntity = Employee.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "employee", referencedColumnName = "id")
    private Employee employee;
}
