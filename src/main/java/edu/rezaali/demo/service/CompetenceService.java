package edu.rezaali.demo.service;

import edu.rezaali.demo.dao.CompetenceDao;
import edu.rezaali.demo.model.Competence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CompetenceService {
    private final CompetenceDao competenceDao;

    @Autowired
    public CompetenceService(CompetenceDao competenceDao) {
        this.competenceDao = competenceDao;
    }

    public List<Competence> getCompetences() {
        return competenceDao.findAll();
    }

    public ResponseEntity<Competence>  getCompetenceById(Integer id) {
        Optional<Competence> competenceExist = competenceDao.findById(id);

        if(competenceExist.isPresent()){
            return new ResponseEntity<>(competenceExist.get(),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    public ResponseEntity<Competence> getCompetenceByNom(String nom) {
        Optional<Competence> competenceExist =  competenceDao.findCompetenceByNom(nom);

        if(competenceExist.isPresent()){
            return new ResponseEntity<>(competenceExist.get(),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Competence> addCompetence(Competence competence) {

        if (competence.getId() != null) {
            Optional<Competence> competenceExist =  competenceDao.findById(competence.getId());//competenceDao.findCompetenceByNom(competence.getNom());

            if (competenceExist.isPresent()) {
                competenceDao.save(competence);
                return new ResponseEntity<>(competence, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            competenceDao.save(competence);
            return new ResponseEntity<>(competence, HttpStatus.CREATED);
        }

    }

    public ResponseEntity<Competence> deleteCompetence(int id) {

        Optional<Competence> competenceExist = competenceDao.findById(id);

        if (competenceExist.isPresent()) {
            competenceDao.deleteById(id);
            return new ResponseEntity<>(competenceExist.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    }


    public ResponseEntity<Competence> updateCompetence(int id , String nom ){

        if(!competenceDao.findById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            Competence competence = competenceDao.findById(id).get();


            if(nom != null && !Objects.equals(competence.getNom(),nom)) {
                competence.setNom(nom);
            }
            competenceDao.save(competence);
            return new ResponseEntity<>(competence, HttpStatus.OK);
        }
    }

}
