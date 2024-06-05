package fr.diginamic;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.junit.Test;

public class ActeurRepositoryTest {

	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("movies");
	private EntityManager em = emf.createEntityManager();

	/**
	 * Extraire tous les acteurs triés dans l'ordre alphabétique des identités
	 */
	@Test
	public void testExtraireActeursTriesParIdentite() {

		TypedQuery<Acteur> query = em.createQuery("SELECT a FROM Acteur a order by a.identite asc", Acteur.class);
		List<Acteur> acteurs = query.getResultList();

		assertEquals(1137, acteurs.size());
		assertEquals("A.J. Danna", acteurs.get(0).getIdentite());
	}

	/**
	 * Extraire l'actrice appelée Marion Cotillard
	 */
	@Test
	public void testExtraireActeursParIdentite() {
		TypedQuery<Acteur> query = em.createQuery("SELECT a FROM Acteur a where a.identite = 'Marion Cotillard'",
				Acteur.class);
		List<Acteur> acteurs = query.getResultList();

		assertEquals(1, acteurs.size());
		assertEquals("Marion Cotillard", acteurs.get(0).getIdentite());
	}

	/**
	 * Extraire la liste des acteurs dont l'année de naissance est 1985.
	 * Astuce: fonction year(...)
	 */
	@Test
	public void testExtraireActeursParAnneeNaissance() {
		TypedQuery<Acteur> query = em.createQuery("SELECT a FROM Acteur a where YEAR(a.anniversaire) = 1985 ",
				Acteur.class);
		List<Acteur> acteurs = query.getResultList();

		assertEquals(10, acteurs.size());
	}

	/**
	 * Extraire la liste des actrices ayant joué le rôle d'Harley QUINN
	 */
	@Test
	public void testExtraireActeursParRole() {

		TypedQuery<Acteur> query = em.createQuery("SELECT a FROM Acteur a JOIN a.roles r WHERE r.nom = 'Harley Quinn'",
				Acteur.class);
		List<Acteur> acteurs = query.getResultList();

		assertEquals(2, acteurs.size());
		assertEquals("Margot Robbie", acteurs.get(0).getIdentite());
		assertEquals("Margot Robbie", acteurs.get(1).getIdentite());
	}

	/**
	 * Extraire la liste de tous les acteurs ayant joué dans un film paru en 2015.
	 */
	@Test
	public void testExtraireActeursParFilmParuAnnee() {
		TypedQuery<Acteur> query = em.createQuery(
				"SELECT a FROM Acteur a " +
						"LEFT JOIN a.roles r " +
						"LEFT JOIN r.film f " +
						"where f.annee = 2015",
				Acteur.class);
		List<Acteur> acteurs = query.getResultList();
		assertEquals(140, acteurs.size());
	}

	/**
	 * Extraire la liste de tous les acteurs ayant joué dans un film français
	 * Astuce: mot clé distinct
	 */
	@Test
	public void testExtraireActeursParPays() {
		TypedQuery<Acteur> query = em.createQuery(
				"SELECT DISTINCT a FROM Acteur a " +
						"JOIN a.roles r " +
						"JOIN r.film f " +
						"JOIN f.pays p " +
						"WHERE p.nom = 'France'",
				Acteur.class);
		List<Acteur> acteurs = query.getResultList();
		assertEquals(158, acteurs.size());
	}

	/**
	 * Extraire la liste de tous les acteurs ayant joué dans un film français paru
	 * en 2017
	 * Astuce: mot clé distinct
	 */
	@Test
	public void testExtraireActeursParListePaysEtAnnee() {
		TypedQuery<Acteur> query = em.createQuery(
				"SELECT DISTINCT a FROM Acteur a " +
						"JOIN a.roles r " +
						"JOIN r.film f " +
						"JOIN f.pays p " +
						"WHERE p.nom = 'France'" +
						"AND f.annee = 2017 ",
				Acteur.class);
		List<Acteur> acteurs = query.getResultList();
		assertEquals(24, acteurs.size());
	}

	/**
	 * Extraire la liste de tous les acteurs ayant joué dans un film réalisé par
	 * Ridley Scott
	 * entre les années 2010 et 2020
	 * Astuce: mot clé distinct
	 */
	@Test
	public void testExtraireParRealisateurEntreAnnee() {
		TypedQuery<Acteur> query = em.createQuery(
				"SELECT DISTINCT a FROM Acteur a " +
						"JOIN a.roles r " +
						"JOIN r.film f " +
						"JOIN f.realisateurs rr " +
						"WHERE rr.identite = 'Ridley Scott' AND f.annee BETWEEN 2010 AND 2020",
				Acteur.class);
		List<Acteur> acteurs = query.getResultList();
		assertEquals(27, acteurs.size());
	}

}