package app.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "automobile")
@AllArgsConstructor
@Getter
@Setter
public class Automobile implements Serializable
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "immatriculation",unique = true)
    private String immatriculation;

    @Column(name = "chassis",length = 17,unique = true)
    private String chassis;

    @Column(name = "marque")
    private String marque;

    @Column(name = "modele")
    private String modele;

    @Column(name = "type_compteur")
    private String typeCompteur;

    @Column(name = "compteur")
    private int compteur;

    @Column(name = "puissance")
    private String puissance;

    @Column(name = "cylindre")
    private String cylindre;

    @Column(name = "transmission")
    private String transmission;

    @Column(name = "pmec")
    private String pmec;

    @ManyToOne
    @JoinColumn(name="client",referencedColumnName="id")
    private Client client;

    @OneToOne(cascade = {CascadeType.PERSIST,CascadeType.REMOVE},mappedBy = "automobile")
    private Dossier dossier;
    
    public Automobile() {
    }

    public static Compteur[] getCompteurs() {
        return Compteur.values();
    }

    public enum Compteur
    {
        KM, 
        MPH;
    }
}
