package fr.evandycke.talend.date;

import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.evandycke.talend.log.AppLog;

/**
 * BeforeTester représente la classe de test d'une date antérieure à une autre.
 * 
 * @author elie.vandycke
 * 
 */
public class BeforeTester extends TestCase {

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
		 * Valeur de référence
		 */
		private Object refValue;

		/**
		 * Résultat attendu
		 */
		private boolean expectedResult;

		/**
		 * Initialise une nouvelle instance de la classe TestSet
		 * 
		 * @param value
		 *            Valeur
		 * @param refValue
		 *            Valeur de référence
		 * @param expectedResult
		 *            Résultat attendu
		 */
		public TestSet(Object value, Object refValue, boolean expectedResult) {

			this.value = value;
			this.refValue = refValue;
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
		 * Obtient la valeur de référence
		 * 
		 * @return Valeur de référence
		 */
		public Object getRefValue() {
			return refValue;
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

			list.add(new TestSet(null, null, false));
			list.add(new TestSet(DateHelper.getDate(1979, 12, 25), null, true));
			list.add(new TestSet(null, DateHelper.getDate(1979, 12, 25), false));
			list.add(new TestSet(DateHelper.getDate(1977, 10, 1), DateHelper.getDate(1979, 12, 25), true));
			list.add(new TestSet(DateHelper.getDate(1979, 12, 25), DateHelper.getDate(1979, 12, 25), false));
			list.add(new TestSet(DateHelper.getDate(2005, 11, 8), DateHelper.getDate(1979, 12, 25), false));
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
	 * Teste si la date est antérieure
	 */
	@Test
	public void testBefore() {

		for (TestSet t : list) {

			boolean result = DateHelper.before(t.getValue(), t.getRefValue());
			log.debug(
					"Valeur : %s - Valeur de référence : %s - Attendu : %s - Trouvé : %s",
					t.getValue(), t.getRefValue(), t.getExpectedResult(),
					result);
			assertEquals(t.getExpectedResult(), result);
		}
	}

}
