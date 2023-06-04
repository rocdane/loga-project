package com.loga.microservices.datamanagementservices.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "dossiers")
public class Dossier implements Serializable
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "reference",unique = true, length = 100)
    private String reference;

    @Column(name = "open_at")
    private Date openAt;

    @Column(name = "updated_at")
    private Date updatedAt;
}
