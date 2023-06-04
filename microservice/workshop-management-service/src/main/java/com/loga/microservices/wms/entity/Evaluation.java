package com.loga.microservices.wms.entity;

import lombok.*;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "evaluation")
public class Evaluation implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "grade")
    private Integer grade;

    @Column(name = "observation")
    private String observation;

    @ManyToOne(targetEntity = Employee.class, cascade = {CascadeType.PERSIST},fetch = FetchType.LAZY)
    @JoinColumn(name = "employee", referencedColumnName = "id")
    private Employee employee;

    @OneToMany(targetEntity = Criteria.class,cascade={CascadeType.PERSIST, CascadeType.REMOVE},fetch = FetchType.LAZY)
    @JoinColumn(name = "evaluation", referencedColumnName = "id")
    private List<Criteria> criteria = new ArrayList<>();

    public void setCriteres(List<Criteria> criteria) {
        for (Criteria item : criteria) {
            item.setEvaluation(this);
        }
        this.criteria = criteria;
    }

    public void addCriteria(Criteria criteria){
        this.criteria.add(criteria);
    }
}
