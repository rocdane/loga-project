package app.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "dossier")
public class Dossier implements Serializable
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "reference",unique = true, length = 100)
    private String reference;

    @Column(name = "open_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOuverture;

    @Column(name = "update_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateMaj;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "client",referencedColumnName = "id")
    private Client client;

    @OneToOne(cascade={CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinColumn(name = "automobile",referencedColumnName = "id")
    private Automobile automobile;

    @OneToMany(mappedBy = "dossier")
    private List<Reception> receptions = new ArrayList<>();

    @OneToMany(mappedBy = "dossier")
    private List<Diagnostic> diagnostics = new ArrayList<>();

    @OneToMany(mappedBy = "dossier")
    private List<Repair> repairs = new ArrayList<>();

    public String toString(){
        return automobile.getImmatriculation()+" / "+automobile.getClient().getRaisonSociale();
    }
}
