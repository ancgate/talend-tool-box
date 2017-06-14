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
 * RemoveExtensionTester représente la classe de test de suppression d'une
 * extension.
 * 
 * @author elie.vandycke
 * 
 */
public class RemoveExtensionTester extends TestCase {

	/**
	 * Extension CSV
	 */
	private static final String EXT_CSV = ".csv";

	/**
	 * Valeur CSV
	 */
	private static final String VALUE_CSV = "evandycke.csv";
	
	/**
	 * Valeur CSV sans extension
	 */
	private static final String VALUE_CSV_SANS_EXT = "evandycke";

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
		 * Extension
		 */
		private String ext;

		/**
		 * Résultat attendu
		 */
		private String expectedResult;

		/**
		 * Initialise une nouvelle instance de la classe TestSet
		 * 
		 * @param value
		 *            Valeur
		 * @param ext
		 *            Extension
		 * @param expectedResult
		 *            Résultat attendu
		 */
		public TestSet(String value, String ext, String expectedResult) {

			this.value = value;
			this.ext = ext;
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
		 * Obtient l'extension
		 * 
		 * @return Extension
		 */
		public String getExt() {
			return ext;
		}

		/**
		 * Obtient le résultat attendu
		 * 
		 * @return Résultat attendu
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
	 * Liste des éléments à tester
	 */
	private List<TestSet> list = new ArrayList<TestSet>();

	/**
	 * Initialise le test unitaire
	 */
	@Before
	public void setUp() {

		if (list.isEmpty()) {

			list.add(new TestSet(null, EXT_CSV, ""));
			list.add(new TestSet("", EXT_CSV, ""));
			list.add(new TestSet("   ", EXT_CSV, ""));

			list.add(new TestSet(VALUE_CSV, null, VALUE_CSV));
			list.add(new TestSet(VALUE_CSV, "", VALUE_CSV));
			list.add(new TestSet(VALUE_CSV, "  ", VALUE_CSV));

			list.add(new TestSet(VALUE_CSV, EXT_CSV, VALUE_CSV_SANS_EXT));
			list.add(new TestSet(VALUE_CSV, "csv", VALUE_CSV_SANS_EXT));
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
	 * Teste la suppression d'une extension
	 */
	@Test
	public void testRemoveExtension() {

		for (TestSet t : list) {

			String result = FileHelper.removeExtension(t.getValue(), t.getExt());
			log.debug(
					"Valeur : %s - Extension : %s - Attendu : %s - Trouvé : %s",
					t.getValue(), t.getExt(), t.getExpectedResult(), result);
			assertEquals(t.getExpectedResult(), result);
		}
	}
}
