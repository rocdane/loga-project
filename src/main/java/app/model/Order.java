package app.model;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "commande")
public class Order implements Serializable
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne
    @JoinColumn(name="fournisseur",referencedColumnName="id",nullable=false)
    private Furnisher furnisher;

    @OneToMany(mappedBy = "commande")
    private List<Spare> spares;

    @Column(name = "quantite")
    private int quantite;

    @Column(name = "montant")
    private double montant;

    @Column(name = "date_creation")
    @Temporal(TemporalType.DATE)
    private Date dateCreation;

    public Order() {
    }

    public String toString(){
        return getId()+" "+ furnisher.getRaisonSociale()+" "+new SimpleDateFormat("dd-MM-yyyy").format(getDateCreation());
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Furnisher getFournisseur() {
        return furnisher;
    }

    public void setFournisseur(Furnisher furnisher) {
        this.furnisher = furnisher;
    }

    public List<Spare> getFournitures() {
        return spares;
    }

    public void setFournitures(List<Spare> spares) {
        this.spares = spares;
    }

    public int getQuantite() {
        int total = 0;
        for (Spare spare : spares) {
            total+= spare.getQuantite();
        }
        quantite = total;
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public double getMontant() {
        double total=0;
        for (Spare spare : spares) {
            total+= spare.getMontant();
        }
        return total;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }
}
