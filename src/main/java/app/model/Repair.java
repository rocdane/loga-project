package app.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "reparation")
public class Repair implements Serializable
{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "reference")
    private String reference;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreation;

    @Column(name = "description")
    private String description;

    @Column(name = "compteur")
    private Integer compteur;

    @Column(name = "is_approved")
    private Boolean approved;

    @Column(name = "total_fourniture")
    private Double totalFourniture;

    @Column(name = "total_tache")
    private Double totalTache;

    @Column(name = "total")
    private Double total;

    @Column(name = "total_lettre")
    private String totalLettre;

    @Column(name = "is_billed")
    private Boolean billed;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name="dossier",referencedColumnName="id",nullable=false)
    private Dossier dossier;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name="profile",referencedColumnName="id")
    private Profile profile;

    @OneToMany(cascade={CascadeType.PERSIST, CascadeType.REMOVE},mappedBy="reparation")
    private List<Reparation> reparations;

    @OneToMany(cascade={CascadeType.PERSIST, CascadeType.REMOVE},mappedBy = "reparation")
    private List<Spare> spares;

    @OneToMany(cascade={CascadeType.PERSIST, CascadeType.REMOVE},mappedBy="reparation")
    private List<Billing> billings;

    @OneToOne(cascade={CascadeType.PERSIST, CascadeType.REMOVE},mappedBy = "reparation")
    private Quality quality;

    public String toString(){
        return getId()+" "+getDossier().getAutomobile().getImmatriculation()+" "+getDossier().getAutomobile().getClient().getRaisonSociale()+" "+ new SimpleDateFormat("dd-MM-yyyy").format(getDateCreation());
    }

    public Double getTotalFourniture() {
        for (Spare spare : this.getSpares()) {
            this.totalFourniture += spare.getMontant();
        }
        return this.totalFourniture;
    }

    public Double getTotalTache() {
        for (Reparation reparation : this.getReparations()) {
            this.totalTache += reparation.getCout();
        }
        return this.totalTache;
    }

    public void addTache(Reparation reparation){
        reparation.setRepair(this);
        this.reparations.add(reparation);
    }

    public void addFourniture(Spare spare){
        spare.setReparation(this);
        this.spares.add(spare);
    }

    public Double getSolde(){
        double solde = 0;
        for (Billing billing : billings) {
            solde+= billing.getVersement();
        }
        return solde;
    }

    public void addVersement(Billing billing){
        billing.setRepair(this);
        this.billings.add(billing);
    }
}
