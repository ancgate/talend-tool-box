package fr.evandycke.talend.numeric;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.evandycke.talend.log.AppLog;
import fr.evandycke.talend.numeric.IntegerHelper;

import junit.framework.TestCase;

/**
 * TryParseIntegerTester représente la classe de test de conversion d'un objet
 * en type Integer.
 * 
 * @author elie.vandycke
 * 
 */
public class TryParseIntegerTester extends TestCase {

	/**
	 * Valeur par défaut
	 */
	private static final Integer DEFAULT_VALUE = 0;

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
		private Integer expectedResult;

		/**
		 * Initialise une nouvelle instance de la classe TestSet
		 * 
		 * @param value
		 *            Valeur
		 * @param expectedResult
		 *            Résultat attendu
		 */
		public TestSet(Object value, Integer expectedResult) {

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
		public Integer getExpectedResult() {
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

			// Traitement des exceptions
			this.list.add(new TestSet(null, DEFAULT_VALUE));
			this.list.add(new TestSet("", DEFAULT_VALUE));
			this.list.add(new TestSet(" ", DEFAULT_VALUE));

			// Traitement des valeurs de type String
			this.list.add(new TestSet("-1000", new Integer(-1000)));
			this.list.add(new TestSet("-100", new Integer(-100)));
			this.list.add(new TestSet("-10", new Integer(-10)));
			this.list.add(new TestSet("-1", new Integer(-1)));
			this.list.add(new TestSet("0", new Integer(0)));
			this.list.add(new TestSet("1", new Integer(1)));
			this.list.add(new TestSet("10", new Integer(10)));
			this.list.add(new TestSet("100", new Integer(100)));
			this.list.add(new TestSet("1000", new Integer(1000)));
			this.list.add(new TestSet("1 ", new Integer(1)));
			this.list.add(new TestSet(" 1  ", new Integer(1)));

			// Traitement des valeurs de type Integer
			this.list.add(new TestSet(new Integer(-1000), new Integer(-1000)));
			this.list.add(new TestSet(new Integer(-100), new Integer(-100)));
			this.list.add(new TestSet(new Integer(-10), new Integer(-10)));
			this.list.add(new TestSet(new Integer(-1), new Integer(-1)));
			this.list.add(new TestSet(new Integer(0), new Integer(0)));
			this.list.add(new TestSet(new Integer(1), new Integer(1)));
			this.list.add(new TestSet(new Integer(10), new Integer(10)));
			this.list.add(new TestSet(new Integer(100), new Integer(100)));
			this.list.add(new TestSet(new Integer(1000), new Integer(1000)));

			// Traitement des valeurs de type int
			this.list.add(new TestSet(-1000, new Integer(-1000)));
			this.list.add(new TestSet(-100, new Integer(-100)));
			this.list.add(new TestSet(-10, new Integer(-10)));
			this.list.add(new TestSet(-1, new Integer(-1)));
			this.list.add(new TestSet(0, new Integer(0)));
			this.list.add(new TestSet(1, new Integer(1)));
			this.list.add(new TestSet(10, new Integer(10)));
			this.list.add(new TestSet(100, new Integer(100)));
			this.list.add(new TestSet(1000, new Integer(1000)));

			// Traitement des valeurs de type Double
			this.list.add(new TestSet(new Double(-10.10), new Integer(-10)));
			this.list.add(new TestSet(new Double(-9.99), new Integer(-9)));
			this.list.add(new TestSet(new Double(-8.88), new Integer(-8)));
			this.list.add(new TestSet(new Double(7.6), new Integer(7)));
			this.list.add(new TestSet(new Double(5.4), new Integer(5)));
			this.list.add(new TestSet(new Double(4.3), new Integer(4)));
			this.list.add(new TestSet(new Double("25.34"), new Integer(25)));
			this.list.add(new TestSet(new Double("-12.34"), new Integer(-12)));

			// Traitement des valeurs de type BigDecimal
			this.list
					.add(new TestSet(new BigDecimal(-10.10), new Integer(-10)));
			this.list.add(new TestSet(new BigDecimal(-9.99), new Integer(-9)));
			this.list.add(new TestSet(new BigDecimal(7.6), new Integer(7)));
			this.list.add(new TestSet(new BigDecimal(5.4), new Integer(5)));
			this.list
					.add(new TestSet(new BigDecimal("25.34"), new Integer(25)));
			this.list.add(new TestSet(new BigDecimal("-12.34"),
					new Integer(-12)));
			this.list.add(new TestSet(new BigDecimal(-11), new Integer(-11)));
			this.list.add(new TestSet(new BigDecimal(12), new Integer(12)));
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
	 * Teste la conversion d'un objet en type Integer
	 */
	@Test
	public void testTryParse() {

		for (TestSet t : list) {

			Integer result = IntegerHelper.tryParse(t.getValue());
			log.debug("Valeur : %s - Attendu : %s - Trouvé : %s", t.getValue(),
					t.getExpectedResult(), result);
			assertEquals(t.getExpectedResult(), result);
		}
	}
}
