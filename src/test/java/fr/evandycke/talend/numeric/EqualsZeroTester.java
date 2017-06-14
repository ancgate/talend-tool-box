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
 * EqualsZeroTester repr�sente la classe de test de v�rification d'un objet
 * �quivalant � z�ro.
 * 
 * @author elie.vandycke
 * 
 */
public class EqualsZeroTester extends TestCase {

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
		private boolean expectedResult;

		/**
		 * Initialise une nouvelle instance de la classe TestSet
		 * 
		 * @param value
		 *            Valeur
		 * @param expectedResult
		 *            R�sultat attendu
		 */
		public TestSet(Object value, boolean expectedResult) {

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

		if (list.isEmpty()) {

			list.add(new TestSet(null, false));
			list.add(new TestSet(1, false));
			list.add(new TestSet("ABC", false));
			list.add(new TestSet("2", false));
			list.add(new TestSet(0, true));
			list.add(new TestSet(3l, false));
			list.add(new TestSet(0l, true));
			list.add(new TestSet(BigDecimal.ZERO, true));
			list.add(new TestSet(BigDecimal.ZERO.setScale(3), true));
			list.add(new TestSet("0.0000", true));
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
	 * Teste la v�rification d'un objet �quivalant � z�ro
	 */
	@Test
	public void testEqualsZero() {

		for (TestSet t : list) {

			boolean result = BigDecimalHelper.equalsZero(t.getValue());
			log.debug("Valeur : %s - Attendu : %s - Trouv� : %s", t.getValue(),
					t.getExpectedResult(), result);
			assertEquals(t.getExpectedResult(), result);
		}
	}
}
