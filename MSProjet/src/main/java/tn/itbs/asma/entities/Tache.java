package tn.itbs.asma.entities;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Tache {

	@Id
	private int id;
	private String titre;
	private String description;
	private Date dateEcheance;
	private Statut statut;

    public enum Statut {
        en_attente,
        en_cours,
        terminee
    }
    
    @ManyToOne
	@JoinColumn(name="idp")
	private Projet projet;
    
    @OneToMany (mappedBy = "tache")
	
	private List<Etiquette> ListeE = new ArrayList<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDateEcheance() {
		return dateEcheance;
	}

	public void setDateEcheance(Date dateEcheance) {
		this.dateEcheance = dateEcheance;
	}

	public Statut getStatut() {
		return statut;
	}

	public void setStatut(Statut statut) {
		this.statut = statut;
	}

	public Projet getProjet() {
		return projet;
	}

	public void setProjet(Projet projet) {
		this.projet = projet;
	}

	public List<Etiquette> getListeE() {
		return ListeE;
	}

	public void setListeE(List<Etiquette> listeE) {
		ListeE = listeE;
	}
    
    
}
