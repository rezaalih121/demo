package edu.rezaali.demo.controller;

import com.fasterxml.jackson.annotation.JsonView;
import edu.rezaali.demo.model.Competence;
import edu.rezaali.demo.service.CompetenceService;
import edu.rezaali.demo.view.ViewCompetence;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class CompetenceController {

    private final CompetenceService    competenceService;

    public CompetenceController(CompetenceService competenceService) {
        this.competenceService = competenceService;
    }

    @GetMapping("/competence-list")
    @JsonView(ViewCompetence.class)
    public ResponseEntity<List<Competence>> getCompetences(){
       return ResponseEntity.ok(competenceService.getCompetences());
    }

    @GetMapping("/competence/{id}")
    @JsonView(ViewCompetence.class)
    public ResponseEntity getCompetence(@PathVariable int id){
        return competenceService.getCompetenceById(id);
    }

    @GetMapping("/competence-byname/{nom}")
    public ResponseEntity<Competence> getCompetence(@PathVariable String nom){
        return competenceService.getCompetenceByNom(nom);
    }

    @PostMapping("/competence")
    public ResponseEntity<Competence> addCompetence(@RequestBody Competence competence){

        return  competenceService.addCompetence(competence);
    }

    @DeleteMapping("/competence/{id}")
    public ResponseEntity<Competence>  deleteCompetence(@PathVariable int id){
        return competenceService.deleteCompetence(id);
    }

    @PutMapping("/competence/{id}/{nom}")
    public ResponseEntity<Competence>  uptdateCompetence(
            @PathVariable(required = true) int id,
            @PathVariable(required = false) String nom
            ){
        return competenceService.updateCompetence(id,nom);
    }
}
