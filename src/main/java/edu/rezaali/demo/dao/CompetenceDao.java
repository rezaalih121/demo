package edu.rezaali.demo.dao;

import edu.rezaali.demo.model.Competence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompetenceDao extends JpaRepository<Competence , Integer> {

    Optional<Competence>  findCompetenceByNom (String nom );

}
