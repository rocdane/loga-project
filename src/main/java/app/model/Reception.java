package app.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "reception")
public class Reception implements Serializable
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "compteur")
    private Integer compteur;

    @Column(name = "commentaire")
    private String commentaire;

    @Column(name = "observation")
    private String observation;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreation;

    @ManyToOne
    @JoinColumn(name="profile",referencedColumnName="id")
    private Profile profile;

    @ManyToOne
    @JoinColumn(name="dossier",referencedColumnName="id",nullable=false)
    private Dossier dossier;

    @OneToMany(cascade={CascadeType.PERSIST, CascadeType.REMOVE},mappedBy = "reception")
    private List<Notice> notices = new ArrayList<>();

    public void addNotice(Notice notice){
        notice.setReception(this);
        this.notices.add(notice);
    }

    public void removeNotice(Notice notice){
        this.notices.remove(notice);
    }
}
