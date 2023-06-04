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
@Table(name = "space")
public class Space implements Serializable
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code",unique = true,length = 50)
    private String code;

    @Column(name = "name")
    private String name;
}
