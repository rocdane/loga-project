package com.loga.microservices.wms.entity;

import lombok.*;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employee")
public class Employee implements Serializable
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "surname")
    private String surname;

    @Column(name = "name")
    private String name;

    @Column(name = "contact", unique = true, length = 50)
    private String contact;

    @Column(name = "address")
    private String address;

    @Column(name = "birthdate")
    private Date birthdate;

    @Column(name = "picture")
    private String picture;

    @Column(name = "is_archived")
    private Boolean archived;

    public Employee(String surname, String name, String contact){
        this.surname = surname;
        this.name = name;
        this.contact = contact;
    }
}
