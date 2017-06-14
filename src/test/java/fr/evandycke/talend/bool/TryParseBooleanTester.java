package fr.evandycke.talend.bool;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.evandycke.talend.log.AppLog;
import junit.framework.TestCase;

/**
 * TryParseBooleanTester représente la classe de test de conversion d'un objet
 * en type Boolean.
 * 
 * @author elie.vandycke
 * 
 */
public class TryParseBooleanTester extends TestCase {
	
	/**
	 * Valeur par défaut
	 */
	private final static Boolean DEFAULT_VALUE = false;

	/**
	 * TestSet représente un élément à tester.
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
		 * Résultat attendu
		 */
		private Boolean expectedResult;

		/**
		 * Initialise une nouvelle instance de la classe TestSet
		 * 
		 * @param value
		 *            Valeur
		 * @param expectedResult
		 *            Résultat attendu
		 */
		public TestSet(Object value, Boolean expectedResult) {

			this.value = value;
			this.expectedResult = expectedResult;
		}

		/**
		 * Obtient la valeur à tester
		 * 
		 * @return Valeur à tester
		 */
		public Object getValue() {
			return value;
		}

		/**
		 * Obtient le résultat attendu
		 * 
		 * @return Résultat attendu
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
	 * Liste des éléments à tester
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
			log.debug("Valeur : %s - Attendu : %s - Trouvé : %s", t.getValue(),
					t.getExpectedResult(), result);
			assertEquals(t.getExpectedResult(), result);
		}
	}
}