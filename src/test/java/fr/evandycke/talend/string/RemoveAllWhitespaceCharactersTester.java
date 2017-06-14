package fr.evandycke.talend.string;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.evandycke.talend.log.AppLog;

import junit.framework.TestCase;

/**
 * RemoveAllWhitespaceCharactersTester repr�sente la classe de test de nettoyage
 * des espaces contenus dans une chaine de caract�res.
 * 
 * @author elie.vandycke
 * 
 */
public class RemoveAllWhitespaceCharactersTester extends TestCase {

	/**
	 * TestSet repr�sente un �l�ment � tester.
	 * 
	 * @author elie.vandycke
	 * 
	 */
	public class TestSet {

		/**
		 * Valeur
		 */
		private String value;

		/**
		 * R�sultat attendu
		 */
		private String expectedResult;

		/**
		 * Initialise une nouvelle instance de la classe TestSet
		 * 
		 * @param value
		 *            Valeur
		 * @param expectedResult
		 *            R�sultat attendu
		 */
		public TestSet(String value, String expectedResult) {

			this.value = value;
			this.expectedResult = expectedResult;
		}

		/**
		 * Obtient la valeur � tester
		 * 
		 * @return Valeur � tester
		 */
		public String getValue() {
			return value;
		}

		/**
		 * Obtient le r�sultat attendu
		 * 
		 * @return R�sultat attendu
		 */
		public String getExpectedResult() {
			return expectedResult;
		}
	}

	/**
	 * Helper de logging
	 */
	private static AppLog log = AppLog.getInstanceForThisClass();

	/**
	 * Liste des �l�ments � tester
	 */
	private List<TestSet> list = new ArrayList<TestSet>();

	/**
	 * Initialise le test unitaire
	 */
	@Before
	public void setUp() {

		if (list.isEmpty()) {

			list.add(new TestSet(null, null));
			list.add(new TestSet("", ""));
			list.add(new TestSet(" ", ""));

			list.add(new TestSet("ABC", "ABC"));
			list.add(new TestSet(" DEF", "DEF"));
			list.add(new TestSet("GHI ", "GHI"));
			list.add(new TestSet(" JKL ", "JKL"));
			list.add(new TestSet(" M N O ", "MNO"));
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
	 * Teste le nettoyage des espaces contenus dans une chaine de caract�res
	 */
	@Test
	public void testRemoveAllWhitespaceCharacters() {

		for (TestSet t : list) {

			String result = StringHelper.removeAllWhitespaceCharacters(t
					.getValue());
			log.debug("Valeur : %s - Attendu : %s - Trouv� : %s", t.getValue(),
					t.getExpectedResult(), result);
			assertEquals(t.getExpectedResult(), result);
		}
	}
}
