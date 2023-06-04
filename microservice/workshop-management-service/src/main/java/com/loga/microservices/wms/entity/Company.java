package com.loga.microservices.wms.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "company")
public class Company {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "headquarter")
    private String headquarter;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "legal_notice")
    private String legalNotice;

    @OneToMany(targetEntity = Workshop.class, cascade={CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "company", referencedColumnName = "id")
    private List<Workshop> workshops = new ArrayList<>();

    @OneToMany(targetEntity = Department.class, cascade={CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "company", referencedColumnName = "id")
    private List<Department> departments = new ArrayList<>();

    @OneToMany(targetEntity = Assets.class, cascade={CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "company", referencedColumnName = "id")
    private List<Assets> assets = new ArrayList<>();
}

