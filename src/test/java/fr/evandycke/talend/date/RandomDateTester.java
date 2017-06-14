package fr.evandycke.talend.date;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.evandycke.talend.log.AppLog;
import junit.framework.TestCase;

/**
 * RandomDateTester représente la classe de test d'obtention d'une date
 * aléatoire.
 * 
 * @author elie.vandycke
 * 
 */
public class RandomDateTester extends TestCase {

	/**
	 * TestSet représente un élément à tester.
	 * 
	 * @author user
	 * 
	 */
	public class TestSet {

		/**
		 * Valeur minimale
		 */
		private int valMin;

		/**
		 * Valeur maximale
		 */
		private int valMax;

		/**
		 * Resultat attendu
		 */
		private boolean expectedResult;

		/**
		 * Initialise une nouvelle instance de la classe TestSet
		 * 
		 * @param valMin
		 *            Valeur minimale
		 * @param valMax
		 *            Valeur maximale
		 * @param expectedResult
		 *            Résultat attendu
		 */
		public TestSet(int valMin, int valMax) {

			this.valMin = valMin;
			this.valMax = valMax;
			this.expectedResult = true;
		}

		/**
		 * Obtient la valeur minimale
		 * 
		 * @return ValMin
		 */
		public int getValMin() {
			return valMin;
		}

		/**
		 * Obtient la valeur maximale
		 * 
		 * @return valMax
		 */
		public int getValMax() {
			return valMax;
		}

		/**
		 * Retourne le resultat attendu
		 * 
		 * @return expectedResult
		 */
		public boolean getExpectedResult() {
			return expectedResult;
		}
	}

	/**
	 * Helper de logging
	 */
	private static AppLog log = AppLog.getInstanceForThisClass();

	/**
	 * Liste des éléments à tester
	 */
	private List<TestSet> list = new ArrayList<TestSet>();

	/**
	 * Initialise le test unitaire
	 */
	@Before
	public void setUp() {

		if (list.isEmpty()) {

			list.add(new TestSet(2010, 2015));
			list.add(new TestSet(1900, 2010));
		}
	}

	/**
	 * Finalise le test unitaire
	 */
	@After
	public void tearDown() {

		list = null;
	}

	/**
	 * Teste si la date est postérieure
	 */
	@Test
	public void testRandom() {

		for (TestSet t : list) {

			Date result = DateHelper.random(t.getValMin(), t.getValMax());
			boolean compris = DateHelper.between(result, DateHelper.getDate(t.getValMin(), 1, 1),
					DateHelper.getDate(t.getValMax(), 12, 31), false);
			log.debug("Valeur minimale : %s - Valeur maximale : %s - Trouvé : %s", t.getValMin(), t.getValMax(),
					result);
			assertNotNull(result);
			assertTrue(compris);
		}
	}

}
