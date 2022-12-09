package edu.rezaali.demo.service;

import edu.rezaali.demo.dao.PaysDao;
import edu.rezaali.demo.model.Pays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PaysService {
    private final PaysDao paysDao;

    @Autowired
    public PaysService(PaysDao paysDao) {
        this.paysDao = paysDao;
    }

    public List<Pays> getPayss() {
        return paysDao.findAll();
    }

    public ResponseEntity<Pays>  getPaysById(Integer id) {
        Optional<Pays> paysExist = paysDao.findById(id);

        if(paysExist.isPresent()){
            return new ResponseEntity<>(paysExist.get(),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    public ResponseEntity<Pays> getPaysByNom(String nom) {
        Optional<Pays> paysExist =  paysDao.findPaysByNom(nom);

        if(paysExist.isPresent()){
            return new ResponseEntity<>(paysExist.get(),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Pays> addPays(Pays pays) {

        if (pays.getId() != null) {
            Optional<Pays> paysExist =  paysDao.findById(pays.getId());//paysDao.findPaysByNom(pays.getNom());

            if (paysExist.isPresent()) {
                paysDao.save(pays);
                return new ResponseEntity<>(pays, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            paysDao.save(pays);
            return new ResponseEntity<>(pays, HttpStatus.CREATED);
        }

    }

    public ResponseEntity<Pays> deletePays(int id) {

        Optional<Pays> paysExist = paysDao.findById(id);

        if (paysExist.isPresent()) {
            paysDao.deleteById(id);
            return new ResponseEntity<>(paysExist.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    }


    public ResponseEntity<Pays> updatePays(int id , String nom ){

        if(!paysDao.findById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            Pays pays = paysDao.findById(id).get();


            if(nom != null && !Objects.equals(pays.getNom(),nom)) {
                pays.setNom(nom);
            }
            paysDao.save(pays);
            return new ResponseEntity<>(pays, HttpStatus.OK);
        }
    }

}
