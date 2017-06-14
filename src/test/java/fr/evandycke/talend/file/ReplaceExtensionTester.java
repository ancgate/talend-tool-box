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
 * ReplaceExtensionTester repr�sente la classe de test de remplacement d'une
 * extension.
 * 
 * @author elie.vandycke
 * 
 */
public class ReplaceExtensionTester extends TestCase {

	/**
	 * Extension CSV
	 */
	private static final String EXT_CSV = ".csv";

	/**
	 * Extension XML
	 */
	private static final String EXT_XML = ".xml";

	/**
	 * Valeur CSV
	 */
	private static final String VALUE_CSV = "evandycke.csv";

	/**
	 * Valeur XML
	 */
	private static final String VALUE_XML = "evandycke.xml";

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
		 * Ancienne extension
		 */
		private String oldExt;

		/**
		 * Nouvelle extension
		 */
		private String newExt;

		/**
		 * list.add(new TestSet("   ", EXT_CSV, EXT_XML, "")); R�sultat attendu
		 */
		private String expectedResult;

		/**
		 * Initialise une nouvelle instance de la classe TestSet
		 * 
		 * @param value
		 *            Valeur
		 * @param oldExt
		 *            Ancienne extension
		 * @param newExt
		 *            Nouvelle extension
		 * @param expectedResult
		 *            R�sultat attendu
		 */
		public TestSet(String value, String oldExt, String newExt,
				String expectedResult) {

			this.value = value;
			this.oldExt = oldExt;
			this.newExt = newExt;
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
		 * Obtient l'ancienne extension
		 * 
		 * @return Ancienne extension
		 */
		public String getOldExt() {
			return oldExt;
		}

		/**
		 * Obtient la nouvelle extension
		 * 
		 * @return Nouvelle extension
		 */
		public String getNewExt() {
			return newExt;
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

			list.add(new TestSet(null, EXT_CSV, EXT_XML, ""));
			list.add(new TestSet("", EXT_CSV, EXT_XML, ""));
			list.add(new TestSet("   ", EXT_CSV, EXT_XML, ""));

			list.add(new TestSet(VALUE_CSV, null, EXT_XML, VALUE_CSV));
			list.add(new TestSet(VALUE_CSV, "", EXT_XML, VALUE_CSV));
			list.add(new TestSet(VALUE_CSV, "  ", EXT_XML, VALUE_CSV));

			list.add(new TestSet(VALUE_CSV, EXT_CSV, null, VALUE_CSV));
			list.add(new TestSet(VALUE_CSV, EXT_CSV, "", VALUE_CSV));
			list.add(new TestSet(VALUE_CSV, EXT_CSV, "  ", VALUE_CSV));

			list.add(new TestSet(VALUE_CSV, EXT_CSV, EXT_XML, VALUE_XML));
			list.add(new TestSet(VALUE_CSV, "csv", EXT_XML, VALUE_XML));

			list.add(new TestSet(VALUE_CSV, EXT_CSV, EXT_XML, VALUE_XML));
			list.add(new TestSet(VALUE_CSV, EXT_CSV, "xml", VALUE_XML));
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
	 * Teste le remplacement d'une extension
	 */
	@Test
	public void testReplaceExtension() {

		for (TestSet t : list) {

			String result = FileHelper.replaceExtension(t.getValue(),
					t.getOldExt(), t.getNewExt());
			log.debug(
					"Valeur : %s - Ancienne extension : %s - Nouvelle extension : %s - Attendu : %s - Trouv� : %s",
					t.getValue(), t.getOldExt(), t.getNewExt(),
					t.getExpectedResult(), result);
			assertEquals(t.getExpectedResult(), result);
		}
	}
}
