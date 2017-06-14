package fr.evandycke.talend.bool;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.evandycke.talend.log.AppLog;
import junit.framework.TestCase;

/**
 * TryParseBooleanTester repr�sente la classe de test de conversion d'un objet
 * en type Boolean.
 * 
 * @author elie.vandycke
 * 
 */
public class TryParseBooleanTester extends TestCase {
	
	/**
	 * Valeur par d�faut
	 */
	private final static Boolean DEFAULT_VALUE = false;

	/**
	 * TestSet repr�sente un �l�ment � tester.
	 * 
	 * @author user
	 * 
	 */
	public class TestSet {

		/**
		 * Valeur
		 */
		private Object value;

		/**
		 * R�sultat attendu
		 */
		private Boolean expectedResult;

		/**
		 * Initialise une nouvelle instance de la classe TestSet
		 * 
		 * @param value
		 *            Valeur
		 * @param expectedResult
		 *            R�sultat attendu
		 */
		public TestSet(Object value, Boolean expectedResult) {

			this.value = value;
			this.expectedResult = expectedResult;
		}

		/**
		 * Obtient la valeur � tester
		 * 
		 * @return Valeur � tester
		 */
		public Object getValue() {
			return value;
		}

		/**
		 * Obtient le r�sultat attendu
		 * 
		 * @return R�sultat attendu
		 */
		public Boolean getExpectedResult() {
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

			// Traitement des exceptions
			this.list.add(new TestSet(null, DEFAULT_VALUE));
			this.list.add(new TestSet("", DEFAULT_VALUE));
			this.list.add(new TestSet(" ", DEFAULT_VALUE));
		
			this.list.add(new TestSet("A", DEFAULT_VALUE));
			this.list.add(new TestSet("O", true));
			this.list.add(new TestSet("N", false));
			this.list.add(new TestSet("B", DEFAULT_VALUE));
			this.list.add(new TestSet("o", true));
			this.list.add(new TestSet("n", false));
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
	 * Teste la conversion d'un objet en type Boolean
	 */
	@Test
	public void testTryParse() {

		for (TestSet t : list) {

			Boolean result = BooleanHelper.tryParse(t.getValue());
			log.debug("Valeur : %s - Attendu : %s - Trouv� : %s", t.getValue(),
					t.getExpectedResult(), result);
			assertEquals(t.getExpectedResult(), result);
		}
	}
}