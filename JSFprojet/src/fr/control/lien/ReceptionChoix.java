package fr.control.lien;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.control.dao.DAO;
import fr.model.JeuxVideo;
import outils.DateValide;

public class ReceptionChoix {
	static final Logger logger = LogManager.getLogger("Suivi");

	// VÉRIFICATION CRÉATION :
	public static void creation(String nom, int a, int m, int j, String categorie, int prix) {
		boolean valide;
		JeuxVideo jeu;
		LocalDate dateDeSortie;

		dateDeSortie = constructionDate(j, m, a);
		valide = false;

		if (dateDeSortie.getYear() > 1 & dateDeSortie.getMonthValue() > 1 & dateDeSortie.getDayOfMonth() > 1)
			valide = true;

		if (!nom.isEmpty()) {
			valide = true;
		}

		if (prix < 1) {
			valide = false;
		} else {
			if (categorie.isBlank())
				valide = false;
		}

		if (valide == true) {
			jeu = new JeuxVideo(nom, dateDeSortie);
			jeu.setGenre(categorie);
			jeu.setPrix(prix);
			DAO.creerJeuxVideo(jeu);
		}
	}// -

	// CONSTRUCTION EST CONTRÔLE DE LA DATE :
	private static LocalDate constructionDate(int j, int m, int a) {
		int jourMax;
		LocalDate ldt, dateActuelle;

		if (a < 1) {
			a = 1;
		}

		if (m < 1) {
			m = 1;
		}

		if (m > 12) {
			m = 12;
		}

		if (j < 1) {
			j = 1;
		}

		jourMax = DateValide.jourMax(j, m, a);
		if (j > jourMax) {
			j = jourMax;
		}

		ldt = LocalDate.of(a, m, j);

		dateActuelle = LocalDate.now();

		if (ldt.isAfter(dateActuelle)) {
			ldt = LocalDate.of(1, 1, 1);
		}

		return ldt;
	}

	// VÉRIFICATION SUPPRESSION :
	public static void suppression(String nom, int anneeSortie, int moisSortie, int jourSortie) {
		LocalDate dateActuelle, dateDeSortie;
		JeuxVideo jeu;

		dateDeSortie = LocalDate.of(anneeSortie, moisSortie, jourSortie);
		dateActuelle = LocalDate.now();

		if (!nom.isEmpty()) {
			if (dateDeSortie.isBefore(dateActuelle)) {
				jeu = DAO.rechercherNomDateJeuxVideo(nom, dateDeSortie);
				if (jeu != null) {
					DAO.suppression(jeu);
				}

			}
		}
	}// -

	// VÉRIFICATION MISE À JOUR :
	public static void modification(String nom, int annee, int mois, int jour, String genre, int prix, int id) {
		LocalDate dateDeSortie;
		JeuxVideo jeu;

		dateDeSortie = constructionDate(jour, mois, annee);

		if (id > 0) {
			jeu = DAO.getID(id);
			if (jeu != null) {
				DAO.modifierJeu(id, nom, dateDeSortie, prix, genre);
			}

		}
	}// -

	// JEUX :
	public static void lectureJeux() {
		List<JeuxVideo> listeJeuxVideos;

		listeJeuxVideos = DAO.listeJeuxVideos();
	}// -

	public static List<String> rechercheNom(String nom) {
		String description;
		JeuxVideo jv;
		Iterator<JeuxVideo> it;
		List<JeuxVideo> listeJV;
		List<String> descriptions;

		listeJV = DAO.rechercherNomJeuxVideo(nom);
		descriptions = new ArrayList<String>();

		if (listeJV.isEmpty() == false) {
			it = listeJV.iterator();
			while (it.hasNext()) {
				jv = it.next();
				description = jv.description();
				descriptions.add(description);
			}
		}
		return descriptions;
	}// -

	public static List<String> rechercheJeuxVideos() {
		String description;
		JeuxVideo jv;
		Iterator<JeuxVideo> it;
		List<JeuxVideo> listeJV;
		List<String> descriptions;

		listeJV = DAO.listeJeuxVideos();
		descriptions = new ArrayList<String>();

		if (listeJV.isEmpty() == false) {
			it = listeJV.iterator();
			while (it.hasNext()) {
				jv = it.next();
				description = jv.description();
				descriptions.add(description);
			}
		}
		return descriptions;
	}

}// FIN PRG
