package fr.vue;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import fr.control.lien.ReceptionChoix;

@ManagedBean(name = "Saisie")
@SessionScoped
public class Saisie implements Serializable {

	private static final long serialVersionUID = -6913972022251814608L;

	static final Logger logger = LogManager.getLogger("Suivi");

	private String messageMenu = "Veuillez choisir l'action à accomplir :";
	private String nom, genre;
	private int jour, mois, annee, prix, id;

	public Saisie() {
		super();
	}

	// Getters et Setters :
	public String getMessageMenu() {
		return messageMenu;
	} 

	public void setMessageMenu(String messageMenu) {
		this.messageMenu = messageMenu;
	}

	// ID :
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	// NOM :
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	// DATE :
	public int getJour() {
		return jour;
	}

	public void setJour(int jour) {
		this.jour = jour;
	}

	public int getMois() {
		return mois;
	}

	public void setMois(int mois) {
		this.mois = mois;
	}

	public int getAnnee() {
		return annee;
	}

	public void setAnnee(int annee) {
		this.annee = annee;
	}

	// PRIX :
	public int getPrix() {
		return prix;
	}

	public void setPrix(int prix) {
		this.prix = prix;
	}

	// GENRE :
	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	// Autres méthodes :
	public void creation() {
		FacesMessage fm;

		fm = new FacesMessage("Procédure de création du jeux vidéo.");
		FacesContext.getCurrentInstance().addMessage(null, fm);
		ReceptionChoix.creation(this.nom, this.annee, this.mois, this.jour, this.genre, this.prix);
	}

	public void modification() {
		FacesMessage fm;

		fm = new FacesMessage("Procédure de modification du jeux vidéo.");
		FacesContext.getCurrentInstance().addMessage(null, fm);
		ReceptionChoix.modification(this.nom, this.annee, this.mois, this.jour, this.genre, this.prix, this.id);
	}

	public void suppression() {
		FacesMessage fm;

		fm = new FacesMessage("Procédure de suppression du jeux vidéo.");
		FacesContext.getCurrentInstance().addMessage(null, fm);
		ReceptionChoix.suppression(this.nom, this.annee, this.mois, this.jour);
	}

	public void existe() {
		String str;
		List<String> liste;
		Iterator<String> it;
		FacesMessage fm, fmr;

		fm = new FacesMessage("Procédure de recherche du jeux vidéo.");
		FacesContext.getCurrentInstance().addMessage(null, fm);

		liste = ReceptionChoix.rechercheNom(this.nom);

		if (liste.isEmpty() == true) {
			fmr = new FacesMessage("Il n'y a aucun jeux vidéo !");
			FacesContext.getCurrentInstance().addMessage(null, fmr);
		} else {
			it = liste.iterator();
			while (it.hasNext()) {
				str = it.next();
				fmr = new FacesMessage(str);
				FacesContext.getCurrentInstance().addMessage(null, fmr);
			}
		}
	}// -

	public static void listageJeuxVideos() {
		String str;
		List<String> liste;
		Iterator<String> it;
		FacesMessage fm, fmr;

		fm = new FacesMessage("Procédure de recherche des jeux vidéos.");
		FacesContext.getCurrentInstance().addMessage(null, fm);

		liste = ReceptionChoix.rechercheJeuxVideos();

		if (liste.isEmpty() == true) {
			fmr = new FacesMessage("Il n'y a aucun jeux vidéo !");
			FacesContext.getCurrentInstance().addMessage(null, fmr);
		} else {
			it = liste.iterator();
			while (it.hasNext()) {
				str = it.next();
				fmr = new FacesMessage(str);
				FacesContext.getCurrentInstance().addMessage(null, fmr);
			}
		}
	}// -

	public void reset() {
		setAnnee(0);
		setGenre(null);
		setId(0);
		setJour(0);
		setMois(0);
		setAnnee(0);
		setNom(null);
		setPrix(0);
	}

}// FIN PRG
