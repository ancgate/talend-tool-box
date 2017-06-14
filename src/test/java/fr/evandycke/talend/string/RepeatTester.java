package fr.evandycke.talend.string;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import fr.evandycke.talend.log.AppLog;

import junit.framework.TestCase;

/**
 * RepeatTester est la classe de test de r�p�tition d'une chaine de caract�res.
 * 
 * @author elie.vandycke
 * 
 */
public class RepeatTester extends TestCase {

	/**
	 * TestSet est la classe d'un �l�ment test� dans ce test unitaire.
	 * 
	 * @author elie.vandycke
	 * 
	 */
	static class TestSet {

		/**
		 * Valeur
		 */
		private String value;

		/**
		 * R�p�tition
		 */
		private int repetition;

		/**
		 * R�sultat attendu
		 */
		private String expectedResult;

		/**
		 * Initialise une nouvelle instance de la classe TestSet
		 * 
		 * @param value
		 *            Valeur
		 * @param repetition
		 *            R�p�tition
		 * @param expectedResult
		 *            R�sultat attendu
		 */
		public TestSet(final String value, final int repetition,
				final String expectedResult) {
			this.value = value;
			this.repetition = repetition;
			this.expectedResult = expectedResult;
		}

		/**
		 * Obtient le r�sultat attendu
		 * 
		 * @return R�sultat attendu
		 */
		public String getExpectedResult() {
			return expectedResult;
		}

		/**
		 * Obtient la r�p�tition
		 * 
		 * @return R�p�tition
		 */
		public int getRepetition() {
			return repetition;
		}

		/**
		 * Obtient la valeur
		 * 
		 * @return Valeur
		 */
		public String getValue() {
			return value;
		}
	}

	/**
	 * Liste des �l�ments � tester
	 */
	private List<TestSet> list = new ArrayList<TestSet>();

	/**
	 * Helper logging
	 */
	private static AppLog log = AppLog.getInstanceForThisClass();

	/**
	 * Initialise le test unitaire
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {

		if (this.list.isEmpty()) {

			// Traitement des valeurs
			this.list.add(new TestSet(null, 5, ""));
			this.list.add(new TestSet("ABC", 3, "ABCABCABC"));
			this.list.add(new TestSet("", 3, ""));
			this.list.add(new TestSet(" DEF ", 2, " DEF  DEF "));
			this.list.add(new TestSet("  ", 3, ""));
			this.list.add(new TestSet("*", 10, "**********"));
		}
	}

	/**
	 * Teste la r�p�tition d'une chaine de caract�res
	 */
	@Test
	public void testRepeat() {

		for (TestSet t : this.list) {
			String result = StringHelper
					.repeat(t.getValue(), t.getRepetition());

			log.info(
					"Valeur : %s - R�p�tition : %s - Attendu : %s - Trouv� : %s",
					t.getValue(), t.getRepetition(), t.getExpectedResult(),
					result);

			assertEquals(t.getExpectedResult(), result);
		}
	}
}