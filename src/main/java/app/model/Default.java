package app.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "defaut")
public class Default implements Serializable
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "code")
    private String code;
    @Enumerated(EnumType.STRING)
    @Column(name = "libelle")
    private Groupe libelle;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name="diagnostic",referencedColumnName="id",nullable=false)
    private Diagnostic diagnostic;

    /**
     * énumération des groupes de libelle::Defaut
     * MOTOPROPULSEUR : Tout défaut lié aux organes(moteur,transmission) de propulsions de l'automobile
     * CHASSIS : Tout défaut lié aux organes(suspensions,direction,freins) de l'ergonomie du véhicule
     * CARROSSERIE : Tout défaut lié aux organes(portes,par-brise) de l'habitacle du véhicule
     * CAN : Tout défaut lié aux organes du réseau de communication du véhicule
     */
    public enum Groupe{
        MOTOPROPULSEUR,CHASSIS,CARROSSERIE,CAN
    }

    public Default(String code, Groupe libelle, String description) {
        this.code = code;
        this.libelle = libelle;
        this.description = description;
    }
}
