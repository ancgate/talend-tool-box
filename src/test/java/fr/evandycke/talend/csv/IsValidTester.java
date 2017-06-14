package fr.evandycke.talend.csv;

import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.evandycke.talend.log.AppLog;

/**
 * IsValidTester repr�sente la classe de test de validit� d'une ligne d'un fichier CSV.
 * 
 * @author elie.vandycke
 * 
 */
public class IsValidTester extends TestCase {

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
		private String value;

		/**
		 * R�sultat attendu
		 */
		private boolean expectedResult;

		/**
		 * Initialise une nouvelle instance de la classe TestSet
		 * 
		 * @param value
		 *            Valeur
		 * @param expectedResult
		 *            R�sultat attendu
		 */
		public TestSet(String value, boolean expectedResult) {

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
		public boolean getExpectedResult() {
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
		
		CsvFileParams params = new CsvFileParams(3, ";");
		CsvFileHelper.getInstance().setParams(params);

		if (list.isEmpty()) {

			list.add(new TestSet(null, false));
			list.add(new TestSet("", false));
			list.add(new TestSet(" ", false));
			list.add(new TestSet("A", false));
			list.add(new TestSet("A;B", false));
			list.add(new TestSet("A,B,C", false));
			list.add(new TestSet("A;B;C", true));
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
	 * Teste la validit� des paramètres
	 */
	@Test
	public void testIsValid() {

		for (TestSet t : list) {

			boolean result = CsvFileHelper.getInstance().isValid(t.getValue());
			log.debug("Valeur : %s - Attendu : %s - Trouv� : %s", t.getValue(),
					t.getExpectedResult(), result);
			assertEquals(t.getExpectedResult(), result);
		}
	}
}
