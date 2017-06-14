package fr.evandycke.talend.numeric;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import fr.evandycke.talend.log.AppLog;

/**
 * TryParseToBigDecimalTester est la classe de test de conversion d'un objet en
 * BigDecimal.
 * 
 * @author elie.vandycke
 * 
 */
public class TryParseToBigDecimalTester extends TestCase {

	static class TestSet {

		/**
		 * Valeur
		 */
		private Object value;

		/**
		 * Résultat attendu
		 */
		private BigDecimal expectedResult;

		/**
		 * Initialise une nouvelle instance de la classe TestSet
		 * 
		 * @param value
		 *            Valeur
		 * @param expectedResult
		 *            Résultat attendu
		 */
		public TestSet(Object value, BigDecimal expectedResult) {
			this.value = value;
			this.expectedResult = expectedResult;
		}

		/**
		 * Obtient le résultat attendu
		 * 
		 * @return Résultat attendu
		 */
		public BigDecimal getExpectedResult() {
			return expectedResult;
		}

		/**
		 * Obtient la valeur
		 * 
		 * @return Valeur
		 */
		public Object getValue() {
			return value;
		}
	}

	/**
	 * Helper logging
	 */
	private static AppLog log = AppLog.getInstanceForThisClass();

	/**
	 * Liste des éléments à tester
	 */
	private List<TestSet> list = new ArrayList<TestSet>();

	/**
	 * Initialise le test unitaire
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {

		if (this.list.size() > 0)
			return;

		// Traitement des exceptions
		this.list.add(new TestSet(null, BigDecimal.ZERO));
		this.list.add(new TestSet("", BigDecimal.ZERO));
		this.list.add(new TestSet("  ", BigDecimal.ZERO));

		// Traitement des valeurs de type String
		this.list.add(new TestSet("-1000", new BigDecimal(-1000)));
		this.list.add(new TestSet("-100", new BigDecimal(-100)));
		this.list.add(new TestSet("-10", new BigDecimal(-10)));
		this.list.add(new TestSet("-1", new BigDecimal(-1)));
		this.list.add(new TestSet("0", BigDecimal.ZERO));
		this.list.add(new TestSet("1", BigDecimal.ONE));
		this.list.add(new TestSet("10", BigDecimal.TEN));
		this.list.add(new TestSet("100", new BigDecimal(100)));
		this.list.add(new TestSet("1000", new BigDecimal(1000)));
		this.list.add(new TestSet("27,5", new BigDecimal("27.5")));
		this.list.add(new TestSet("27.5", new BigDecimal("27.5")));

		// Traitement des valeurs de type Integer
		this.list.add(new TestSet(new Integer(-1000), new BigDecimal(-1000)));
		this.list.add(new TestSet(new Integer(-100), new BigDecimal(-100)));
		this.list.add(new TestSet(new Integer(-10), new BigDecimal(-10)));
		this.list.add(new TestSet(new Integer(-1), new BigDecimal(-1)));
		this.list.add(new TestSet(new Integer(0), BigDecimal.ZERO));
		this.list.add(new TestSet(new Integer(1), BigDecimal.ONE));
		this.list.add(new TestSet(new Integer(10), BigDecimal.TEN));
		this.list.add(new TestSet(new Integer(100), new BigDecimal(100)));
		this.list.add(new TestSet(new Integer(1000), new BigDecimal(1000)));

		// Traitement des valeurs de type int
		this.list.add(new TestSet(-1000, new BigDecimal(-1000)));
		this.list.add(new TestSet(-100, new BigDecimal(-100)));
		this.list.add(new TestSet(-10, new BigDecimal(-10)));
		this.list.add(new TestSet(-1, new BigDecimal(-1)));
		this.list.add(new TestSet(0, BigDecimal.ZERO));
		this.list.add(new TestSet(1, BigDecimal.ONE));
		this.list.add(new TestSet(10, BigDecimal.TEN));
		this.list.add(new TestSet(100, new BigDecimal(100)));
		this.list.add(new TestSet(1000, new BigDecimal(1000)));

		// Traitement des valeurs de type Double
		this.list.add(new TestSet(new Double(-10.10), new BigDecimal("-10.1")));
		this.list.add(new TestSet(new Double(-9.99), new BigDecimal("-9.99")));
		this.list.add(new TestSet(new Double(-8.88), new BigDecimal("-8.88")));
		this.list.add(new TestSet(new Double(7.6), new BigDecimal("7.6")));
		this.list.add(new TestSet(new Double(5.4), new BigDecimal("5.4")));
		this.list.add(new TestSet(new Double(4.3), new BigDecimal("4.3")));
		this.list
				.add(new TestSet(new Double("25.34"), new BigDecimal("25.34")));
		this.list.add(new TestSet(new Double("-12.34"),
				new BigDecimal("-12.34")));

		// Traitement des valeurs de type BigDecimal
		this.list
				.add(new TestSet(new BigDecimal(-10.1), new BigDecimal(-10.1)));
		this.list
				.add(new TestSet(new BigDecimal(-9.99), new BigDecimal(-9.99)));
		this.list.add(new TestSet(new BigDecimal(7.6), new BigDecimal(7.60)));
		this.list.add(new TestSet(new BigDecimal(5.4), new BigDecimal(5.40)));
		this.list
				.add(new TestSet(new BigDecimal(25.34), new BigDecimal(25.34)));
		this.list.add(new TestSet(new BigDecimal(-12.34),
				new BigDecimal(-12.34)));
		this.list.add(new TestSet(new BigDecimal(-11), new BigDecimal(-11)));
		this.list.add(new TestSet(new BigDecimal(12), new BigDecimal(12)));
	}

	/**
	 * Teste la conversion d'un objet en BigDecimal, avec une valeur par défaut
	 */
	@Test
	public final void testTryParseToBigDecimal() {

		for (TestSet testSet : this.list) {
			BigDecimal result = BigDecimalHelper.tryParseToBigDecimal(testSet
					.getValue());

			log.info("Valeur : %s - Attendu : %s - Trouvé : %s",
					testSet.getValue(), testSet.getExpectedResult(), result);

			assertEquals(testSet.getExpectedResult(), result);
		}
	}
}
