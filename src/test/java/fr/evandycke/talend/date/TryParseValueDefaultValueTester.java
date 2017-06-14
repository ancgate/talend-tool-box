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
 * TryParseValueDefaultValueTester repr�sente la classe de test de conversion
 * d'un objet en type Date.
 * 
 * @author elie.vandycke
 * 
 */
public class TryParseValueDefaultValueTester extends TestCase {

	/**
	 * Date par d�faut
	 */
	private static final Date DEFAULT_VALUE = DateHelper.getDate(2005, 11, 8);

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
		private Date expectedResult;

		/**
		 * Initialise une nouvelle instance de la classe TestSet
		 * 
		 * @param value
		 *            Valeur
		 * @param expectedResult
		 *            R�sultat attendu
		 */
		public TestSet(Object value, Date expectedResult) {

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
		public Date getExpectedResult() {
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

			list.add(new TestSet(null, DEFAULT_VALUE));
			list.add(new TestSet("25/12/1979", DateHelper.getDate(1979, 12, 25)));
			list.add(new TestSet("ABC", DEFAULT_VALUE));
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

			Date result = DateHelper.tryParse(t.getValue(), DEFAULT_VALUE);
			log.debug("Valeur : %s - Attendu : %s - Trouv� : %s", t.getValue(), t.getExpectedResult(),
					result);
			assertEquals(t.getExpectedResult(), result);
		}
	}
}
