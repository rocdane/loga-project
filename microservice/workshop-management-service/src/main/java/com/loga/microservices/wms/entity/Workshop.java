package com.loga.microservices.wms.entity;

import lombok.*;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "workshop")
public class Workshop implements Serializable
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "contact")
    private String contact;

    @Column(name = "ifu")
    private String ifu;

    @OneToMany(targetEntity = Space.class, cascade={CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "workshop", referencedColumnName = "id")
    private List<Space> spaces = new ArrayList<>();

    @OneToMany(targetEntity = Schedule.class, cascade={CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "workshop", referencedColumnName = "id")
    private List<Schedule> schedules = new ArrayList<>();

    @OneToMany(targetEntity = Register.class, cascade={CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "workshop", referencedColumnName = "id")
    private List<Register> registers = new ArrayList<>();
}
