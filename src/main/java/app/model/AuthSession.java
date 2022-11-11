package app.model;

import lombok.Getter;
import lombok.Setter;
import vendor.security.Crypto;

import javax.persistence.*;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "sessions")
public class AuthSession implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "host")
    private String host;

    @Column(name = "token",unique = true,length = 128)
    private String token;

    @Column(name = "is_closed")
    private boolean cloded;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date dateCreate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "closed_at")
    private Date dateClose;

    @ManyToOne
    @JoinColumn(name = "users", referencedColumnName = "username")
    private User user;
    
    public AuthSession() {
        this.setToken(Crypto.getInstance().generateToken(128));
    }
}
