package app.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "livraison")
public class Delivery implements Serializable
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @OneToOne
    @JoinColumn(name="commande",referencedColumnName="id",nullable=false)
    private Order order;

    @OneToMany(mappedBy = "livraison")
    private List<Spare> spares;

    @Column(name = "quantite")
    private int quantite;

    @Column(name = "montant")
    private double montant;

    @Column(name = "date_creation")
    @Temporal(TemporalType.DATE)
    private Date dateCreation;

    public Delivery() {
    }

    public long getId() {
        return this.id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public Order getCommande() {
        return this.order;
    }
    
    public void setCommande(Order order) {
        this.order = order;
    }

    public List<Spare> getFournitures() {
        return spares;
    }

    public void setFournitures(List<Spare> spares) {
        this.spares = spares;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public Date getDateCreation() {
        return this.dateCreation;
    }
    
    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }
}
