package tn.itbs.asma.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data

public class Utilisateur {

	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	private String name;
	private String surname;
	@NotBlank(message = "login cannot be empty")
	private String login;
	@NotBlank(message = "mdp cannot be empty")
	private String mdp;
	@Column (unique=true)
	private String email;
	
	/* @OneToMany (mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
*/
	private List<Integer> ListeIdP = new ArrayList<Integer>(); 
	@Transient
	private List<Projet> ListeP = new ArrayList<Projet>();
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getMdp() {
		return mdp;
	}
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<Integer> getListeIdP() {
		return ListeIdP;
	}
	public void setListeIdP(List<Integer> listeIdP) {
		ListeIdP = listeIdP;
	}
	public List<Projet> getListeP() {
		return ListeP;
	}
	public void setListeP(List<Projet> listeP) {
		ListeP = listeP;
	}
	
	
	
	
	
}
