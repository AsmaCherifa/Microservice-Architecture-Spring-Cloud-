package tn.itbs.asma.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import tn.itbs.asma.entities.Tache;
import tn.itbs.asma.repositories.TacheRepo;

@Service

public class TacheService {
@Autowired
private TacheRepo tacheRepo;
	
	
	public ResponseEntity<String> save(Tache task) {
		tacheRepo.findById(task.getId()).ifPresentOrElse(
		            (existingTask) -> {
		                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "task exists already");
		            },
		            () -> {
		            	tacheRepo.save(task);
		            }
		        );
		        
		        return ResponseEntity.accepted().body("task added :!!");
		 	}
	/*
	 methode 1
	public Tache findById(Integer id){
		return tacheRepo.findById(id).get();
	}
	*/
// methode 2
// on utlise optional qd on a findby

	public Optional<Tache> findById(Integer id){
		return tacheRepo.findById(id);
	}
	
	
	public ResponseEntity<String> UpdateTask(Tache task, Integer id) {
		tacheRepo.findById(id)
		.ifPresentOrElse(taskUp -> {
		taskUp.setTitre(task.getTitre());
		taskUp.setDescription(task.getDescription());
		taskUp.setStatut(task.getStatut());
		taskUp.setDateEcheance(task.getDateEcheance());
		 tacheRepo.save(taskUp);
		 
	 }, 
		() -> {
		throw new RuntimeException("T doesn't exist");
			});
		return ResponseEntity.accepted().body("updated successfully !");
			}

	
	
	public ResponseEntity<String> deleteTask(Integer id) {
		tacheRepo.findById(id).ifPresentOrElse(existingT -> {
			tacheRepo.delete(existingT);
        }, () -> {
            throw new RuntimeException("T doesn't exist");
        });
		return ResponseEntity.accepted().body("deleted successfully !");
   
	}
	
	
	/*
	public List<Tache> SearchTaskByProjectId(Integer idP){
		return tacheRepo.findByProjectId(idP);
		
	}
	
	  */
}
