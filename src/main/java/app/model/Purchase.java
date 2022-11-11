package app.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "achat")
@XmlRootElement
public class Purchase implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_achat")
    private Date date;

    @Column(name = "fournisseur")
    private String fournisseur;

    @OneToOne
    @JoinColumn(name="article",referencedColumnName="id")
    private Article article;

    @Column(name = "prix")
    private double prix;

    @Column(name = "quantite")
    private int quantite;

    @Column(name = "montant")
    private double montant;
}
