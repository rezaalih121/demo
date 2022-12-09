package edu.rezaali.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Competence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({ViewUtilisateur.class, ViewCompetence.class})
    private Integer id;
    @Column(nullable = false, unique = true)
    @JsonView({ViewUtilisateur.class, ViewCompetence.class})
    private String nom;

    // comes from Utilisateur class
    @ManyToMany(mappedBy = "listeCompetence")
    @JsonView( ViewCompetence.class)
    private Set<Utilisateur> listeUtilisateur = new HashSet<>();

    public Competence() {
    }

    public Competence(String nom) {
        this.id = id;
        this.nom = nom;
    }
}
