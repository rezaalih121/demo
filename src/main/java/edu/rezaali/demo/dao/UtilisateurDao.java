package edu.rezaali.demo.dao;

import edu.rezaali.demo.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilisateurDao extends JpaRepository<Utilisateur , Integer> {

    Optional<Utilisateur>  findUtilisateurByFirstnameAndLastname (String Firstname , String Lastname);

}
