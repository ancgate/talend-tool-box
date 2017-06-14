package fr.evandycke.talend.file;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.evandycke.talend.log.AppLog;
import fr.evandycke.talend.file.FileHelper;
import junit.framework.TestCase;

/**
 * IsValidNameTester représente la classe de test de validité d'un nom de
 * fichier.
 * 
 * @author elie.vandycke
 * 
 */
public class IsValidNameTester extends TestCase {

	/**
	 * Valeur CSV
	 */
	private static final String VALUE_CSV_LO = "evandycke.csv";

	/**
	 * Valeur CSV
	 */
	private static final String VALUE_CSV_UP = "evandycke.CSV";

	/**
	 * Pattern
	 */
	private static final String PATTERN = "([^\\s]+(\\.(?i)(csv))$)";

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
		 * Pattern
		 */
		private String pattern;

		/**
		 * Résultat attendu
		 */
		private boolean expectedResult;

		/**
		 * Initialise une nouvelle instance de la classe TestSet
		 * 
		 * @param value
		 *            Valeur
		 * @param pattern
		 *            Pattern
		 * @param expectedResult
		 *            Résultat attendu
		 */
		public TestSet(String value, String pattern, boolean expectedResult) {

			this.value = value;
			this.pattern = pattern;
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
		 * Obtient le pattern
		 * 
		 * @return Pattern
		 */
		public String getPattern() {
			return pattern;
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

		if (list.isEmpty()) {

			list.add(new TestSet(null, PATTERN, false));
			list.add(new TestSet("", PATTERN, false));
			list.add(new TestSet("   ", PATTERN, false));

			list.add(new TestSet(VALUE_CSV_LO, null, false));
			list.add(new TestSet(VALUE_CSV_LO, "", false));
			list.add(new TestSet(VALUE_CSV_LO, "  ", false));

			list.add(new TestSet(VALUE_CSV_UP, null, false));
			list.add(new TestSet(VALUE_CSV_UP, "", false));
			list.add(new TestSet(VALUE_CSV_UP, "  ", false));

			list.add(new TestSet(VALUE_CSV_LO, PATTERN, true));
			list.add(new TestSet("evandycke", PATTERN, false));
			list.add(new TestSet(VALUE_CSV_UP, PATTERN, true));
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
	 * Teste la validité d'un nom de fichier
	 */
	@Test
	public void testIsValidName() {

		for (TestSet t : list) {

			boolean result = FileHelper.isValidName(t.getValue(),
					t.getPattern());
			log.debug(
					"Valeur : %s - Pattern : %s - Attendu : %s - Trouvé : %s",
					t.getValue(), t.getPattern(), t.getExpectedResult(), result);
			assertEquals(t.getExpectedResult(), result);
		}
	}
}
