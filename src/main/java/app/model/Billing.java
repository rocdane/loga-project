package app.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "versement")
public class Billing implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "date_versement")
    @Temporal(TemporalType.DATE)
    private Date dateVersement;

    @Enumerated(EnumType.STRING)
    @Column(name = "mode")
    private Mode mode;

    @Column(name = "reference")
    private String reference;

    @Column(name = "versement")
    private Double versement;

    @ManyToOne
    @JoinColumn(name="reparation",referencedColumnName="id")
    private Repair repair;

    public enum Mode{
        ESPECE,CHEQUE,MOBILE
    }
}
