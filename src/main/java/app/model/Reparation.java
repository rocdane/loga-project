package app.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@ToString
@Entity
@Table(name = "tache")
public class Reparation implements Serializable
{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "description")
    private String description;

    @Column(name = "cout")
    private float cout;

    @Column(name = "temps")
    private float temps;

    @Column(name = "taux_horaire")
    private float tauxHoraire;

    @ManyToOne
    @JoinColumn(name="reparation",referencedColumnName="id",nullable=false)
    private Repair repair;

    public Reparation() {
    }

    public Reparation(String description, float cout) {
        this.description = description;
        this.cout = cout;
    }
}
