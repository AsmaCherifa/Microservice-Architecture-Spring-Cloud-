package tn.itbs.asma.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import tn.itbs.asma.entities.Projet;

@FeignClient(name="microservice-projet" , url="${serviceU.baseUrl}")
public interface ProjetFeign {
	@GetMapping(value="/{id}")
    public ResponseEntity<Projet> getProjectById(@PathVariable("id") int id);
	
	@PostMapping("/add")
    public ResponseEntity<String> addProject(@RequestBody Projet projet, BindingResult result);
	
	@PutMapping("/update/{id}")
	    public ResponseEntity<String> updateProjet(@PathVariable("id") int id, @RequestBody Projet updatedProjet);
	  
	@DeleteMapping("/delete/{id}")
	    public ResponseEntity<String> deleteProject(@PathVariable("id") int id);
    
}
