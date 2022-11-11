package app.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "profile")
public class Profile implements Serializable
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "contact",length = 50,unique = true)
    private String contact;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "poste")
    private String poste;

    @Column(name = "archived")
    private Boolean archived;

    @Column(name = "birthdate")
    @Temporal(TemporalType.DATE)
    private Date birthdate;

    @Column(name = "profile_path")
    private String profilePath;

    @Column(name = "date_ajout")
    @Temporal(TemporalType.DATE)
    private Date dateAjout;

    @Column(name = "date_archive")
    @Temporal(TemporalType.DATE)
    private Date dateArchive;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "titre",referencedColumnName = "id")
    private Position position;

    @Column(name = "salaire")
    private int salaire;

    @OneToOne(cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinColumn(name = "users",referencedColumnName = "id")
    private User user;

    @OneToMany(cascade = {CascadeType.PERSIST},mappedBy = "profile")
    private List<Evaluation> evaluations = new ArrayList<>();

    @OneToMany(cascade = {CascadeType.PERSIST},mappedBy = "profile")
    private List<Salary> salaries = new ArrayList<>();

    @OneToMany(cascade = {CascadeType.PERSIST},mappedBy = "profile")
    private List<Reception> receptions = new ArrayList<>();

    @OneToMany(cascade = {CascadeType.PERSIST},mappedBy = "profile")
    private List<Diagnostic> diagnostics = new ArrayList<>();

    @OneToMany(cascade = {CascadeType.PERSIST},mappedBy = "profile")
    private List<Repair> repairs = new ArrayList<>();

    public Profile() {
    }

    public String toString(){
        return id+" "+nom+" "+prenom;
    }

    public void addEvaluation(Evaluation evaluation){
        evaluation.setProfile(this);
        this.evaluations.add(evaluation);
    }

    public void addSalaire(Salary salary){
        salary.setProfile(this);
        this.salaries.add(salary);
    }

    public void addReception(Reception reception){
        reception.setProfile(this);
        this.receptions.add(reception);
    }

    public void addDiagnostic(Diagnostic diagnostic){
        diagnostic.setProfile(this);
        this.diagnostics.add(diagnostic);
    }

    public void addReparation(Repair repair){
        repair.setProfile(this);
        this.repairs.add(repair);
    }
}
