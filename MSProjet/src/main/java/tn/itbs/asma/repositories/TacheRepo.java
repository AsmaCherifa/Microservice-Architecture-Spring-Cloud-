package tn.itbs.asma.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import tn.itbs.asma.entities.Tache;

public interface TacheRepo  extends JpaRepository<Tache,Integer>{
}
