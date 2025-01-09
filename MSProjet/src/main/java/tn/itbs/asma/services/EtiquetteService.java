package tn.itbs.asma.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import tn.itbs.asma.entities.Etiquette;
import tn.itbs.asma.repositories.EtiquetteRepo;

@Service

public class EtiquetteService {

@Autowired
private EtiquetteRepo etiquetteRepo;
	
	public ResponseEntity<String> addEtiquette(Etiquette etiquette) {		
		etiquetteRepo.findById(etiquette.getId()).ifPresentOrElse(
	            (existingProject) -> {
	                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Project exists already");
	            },
	            () -> {
	            	etiquetteRepo.save(etiquette);
	            }
	        );
	        
	        return ResponseEntity.accepted().body("etq added");
	    
	}
	
	   public Optional<Etiquette> findById(int id) {
	    	 return etiquetteRepo.findById(id);
		}
	    
	
	
	public List<Etiquette> ReadEtiquette(){
		return etiquetteRepo.findAll();
	}
	
	
    public  ResponseEntity<String> updateEtiquette(Integer id,Etiquette etiquette) {
    	etiquetteRepo.findById(id)
   	.ifPresentOrElse(e -> {
       e.setId(etiquette.getId());
       e.setLibelle(etiquette.getLibelle());
       e.setTache(etiquette.getTache());
  
       
       etiquetteRepo.save(e);
       }, 
			() -> {
			throw new RuntimeException("e doesn't exist");
			});
		return ResponseEntity.accepted().body("updated successfully !");
			}
    
    
    public  ResponseEntity<String> deleteEtiquette(Integer id) { 
    	etiquetteRepo.findById(id).ifPresentOrElse(existingE -> {
    		etiquetteRepo.delete(existingE);
        }, () -> {
            throw new RuntimeException("E doesn't exist");
        });
		return ResponseEntity.accepted().body("deleted successfully !");
    }

}
