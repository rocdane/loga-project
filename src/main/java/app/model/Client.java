package app.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "client")
public class Client implements Serializable
{
    @Id
    @GeneratedValue (strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "raison_sociale")
    private String raisonSociale;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private Type type;

    @Column(name = "mention_legale")
    private String mentionLegale;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "ville")
    private String ville;

    @Column(name = "contact")
    private String contact;

    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.REMOVE},mappedBy = "client")
    protected List<Automobile> automobiles = new ArrayList<>();

    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.REMOVE},mappedBy = "client")
    protected List<Dossier> dossiers = new ArrayList<>();

    public enum Type{
        HOMME,FEMME,SOCIETE
    }

    public static Type[] getTypes(){
        return Type.values();
    }

    public String toString(){
        return id+raisonSociale;
    }
}
