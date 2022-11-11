package app.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "qualite")
public class Quality implements Serializable
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "compteur")
    private Integer compteur;

    @Column(name = "commentaire")
    private String commentaire;

    @Column(name = "date_creation")
    @Temporal(TemporalType.DATE)
    private Date dateCreation;

    @OneToOne(cascade={CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name="reparation",referencedColumnName="id",nullable=false)
    private Repair repair;

    @OneToMany(mappedBy = "qualite")
    private List<Control> controls;

    public long getId() {
        return this.id;
    }
    
    public void setId(long id) {
        this.id = id;
    }

    public Integer getCompteur() {
        return compteur;
    }

    public void setCompteur(Integer compteur) {
        this.compteur = compteur;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Repair getReparation() {
        return repair;
    }

    public void setReparation(Repair repair) {
        this.repair = repair;
    }

    public List<Control> getControles() {
        return this.controls;
    }
    
    public void setControles(List<Control> controls) {
        this.controls = controls;
    }

    public void addControle(Control control){
        control.setQualite(this);
        this.controls.add(control);
    }
    
    public Date getDateCreation() {
        return this.dateCreation;
    }
    
    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }
}
