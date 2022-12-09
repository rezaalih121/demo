package edu.rezaali.demo.controller;

import com.fasterxml.jackson.annotation.JsonView;
import edu.rezaali.demo.model.Utilisateur;
import edu.rezaali.demo.service.UtilisateurService;
import edu.rezaali.demo.view.ViewUtilisateur;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class UtilisateurController {

    private final UtilisateurService    utilisateurService;

    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @GetMapping("/user-list")
    @JsonView(ViewUtilisateur.class)
    public ResponseEntity<List<Utilisateur>> getUtilisateurs(){
       return ResponseEntity.ok(utilisateurService.getUtilisateurs());
    }

    @GetMapping("/user/{id}")
    @JsonView(ViewUtilisateur.class)
    public ResponseEntity getUtilisateur(@PathVariable int id){
        return utilisateurService.getUtilisateurById(id);
    }

    @GetMapping("/user/{firstname}/{lastname}")
    @JsonView(ViewUtilisateur.class)
    public ResponseEntity<Utilisateur> getUtilisateur(@PathVariable String firstname, @PathVariable String lastname ){
        return utilisateurService.getUtilisateurByFirstnameAndLastname(firstname,lastname);
    }

    @PostMapping("/user")
    public ResponseEntity<Utilisateur> addUtilisateur(@RequestBody Utilisateur utilisateur){

        return  utilisateurService.addUtilisateur(utilisateur);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Utilisateur>  deleteUtilisateur(@PathVariable int id){
        return utilisateurService.deleteUtilisateur(id);
    }

    @PutMapping("/user/{id}/{firstname}/{lastname}")
    public ResponseEntity<Utilisateur>  uptdateUtilisateur(
            @PathVariable(required = true) int id,
            @PathVariable(required = false) String firstname,
            @PathVariable(required = false) String lastname
            ){
        return utilisateurService.updateUtilisateur(id,firstname,lastname);
    }
}
