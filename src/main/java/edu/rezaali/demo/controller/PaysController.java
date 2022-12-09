package edu.rezaali.demo.controller;

import edu.rezaali.demo.model.Pays;
import edu.rezaali.demo.service.PaysService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class PaysController {

    private final PaysService    paysService;

    public PaysController(PaysService paysService) {
        this.paysService = paysService;
    }

    @GetMapping("/pays-list")
    public ResponseEntity<List<Pays>> getPayss(){
       return ResponseEntity.ok(paysService.getPayss());
    }

    @GetMapping("/pays/{id}")
    public ResponseEntity getPays(@PathVariable int id){
        return paysService.getPaysById(id);
    }

    @GetMapping("/Pays/{nom}")
    public ResponseEntity<Pays> getPays(@PathVariable String nom){
        return paysService.getPaysByNom(nom);
    }

    @PostMapping("/pays")
    public ResponseEntity<Pays> addPays(@RequestBody Pays pays){

        return  paysService.addPays(pays);
    }

    @DeleteMapping("/pays/{id}")
    public ResponseEntity<Pays>  deletePays(@PathVariable int id){
        return paysService.deletePays(id);
    }

    @PutMapping("/pays/{id}/{nom}")
    public ResponseEntity<Pays>  uptdatePays(
            @PathVariable(required = true) int id,
            @PathVariable(required = false) String nom
            ){
        return paysService.updatePays(id,nom);
    }
}
