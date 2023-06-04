package com.loga.microservices.wms.entity;

import lombok.*;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "department")
public class Department implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "designation")
    private String designation;

    @OneToMany(cascade = CascadeType.ALL,targetEntity = Position.class,fetch = FetchType.LAZY)
    @JoinColumn(name = "department", referencedColumnName = "id")
    private List<Position> positions = new ArrayList<>();

    public void addPosition(Position position){
        this.positions.add(position);
    }
}
