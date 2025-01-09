package tn.itbs.asma.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import tn.itbs.asma.entities.Projet;
import tn.itbs.asma.entities.Utilisateur;
import tn.itbs.asma.repositories.ProjetRepo;

@Service
public class ProjetService {

    @Autowired
    private ProjetRepo projetRepo;

    public ResponseEntity<String> addProject(Projet projet) {
        projetRepo.findById(projet.getId()).ifPresentOrElse(
            (existingProject) -> {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Project exists already");
            },
            () -> {
                projetRepo.save(projet);
            }
        );
        
        return ResponseEntity.accepted().body("Procjet added : " + projet.getNomProjet());
    }

    public Optional<Projet> findById(int id) {
    	 return projetRepo.findById(id);
	}
    
    

    public  ResponseEntity<String> updateProjet(Integer id, Projet projet) {
    	 projetRepo.findById(id)
    	.ifPresentOrElse(p -> {
        p.setId(projet.getId());
        p.setNomProjet(projet.getNomProjet());
        p.setDateDebut(projet.getDateDebut());
        p.setDateFin(projet.getDateFin());
        p.setDescription(projet.getDescription());
        
        projetRepo.save(p);
        }, 
			() -> {
			throw new RuntimeException("Project doesn't exist");
			});
		return ResponseEntity.accepted().body("updated successfully !");
			}
    
    
    public  ResponseEntity<String> deleteProjet(Integer id) { 
        projetRepo.findById(id).ifPresentOrElse(existingPj -> {
        	projetRepo.delete(existingPj);
        }, () -> {
            throw new RuntimeException("Project doesn't exist");
        });
		return ResponseEntity.accepted().body("deleted successfully !");
    }

   

    public List<Projet> getAllProjets() {
        return projetRepo.findAll();
    }


	

}
