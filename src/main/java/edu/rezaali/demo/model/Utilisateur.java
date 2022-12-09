package edu.rezaali.demo.model;

import com.fasterxml.jackson.annotation.JsonView;
import edu.rezaali.demo.view.ViewCompetence;
import edu.rezaali.demo.view.ViewUtilisateur;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({ViewUtilisateur.class , ViewCompetence.class})
    private Integer id;
    @Column(nullable = false, unique = false)
    @JsonView({ViewUtilisateur.class , ViewCompetence.class})
    private String firstname;
    @Column(nullable = false, length = 121)
    @JsonView({ViewUtilisateur.class , ViewCompetence.class})
    private String lastname;

    @ManyToOne(optional = false)
    @JsonView(ViewUtilisateur.class)
    private Pays pays;

    @ManyToMany
    @JoinTable(
            name = "utilisateur_competence",
            joinColumns = @JoinColumn(name = "utilisateur_id"),
            inverseJoinColumns = @JoinColumn(name = "competence_id")
    )
    @JsonView(ViewUtilisateur.class)
    private Set<Competence> listeCompetence = new HashSet<>();

    public Utilisateur() {
    }

    public Utilisateur(String firstname, String lastname, Integer id) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
    }
}
