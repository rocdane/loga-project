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
@Table(name = "assets")
public class Assets implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "category")
    private String category;

    @Column(name = "designation")
    private String designation;

    @Column(name = "entry_date")
    private Date entryDate;

    @Column(name = "depreciation_rate")
    private Float depreciationRate;
}
