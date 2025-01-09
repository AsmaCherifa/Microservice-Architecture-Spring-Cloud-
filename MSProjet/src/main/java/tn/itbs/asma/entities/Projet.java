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

@Entity
@Data
public class Projet {
	@Id
	private int id;
	private String nomProjet;
	
	private String description;
	private Date dateDebut;
	private Date dateFin;
	
	@ManyToOne
	@JoinColumn(name="idu")
    @JsonBackReference

	private Utilisateur user;

	
	 @OneToMany(mappedBy = "projet", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	    private List<Tache> ListeT = new ArrayList<>();


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNomProjet() {
		return nomProjet;
	}


	public void setNomProjet(String nomProjet) {
		this.nomProjet = nomProjet;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Date getDateDebut() {
		return dateDebut;
	}


	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}


	public Date getDateFin() {
		return dateFin;
	}


	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}


	public Utilisateur getUser() {
		return user;
	}


	public void setUser(Utilisateur user) {
		this.user = user;
	}


	public List<Tache> getListeT() {
		return ListeT;
	}


	public void setListeT(List<Tache> listeT) {
		ListeT = listeT;
	}
	 
	 
}
