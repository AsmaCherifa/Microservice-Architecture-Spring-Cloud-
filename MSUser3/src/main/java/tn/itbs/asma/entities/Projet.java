package tn.itbs.asma.entities;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
public class Projet {
	private int id;
	private String nomProjet;
	
	private String description;
	private Date dateDebut;
	private Date dateFin;
	
	/*@ManyToOne
	@JoinColumn(name="idu")
    @JsonBackReference

	private Utilisateur user;

	
	 @OneToMany(mappedBy = "projet", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	    private List<Tache> ListeT = new ArrayList<>();
	    */
}
