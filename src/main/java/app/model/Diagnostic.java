package app.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "diagnostic")
public class Diagnostic implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "description")
    private String description;

    @Column(name = "compteur")
    private Integer compteur;

    @Column(name = "date_creation")
    @Temporal(TemporalType.DATE)
    private Date dateCreation;

    @ManyToOne
    @JoinColumn(name="profile",referencedColumnName="id")
    private Profile profile;

    @ManyToOne
    @JoinColumn(name="dossier",referencedColumnName="id",nullable=false)
    private Dossier dossier;

    @OneToMany(cascade={CascadeType.PERSIST, CascadeType.REMOVE},mappedBy = "diagnostic")
    private List<Default> defaults = new ArrayList<>();

    @OneToMany(cascade={CascadeType.PERSIST, CascadeType.REMOVE},mappedBy = "diagnostic")
    private List<Solution> solutions = new ArrayList<>();

    public void addDefault (Default defaut) {
        defaut.setDiagnostic(this);
        defaults.add(defaut);
    }

    public void addSolution(Solution solution){
        solution.setDiagnostic(this);
        this.solutions.add(solution);
    }
}
