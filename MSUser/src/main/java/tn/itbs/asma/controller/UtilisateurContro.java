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

import tn.itbs.asma.entities.Utilisateur;
import tn.itbs.asma.services.UtilisateurService;
//@controller va renvoyer une vue
//@restcontroller va renvoyer json
@RestController
@RequestMapping("/users")

public class UtilisateurContro {
    @Autowired
    private UtilisateurService UtilisateurService;

	//http://localhost:9095/users/1
    @GetMapping(value="/{id}")
    public ResponseEntity<Utilisateur> getUserById(@PathVariable("id") int id) {
        Optional<Utilisateur> user = UtilisateurService.findById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

      //http://localhost:9095/users/add
	 //post method va envoyer json dans le body de la requete
    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody Utilisateur user, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid user data");
        }
        try {
            UtilisateurService.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("User added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding user: " + e.getMessage());
        }
    }
    //http://localhost:9095/users/update/1
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateUser(@PathVariable("id") int id, @RequestBody Utilisateur updatedUser) {
    	try {
            Optional<Utilisateur> existingUserOptional = UtilisateurService.findById(id);
            if (existingUserOptional.isPresent()) {                
                UtilisateurService.updateUser(id, updatedUser);
                return ResponseEntity.ok("User updated successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating user: " + e.getMessage());
        }
    }
                
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") int id) {
        try {
        	UtilisateurService.deleteUser(id);
            return ResponseEntity.ok("User deleted successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting user: " + e.getMessage());
        }
    }
      

/*
 http://localhost:9095/users/add
{
    "id": 1,
    "Nom": "Asma",
    "Prenom": "Cherifa",
    "login": "asma",
    "mdp": "123",
    "email": "asma@gmail.com",
    "ListeP": [
        {
            "id": 1,
            "NomProjet": "Project1"
        }
    ]
}

*/

}
