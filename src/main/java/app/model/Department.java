package app.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "department")
public class Department implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "designation")
    private String designation;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name="atelier",referencedColumnName="id")
    private Atelier atelier;

    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.REMOVE},mappedBy = "department")
    private List<Position> positions;

    public String toString(){
        return designation;
    }

    public void addTitre(Position position){
        position.setService(this);
        this.positions.add(position);
    }
}
