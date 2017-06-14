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
 * TryParseFormatValueTester représente la classe de test de conversion d'un
 * objet en type Date.
 * 
 * @author elie.vandycke
 * 
 */
public class TryParseFormatValueTester extends TestCase {

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
		 * Format
		 */
		private String format;

		/**
		 * Résultat attendu
		 */
		private Date expectedResult;

		/**
		 * Initialise une nouvelle instance de la classe TestSet
		 * 
		 * @param value
		 *            Valeur
		 * @param format
		 *            Format
		 * @param expectedResult
		 *            Résultat attendu
		 */
		public TestSet(Object value, String format, Date expectedResult) {

			this.value = value;
			this.format = format;
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
		 * Obtient le format
		 * 
		 * @return Format
		 */
		public String getFormat() {
			return format;
		}

		/**
		 * Obtient le résultat attendu
		 * 
		 * @return Résultat attendu
		 */
		public Date getExpectedResult() {
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

			list.add(new TestSet(null, "dd/MM/yyyy", null));
			list.add(new TestSet("25/12/1979", "dd/MM/yyyy", DateHelper
					.getDate(1979, 12, 25)));
			list.add(new TestSet("ABC", "dd/MM/yyyy", null));
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
	 * Teste la conversion d'un objet en type Date
	 */
	@Test
	public void testTryParse() {

		for (TestSet t : list) {

			Date result = DateHelper
					.tryParseFormat(t.getFormat(), t.getValue());
			log.debug("Valeur : %s - Format : %s - Attendu : %s - Trouvé : %s",
					t.getValue(), t.getFormat(), t.getExpectedResult(), result);
			assertEquals(t.getExpectedResult(), result);
		}
	}
}
