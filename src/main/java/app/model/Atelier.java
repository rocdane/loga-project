package app.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "atelier")
public class Atelier implements Serializable
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "raison_sociale")
    private String raisonSociale;

    @Column(name = "mention_legale")
    private String mentionLegale;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "contact")
    private String contact;

    @OneToMany(cascade={CascadeType.PERSIST, CascadeType.REMOVE},mappedBy = "atelier")
    private List<Space> spaces = new ArrayList<>();

    @OneToMany(cascade={CascadeType.PERSIST, CascadeType.REMOVE},mappedBy = "atelier")
    private List<Department> departments = new ArrayList<>();

    @OneToMany(cascade={CascadeType.PERSIST, CascadeType.REMOVE},mappedBy = "atelier")
    private List<Finance> finances = new ArrayList<>();

    @OneToMany(cascade={CascadeType.PERSIST, CascadeType.REMOVE},mappedBy = "atelier")
    private List<Article> articles = new ArrayList<>();

    @OneToMany(cascade={CascadeType.PERSIST, CascadeType.REMOVE},mappedBy = "atelier")
    private List<Immobility> immobilities = new ArrayList<>();

    public void addEspace(Space space){
        space.setAtelier(this);
        this.spaces.add(space);
    }

    public void addService(Department department){
        department.setAtelier(this);
        this.departments.add(department);
    }

    public void addTresor(Finance finance){
        finance.setAtelier(this);
        this.finances.add(finance);
    }

    public void addArticle(Article article){
        article.setAtelier(this);
        this.articles.add(article);
    }

    public void addImmobilisation(Immobility immobility){
        immobility.setAtelier(this);
        this.immobilities.add(immobility);
    }
}
