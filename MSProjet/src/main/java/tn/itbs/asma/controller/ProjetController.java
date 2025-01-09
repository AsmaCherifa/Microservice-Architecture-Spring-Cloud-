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

import tn.itbs.asma.entities.Projet;
import tn.itbs.asma.services.ProjetService;
@RestController
@RequestMapping("/projects")
public class ProjetController {
	@Autowired
	    private ProjetService ProjetService;

	    @GetMapping(value="/{id}")
	    public ResponseEntity<Projet> getProjectById(@PathVariable("id") int id) {
	        Optional<Projet> project = ProjetService.findById(id);
	        if (project.isPresent()) {
	            return ResponseEntity.ok(project.get());
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	        }
	    }

	     
	    @PostMapping("/add")
	    public ResponseEntity<String> addProject(@RequestBody Projet projet, BindingResult result) {
	        if (result.hasErrors()) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid project data");
	        }
	        try {
	        	ProjetService.addProject(projet);
	            return ResponseEntity.status(HttpStatus.CREATED).body("project added successfully");
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding project: " + e.getMessage());
	        }
	    }
	    
	    
	    @PutMapping("/update/{id}")
	    public ResponseEntity<String> updateProjet(@PathVariable("id") int id, @RequestBody Projet updatedProjet) {
	    	try {
	            Optional<Projet> existingProjetOptional = ProjetService.findById(id);
	            if (existingProjetOptional.isPresent()) {                
	            	ProjetService.updateProjet(id, updatedProjet);
	                return ResponseEntity.ok("Projet updated successfully");
	            } else {
	                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Projet not found");
	            }
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating Projet: " + e.getMessage());
	        }
	    }
	 
	    @DeleteMapping("/delete/{id}")
	    public ResponseEntity<String> deleteProject(@PathVariable("id") int id) {
	        try {
	        	ProjetService.deleteProjet(id);
	            return ResponseEntity.ok("Project deleted successfully");
	        } catch (RuntimeException e) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting Project: " + e.getMessage());
	        }
	    }
	    /*
	      {
		    "id": 2,
		    "nomProjet": "New Project",
		    "description": "A description of the new project",
		    "dateDebut": "2024-10-18T00:00:00",
		    "dateFin": "2025-01-18T00:00:00",
		    "user": {
		        "id": 1
		    },
		    "ListeT": []
			}
*/
	   
	
}
