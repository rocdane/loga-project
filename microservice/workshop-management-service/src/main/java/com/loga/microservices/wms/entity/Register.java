package com.loga.microservices.wms.entity;

import jakarta.persistence.*;
import lombok.*;

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
@Table(name = "register")
public class Register implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "created_at",unique = true)
    private Date createdAt;

    @OneToMany(cascade={CascadeType.PERSIST, CascadeType.REMOVE},targetEntity = Recipe.class, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "register", referencedColumnName = "id")
    private List<Recipe> recipes = new ArrayList<>();

    @JoinColumn(name = "register", referencedColumnName = "id")
    @OneToMany(cascade={CascadeType.PERSIST, CascadeType.REMOVE},targetEntity = Expense.class, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Expense> expenses = new ArrayList<>();
}
