package com.loga.microservices.rms.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "task")
public class Task implements Serializable
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "cost")
    private Float cost;

    @Column(name = "duration")
    private Integer duration;

    public Task(String description, Float cost) {
        this.description = description;
        this.cost = cost;
    }
}
