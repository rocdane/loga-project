package app.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "fourniture")
public class Spare implements Serializable
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "designation")
    private String designation;

    @Column(name = "prix")
    private double prix;

    @Column(name = "quantite")
    private int quantite;

    @Column(name = "montant")
    private double montant;

    @Column(name = "is_ordered")
    private Boolean ordered;

    @Column(name = "is_delivered")
    private Boolean delivered;

    @ManyToOne
    @JoinColumn(name="commande",referencedColumnName="id")
    private Order order;

    @ManyToOne
    @JoinColumn(name="livraison",referencedColumnName="id")
    private Delivery delivery;

    @ManyToOne
    @JoinColumn(name="reparation",referencedColumnName="id")
    private Repair repair;

    public String toString(){
        return designation;
    }

    public Spare() {
        setOrdered(false);
        setDelivered(false);
    }
    
    public long getId() {
        return this.id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public String getDesignation() {
        return this.designation;
    }
    
    public void setDesignation(String designation) {
        this.designation = designation;
    }
    
    public double getPrix() {
        return this.prix;
    }
    
    public void setPrix(double prix) {
        this.prix = prix;
    }
    
    public int getQuantite() {
        return this.quantite;
    }
    
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
    
    public double getMontant() {
        return this.montant;
    }
    
    public void setMontant(double montant) {
        this.montant = montant;
    }

    public Boolean getOrdered() {
        return ordered;
    }

    public void setOrdered(Boolean ordered) {
        this.ordered = ordered;
    }

    public Repair getReparation() {
        return repair;
    }

    public void setReparation(Repair repair) {
        this.repair = repair;
    }

    public Boolean getDelivered() {
        return delivered;
    }

    public void setDelivered(Boolean delivered) {
        this.delivered = delivered;
    }

    public Order getCommande() {
        return order;
    }

    public void setCommande(Order order) {
        this.order = order;
    }

    public Delivery getLivraison() {
        return delivery;
    }

    public void setLivraison(Delivery delivery) {
        this.delivery = delivery;
    }
}
