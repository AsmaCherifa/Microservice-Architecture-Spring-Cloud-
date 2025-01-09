package tn.itbs.asma.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.itbs.asma.entities.Etiquette;
import tn.itbs.asma.services.EtiquetteService;

@RestController
@RequestMapping("/etiquettes")

public class EtiquetteController {
	@Autowired
	    private  EtiquetteService EtiquetteService;
	 
	 @GetMapping(value="/{id}")
	    public ResponseEntity<Etiquette> getEById(@PathVariable("id") int id) {
	        Optional<Etiquette> etiquette = EtiquetteService.findById(id);
	        if (etiquette.isPresent()) {
	            return ResponseEntity.ok(etiquette.get());
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	        }
	    }
	 
	 @PostMapping("/add")
	    public ResponseEntity<String> addEtiquette(@RequestBody Etiquette etiquette, BindingResult result) {
	        if (result.hasErrors()) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Etiquette data");
	        }
	        try {
	        	EtiquetteService.addEtiquette(etiquette);
	            return ResponseEntity.status(HttpStatus.CREATED).body("Etiquette added successfully");
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding ee " + e.getMessage());
	        }
	    }
	 
	 
	 @PutMapping("/update/{id}")
	    public ResponseEntity<String> updateProjet(@PathVariable("id") int id, @RequestBody Etiquette updatedProjet) {
	    	try {
	            Optional<Etiquette> existingEOptional = EtiquetteService.findById(id);
	            if (existingEOptional.isPresent()) {                
	            	EtiquetteService.updateEtiquette(id, updatedProjet);
	                return ResponseEntity.ok("etq updated successfully");
	            } else {
	                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Etiquette not found");
	            }
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating Etiquette: " + e.getMessage());
	        }
	    }
	 
	    @DeleteMapping("/delete/{id}")
	    public ResponseEntity<String> deleteEtiquette(@PathVariable("id") int id) {
	        try {
	        	EtiquetteService.deleteEtiquette(id);
	            return ResponseEntity.ok("Etiquette deleted successfully");
	        } catch (RuntimeException e) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting Etiquette: " + e.getMessage());
	        }
	    }
	    
	    
	 
	 /*
	  {
    "id" : "2",
    "libelle": "medium Priority",
    "tache": {
        "id": 0
    }
}
*/
	
}
