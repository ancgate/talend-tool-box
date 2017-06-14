package fr.evandycke.talend.numeric;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.evandycke.talend.log.AppLog;
import junit.framework.TestCase;

/**
 * NotEqualsZeroTester représente la classe de test de vérification d'un objet
 * différent de zéro.
 * 
 * @author elie.vandycke
 * 
 */
public class NotEqualsZeroTester extends TestCase {

	/**
	 * TestSet représente un élément à tester.
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
		public TestSet(Object value, boolean expectedResult) {

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

			list.add(new TestSet(null, true));
			list.add(new TestSet(1, true));
			list.add(new TestSet("ABC", true));
			list.add(new TestSet("2", true));
			list.add(new TestSet(0, false));
			list.add(new TestSet(3l, true));
			list.add(new TestSet(0l, false));
			list.add(new TestSet(BigDecimal.ZERO, false));
			list.add(new TestSet(BigDecimal.ZERO.setScale(3), false));
			list.add(new TestSet("0.0000", false));
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
	 * Teste la vérification d'un objet différent de zéro
	 */
	@Test
	public void testNotEqualsZero() {

		for (TestSet t : list) {

			boolean result = BigDecimalHelper.notEqualsZero(t.getValue());
			log.debug("Valeur : %s - Attendu : %s - Trouvé : %s", t.getValue(),
					t.getExpectedResult(), result);
			assertEquals(t.getExpectedResult(), result);
		}
	}
}
