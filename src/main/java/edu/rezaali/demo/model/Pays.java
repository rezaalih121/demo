package edu.rezaali.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
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
public class Pays {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(ViewUtilisateur.class)
    private Integer id;
    @Column(nullable = false, unique = true)
    @JsonView(ViewUtilisateur.class)
    private String nom;

    @OneToMany(mappedBy = "pays")
    @JsonIgnore
    private Set<Utilisateur> listeUtilisateur = new HashSet<>();


    public Pays() {
    }

    public Pays(String nom) {
        this.id = id;
        this.nom = nom;
    }
}
