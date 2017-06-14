package fr.evandycke.talend.csv;

import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.evandycke.talend.log.AppLog;

/**
 * IsValidTester représente la classe de test de validité d'une ligne d'un fichier CSV.
 * 
 * @author elie.vandycke
 * 
 */
public class IsValidTester extends TestCase {

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
		private String value;

		/**
		 * Résultat attendu
		 */
		private boolean expectedResult;

		/**
		 * Initialise une nouvelle instance de la classe TestSet
		 * 
		 * @param value
		 *            Valeur
		 * @param expectedResult
		 *            Résultat attendu
		 */
		public TestSet(String value, boolean expectedResult) {

			this.value = value;
			this.expectedResult = expectedResult;
		}

		/**
		 * Obtient la valeur à tester
		 * 
		 * @return Valeur à tester
		 */
		public String getValue() {
			return value;
		}

		/**
		 * Obtient le résultat attendu
		 * 
		 * @return Résultat attendu
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
	 * Teste la validité des paramÃ¨tres
	 */
	@Test
	public void testIsValid() {

		for (TestSet t : list) {

			boolean result = CsvFileHelper.getInstance().isValid(t.getValue());
			log.debug("Valeur : %s - Attendu : %s - Trouvé : %s", t.getValue(),
					t.getExpectedResult(), result);
			assertEquals(t.getExpectedResult(), result);
		}
	}
}
