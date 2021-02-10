package fr.control.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import fr.model.JeuxVideo;

public class DAO {
	static final Logger logger = LogManager.getLogger("Suivi");

	// CRÉATION :
	public static void creerJeuxVideo(JeuxVideo jeu) {
		try (Session session = Base.getSessionFactory().openSession()) {
			Transaction tx = session.beginTransaction();
			session.save(jeu);
			tx.commit();
		}
	}// -

	// SUPPRESSION :
	public static void suppression(JeuxVideo jeu) {
		try (Session session = Base.getSessionFactory().openSession()) {
			Transaction tx = session.beginTransaction();
			session.delete(jeu);
			tx.commit();
		}
	}// -

	// MODIFICATION(S) D'UN JEUX VIDEO :
	public static void modifierJeu(int id, String nom, LocalDate date, int prix, String genre) {
		JeuxVideo jeuVideo;

		try (Session session = Base.getSessionFactory().openSession()) {
			Transaction tx = session.beginTransaction();
			jeuVideo = session.load(JeuxVideo.class, id);

			if (nom.isBlank() == false) {
				jeuVideo.setNom(nom);
			}

			if (date.getYear() > 1 & date.getMonthValue() > 1 & date.getDayOfMonth() > 1) {
				jeuVideo.setDateDeSortie(date);
			}

			if (prix > 0) {
				jeuVideo.setPrix(prix);
			}

			if (genre.isBlank() == false) {
				jeuVideo.setGenre(genre);
			}

			tx.commit();
		}

	}// -

	// RECHERCHE DES JEUX VIDÉOS :
	public static List<JeuxVideo> listeJeuxVideos() {
		List<JeuxVideo> jeuxVideos;

		jeuxVideos = new ArrayList<JeuxVideo>();

		try (Session session = Base.getSessionFactory().openSession()) {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<JeuxVideo> criteriaQuery = builder.createQuery(JeuxVideo.class);
			Root<JeuxVideo> root = criteriaQuery.from(JeuxVideo.class);
			criteriaQuery.select(root);

			jeuxVideos = session.createQuery(criteriaQuery).getResultList();
		}

		return jeuxVideos;
	}// -

	// RECHERCHE D'UN JEUX VIDÉO :
	public static List<JeuxVideo> rechercherJeuxVideo(String nom, LocalDate dateDeSortie) {
		Predicate p1, p2;
		List<JeuxVideo> jeuxVideos;

		jeuxVideos = new ArrayList<JeuxVideo>();

		try (Session session = Base.getSessionFactory().openSession()) {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<JeuxVideo> criteriaQuery = builder.createQuery(JeuxVideo.class);
			Root<JeuxVideo> root = criteriaQuery.from(JeuxVideo.class);
			criteriaQuery.select(root);

			p1 = builder.equal(root.<LocalDate>get("dateDeSortie"), builder.parameter(LocalDate.class, "dateDeSortie"));
			p2 = builder.equal(root.<String>get("nom"), builder.parameter(String.class, "nom"));
			criteriaQuery.where(p1, p2);

			jeuxVideos = session.createQuery(criteriaQuery).setParameter("dateDeSortie", dateDeSortie)
					.setParameter("nom", nom).getResultList();
		}

		return jeuxVideos;
	}// -

	public static List<JeuxVideo> rechercherNomJeuxVideo(String nom) {
		JeuxVideo jeu;
		Predicate p;
		List<JeuxVideo> jeuxVideos;

		jeuxVideos = new ArrayList<JeuxVideo>();

		try (Session session = Base.getSessionFactory().openSession()) {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<JeuxVideo> criteriaQuery = builder.createQuery(JeuxVideo.class);
			Root<JeuxVideo> root = criteriaQuery.from(JeuxVideo.class);
			criteriaQuery.select(root);

			p = builder.equal(root.<String>get("nom"), builder.parameter(String.class, "nom"));
			criteriaQuery.where(p);

			jeuxVideos = session.createQuery(criteriaQuery).setParameter("nom", nom).getResultList();
		}

		return jeuxVideos;
	}// -

	public static JeuxVideo rechercherNomDateJeuxVideo(String nom, LocalDate dateDeSortie) {
		JeuxVideo jeu;
		Predicate p1, p2;
		List<JeuxVideo> jeuxVideos;

		jeu = new JeuxVideo();

		try (Session session = Base.getSessionFactory().openSession()) {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<JeuxVideo> criteriaQuery = builder.createQuery(JeuxVideo.class);
			Root<JeuxVideo> root = criteriaQuery.from(JeuxVideo.class);
			criteriaQuery.select(root);

			p1 = builder.equal(root.<String>get("nom"), builder.parameter(String.class, "nom"));
			p2 = builder.equal(root.<String>get("dateDeSortie"), builder.parameter(LocalDate.class, "dateDeSortie"));
			criteriaQuery.where(p1, p2);

			jeuxVideos = session.createQuery(criteriaQuery).setParameter("nom", nom)
					.setParameter("dateDeSortie", dateDeSortie).getResultList();

			if (!jeuxVideos.isEmpty())
				jeu = jeuxVideos.get(0);
		}

		return jeu;
	}// -

	public static JeuxVideo getID(int id) {
		JeuxVideo j;

		j = new JeuxVideo();

		try (Session session = Base.getSessionFactory().openSession()) {
			j = session.load(JeuxVideo.class, id);
		}

		return j;
	}

}// FIN PRG
