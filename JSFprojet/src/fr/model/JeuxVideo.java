package fr.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class JeuxVideo {
	private String nom, genre;
	private LocalDate dateDeSortie;
	private int id, prix;

	public JeuxVideo() {
		setDateDeSortie(LocalDate.now());
		setNom("");
	}

	public JeuxVideo(String nom, LocalDate dateDeSortie) {
		setDateDeSortie(dateDeSortie);
		setNom(nom);
	}

	// Getters et Setters :
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom.toUpperCase();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getDateDeSortie() {
		return dateDeSortie;
	}

	public void setDateDeSortie(LocalDate dateDeSortie) {
		this.dateDeSortie = dateDeSortie;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre.toUpperCase();
	}

	public int getPrix() {
		return prix;
	}

	public void setPrix(int prix) {
		this.prix = prix;
	}

	// Autres :
	public String description() {
		StringBuilder sb;
		String str;

		if (this.nom.isBlank() == true) {
			str = "Ce jeux vidéo n'existe pas";
		} else {
			sb = new StringBuilder(this.id);
			sb.append("- Le jeux vidéo se nomme ");
			sb.append(this.nom);
			sb.append(", il est sortie le ");
			sb.append(this.dateDeSortie.getDayOfMonth());
			sb.append("/");
			sb.append(this.dateDeSortie.getMonthValue());
			sb.append("/");
			sb.append(this.dateDeSortie.getYear());
			sb.append(". ");
			if (this.genre.isBlank() == false) {
				sb.append("Il est catégorisé comme étant un jeu de ");
				sb.append(this.genre);
				sb.append(". ");
			}
			if (this.prix > 0) {
				sb.append("Il est au prix de ");
				sb.append(this.prix);
				sb.append(" €.");
			}
			str = sb.toString();
		}

		return str;
	}

}// FIN PRG
