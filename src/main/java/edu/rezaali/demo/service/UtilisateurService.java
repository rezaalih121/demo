package edu.rezaali.demo.service;

import com.sun.net.httpserver.Headers;
import edu.rezaali.demo.dao.UtilisateurDao;
import edu.rezaali.demo.model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UtilisateurService {
    private final UtilisateurDao utilisateurDao;

    @Autowired
    public UtilisateurService(UtilisateurDao utilisateurDao) {
        this.utilisateurDao = utilisateurDao;
    }

    public List<Utilisateur> getUtilisateurs() {
        return utilisateurDao.findAll();
    }

    public ResponseEntity<Utilisateur>  getUtilisateurById(Integer id) {
        Optional<Utilisateur> utilisateurExist = utilisateurDao.findById(id);

        if(utilisateurExist.isPresent()){
            return new ResponseEntity<>(utilisateurExist.get(),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    public ResponseEntity<Utilisateur> getUtilisateurByFirstnameAndLastname(String firstname, String lastname) {
        Optional<Utilisateur> utilisateurExist =  utilisateurDao.findUtilisateurByFirstnameAndLastname(firstname, lastname);

        if(utilisateurExist.isPresent()){
            return new ResponseEntity<>(utilisateurExist.get(),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Utilisateur> addUtilisateur(Utilisateur utilisateur) {

        if (utilisateur.getId() != null) {
            Optional<Utilisateur> utilisateurExist =  utilisateurDao.findById(utilisateur.getId());//utilisateurDao.findUtilisateurByFirstnameAndLastname(utilisateur.getFirstname(), utilisateur.getLastname());
            if (utilisateurExist.isPresent()) {
                utilisateurDao.save(utilisateur);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            utilisateurDao.save(utilisateur);
            return new ResponseEntity<>(utilisateur, HttpStatus.CREATED);
        }

    }

    public ResponseEntity<Utilisateur> deleteUtilisateur(int id) {

        Optional<Utilisateur> utilisateurExist = utilisateurDao.findById(id);

        if (utilisateurExist.isPresent()) {
            utilisateurDao.deleteById(id);
            return new ResponseEntity<>(utilisateurExist.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    }


    public ResponseEntity<Utilisateur> updateUtilisateur(int id , String firstname , String lastname){

        if(!utilisateurDao.findById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            Utilisateur utilisateur = utilisateurDao.findById(id).get();


            if(firstname != null && !Objects.equals(utilisateur.getFirstname(),firstname)) {
                utilisateur.setFirstname(firstname);
            }
            if(lastname != null && !Objects.equals(utilisateur.getLastname(),lastname)) {
                utilisateur.setLastname(lastname);
            }
            utilisateurDao.save(utilisateur);
            return new ResponseEntity<>(utilisateur, HttpStatus.OK);
        }
    }

}
