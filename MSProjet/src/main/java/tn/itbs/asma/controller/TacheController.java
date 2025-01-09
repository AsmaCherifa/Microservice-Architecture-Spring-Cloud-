package tn.itbs.asma.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.itbs.asma.entities.Tache;
import tn.itbs.asma.entities.Utilisateur;
import tn.itbs.asma.services.TacheService;

@RestController
@RequestMapping("/tasks")
public class TacheController {
	 @Autowired
	    private TacheService TacheService;
	 
	 public ResponseEntity<Tache> getUserById(@PathVariable("id") int id) {
	        Optional<Tache> t = TacheService.findById(id);
	        if (t.isPresent()) {
	            return ResponseEntity.ok(t.get());
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	        }
	    }
	 @PostMapping("/add")
	    public ResponseEntity<String> addUser(@RequestBody Tache t, BindingResult result) {
	        if (result.hasErrors()) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid t data");
	        }
	        try {
	        	TacheService.save(t);
	            return ResponseEntity.status(HttpStatus.CREATED).body("t added successfully");
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding t: " + e.getMessage());
	        }
	    }
	 @PutMapping("/update/{id}")
	    public ResponseEntity<String> updateUser(@PathVariable("id") int id, @RequestBody Tache updatedT) {
	    	try {
	            Optional<Tache> existingTOptional = TacheService.findById(id);
	            if (existingTOptional.isPresent()) {                
	            	TacheService.UpdateTask(updatedT, id);
	                return ResponseEntity.ok("T updated successfully");
	            } else {
	                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("T not found");
	            }
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating T: " + e.getMessage());
	        }
	    }
	                
	    @DeleteMapping("/delete/{id}")
	    public ResponseEntity<String> deleteUser(@PathVariable("id") int id) {
	        try {
	        	TacheService.deleteTask(id);
	            return ResponseEntity.ok("t deleted successfully");
	        } catch (RuntimeException e) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting user: " + e.getMessage());
	        }
	    }
	      
	 
	 
	 /*
	  {
    "titre": "Develop a new feature",
    "description": "Implement user authentication",
    "dateEcheance": "2024-11-30",
    "statut": "en_attente",
    "projet": {
        "id": 1
    },
    "listeE": []
}

*/
	
	 
}
