package tn.itbs.asma.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import tn.itbs.asma.entities.Utilisateur;
import tn.itbs.asma.repositories.UtilisateurRepo;

@Service
public class UtilisateurService {

	
    private final UtilisateurRepo utilisateurRepo;
    
    public UtilisateurService(UtilisateurRepo utilisateurRepo) {
        this.utilisateurRepo = utilisateurRepo;
    }

    public Utilisateur save(Utilisateur u) {
        return utilisateurRepo.save(u);
    }
    
    public void updateUser(int id, Utilisateur user) {
        utilisateurRepo.findById(id)
            .ifPresentOrElse(u -> {
                u.setName(user.getName());
                u.setSurname(user.getSurname());
                u.setEmail(user.getEmail());
                u.setMdp(user.getMdp());
                utilisateurRepo.save(u);
                            }, 
            () -> {
                throw new RuntimeException("User doesn't exist");
            });
    }
    
    public Optional<Utilisateur> findById(int id) {
        return utilisateurRepo.findById(id);
    }

    public void deleteUser(int id) {
        utilisateurRepo.findById(id).ifPresentOrElse(existingUser -> {
            utilisateurRepo.delete(existingUser);
        }, () -> {
            throw new RuntimeException("User doesn't exist");
        });
    }

}
