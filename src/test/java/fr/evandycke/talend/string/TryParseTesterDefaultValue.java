package fr.evandycke.talend.string;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.evandycke.talend.log.AppLog;

import junit.framework.TestCase;

/**
 * TryParseTesterDefaultValue repr�sente la classe de test de conversion d'un
 * objet en type String.
 * 
 * @author elie.vandycke
 * 
 */
public class TryParseTesterDefaultValue extends TestCase {

	/**
	 * Valeur par d�faut
	 */
	private static final String DEFAULT_VALUE = "EVANDYCKE";

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
		private Object value;

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
		public TestSet(Object value, String expectedResult) {

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

			list.add(new TestSet("ABC", "ABC"));
			list.add(new TestSet(null, DEFAULT_VALUE));
			list.add(new TestSet(1, "1"));
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
	 * Teste la conversion d'un objet en type String, avec une valeur par d�faut
	 */
	@Test
	public void testConvertIntegerDefaultValue() {

		for (TestSet t : list) {

			String result = StringHelper.tryParse(t.getValue(), DEFAULT_VALUE);
			log.debug("Valeur : %s - Attendu : %s - Trouv� : %s", t.getValue(), t.getExpectedResult(),
					result);
			assertEquals(t.getExpectedResult(), result);
		}
	}
}
