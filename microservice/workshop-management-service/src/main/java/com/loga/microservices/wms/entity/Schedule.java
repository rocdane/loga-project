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
@Table(name = "schedule")
public class Schedule implements Serializable {
	
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "start_at")
    private Date startAt;

    @Column(name = "end_at")
    private Date endAt;

    @Column(name = "status")
    private Boolean status;
}
