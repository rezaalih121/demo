package edu.rezaali.demo.dao;

import edu.rezaali.demo.model.Pays;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaysDao extends JpaRepository<Pays , Integer> {

    Optional<Pays>  findPaysByNom (String nom );

}
