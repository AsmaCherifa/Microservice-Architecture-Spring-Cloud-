package tn.itbs.asma.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import tn.itbs.asma.entities.Utilisateur;

@FeignClient(name="microservice-user" , url="${serviceU.baseUrl}")

public interface UserFeign {
	  @GetMapping(value="/{id}")
	    public ResponseEntity<Utilisateur> getUserById(@PathVariable("id") int id);
}
