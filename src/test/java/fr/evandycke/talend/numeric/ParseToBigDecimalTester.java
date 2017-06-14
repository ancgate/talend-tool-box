package fr.evandycke.talend.numeric;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import fr.evandycke.talend.log.AppLog;

import junit.framework.TestCase;

/**
 * ParseToBigDecimalTester est la classe de test de conversion d'un objet en
 * BigDecimal.
 * 
 * @author elie.vandycke
 * 
 */
public class ParseToBigDecimalTester extends TestCase {

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

		if (this.list.size() > 0) {
			return;
		}

		// Traitement des exceptions
		this.list.add(new TestSet(null, null));
		this.list.add(new TestSet("", null));
		this.list.add(new TestSet("  ", null));

		// Traitement des valeurs de type String
		this.list.add(new TestSet("-1000", BigDecimal.valueOf(-1000)));
		this.list.add(new TestSet("-100", BigDecimal.valueOf(-100)));
		this.list.add(new TestSet("-10", BigDecimal.valueOf(-10)));
		this.list.add(new TestSet("-1", BigDecimal.valueOf(-1)));
		this.list.add(new TestSet("0", BigDecimal.ZERO));
		this.list.add(new TestSet("1", BigDecimal.ONE));
		this.list.add(new TestSet("10", BigDecimal.TEN));
		this.list.add(new TestSet("100", BigDecimal.valueOf(100)));
		this.list.add(new TestSet("1000", BigDecimal.valueOf(1000)));
		this.list.add(new TestSet("1 ", BigDecimal.valueOf(1)));
		this.list.add(new TestSet(" 1.0  ", new BigDecimal("1.0")));
		this.list.add(new TestSet("un", null));

		// Traitement des valeurs de type Integer
		this.list.add(new TestSet(Integer.valueOf(-1000), BigDecimal
				.valueOf(-1000)));
		this.list.add(new TestSet(Integer.valueOf(-100), BigDecimal
				.valueOf(-100)));
		this.list
				.add(new TestSet(Integer.valueOf(-10), BigDecimal.valueOf(-10)));
		this.list.add(new TestSet(Integer.valueOf(-1), BigDecimal.valueOf(-1)));
		this.list.add(new TestSet(Integer.valueOf(0), BigDecimal.ZERO));
		this.list.add(new TestSet(Integer.valueOf(1), BigDecimal.ONE));
		this.list.add(new TestSet(Integer.valueOf(10), BigDecimal.TEN));
		this.list
				.add(new TestSet(Integer.valueOf(100), BigDecimal.valueOf(100)));
		this.list.add(new TestSet(Integer.valueOf(1000), BigDecimal
				.valueOf(1000)));

		// Traitement des valeurs de type int
		this.list.add(new TestSet(-1000, BigDecimal.valueOf(-1000)));
		this.list.add(new TestSet(-100, BigDecimal.valueOf(-100)));
		this.list.add(new TestSet(-10, BigDecimal.valueOf(-10)));
		this.list.add(new TestSet(-1, BigDecimal.valueOf(-1)));
		this.list.add(new TestSet(0, BigDecimal.ZERO));
		this.list.add(new TestSet(1, BigDecimal.ONE));
		this.list.add(new TestSet(10, BigDecimal.TEN));
		this.list.add(new TestSet(100, BigDecimal.valueOf(100)));
		this.list.add(new TestSet(1000, BigDecimal.valueOf(1000)));

		// Traitement des valeurs de type Double
		this.list.add(new TestSet(Double.valueOf(-10.10), new BigDecimal(
				"-10.1")));
		this.list.add(new TestSet(Double.valueOf(-9.99),
				new BigDecimal("-9.99")));
		this.list.add(new TestSet(Double.valueOf(-8.88),
				new BigDecimal("-8.88")));
		this.list.add(new TestSet(Double.valueOf(7.6), new BigDecimal("7.6")));
		this.list.add(new TestSet(Double.valueOf(5.4), new BigDecimal("5.4")));
		this.list.add(new TestSet(Double.valueOf(4.3), new BigDecimal("4.3")));
		this.list.add(new TestSet(Double.valueOf("25.34"), new BigDecimal(
				"25.34")));
		this.list.add(new TestSet(Double.valueOf("-12.34"), new BigDecimal(
				"-12.34")));

		// Traitement des valeurs de type BigDecimal
		this.list.add(new TestSet(BigDecimal.valueOf(-10.10), BigDecimal
				.valueOf(-10.10)));
		this.list.add(new TestSet(BigDecimal.valueOf(-9.99), BigDecimal
				.valueOf(-9.99)));
		this.list.add(new TestSet(BigDecimal.valueOf(7.6), BigDecimal
				.valueOf(7.6)));
		this.list.add(new TestSet(BigDecimal.valueOf(5.4), BigDecimal
				.valueOf(5.4)));
		this.list.add(new TestSet(new BigDecimal("25.34"), new BigDecimal(
				"25.34")));
		this.list.add(new TestSet(new BigDecimal("-12.34"), new BigDecimal(
				"-12.34")));
		this.list.add(new TestSet(BigDecimal.valueOf(-11), BigDecimal
				.valueOf(-11)));
		this.list.add(new TestSet(BigDecimal.valueOf(12), BigDecimal
				.valueOf(12)));

		// Traitement des valeurs de type Long
		this.list.add(new TestSet(Long.valueOf(-1000), BigDecimal
				.valueOf(-1000)));
		this.list
				.add(new TestSet(Long.valueOf(-100), BigDecimal.valueOf(-100)));
		this.list.add(new TestSet(Long.valueOf(-10), BigDecimal.valueOf(-10)));
		this.list.add(new TestSet(Long.valueOf(-1), BigDecimal.valueOf(-1)));
		this.list.add(new TestSet(Long.valueOf(0), BigDecimal.ZERO));
		this.list.add(new TestSet(Long.valueOf(1), BigDecimal.ONE));
		this.list.add(new TestSet(Long.valueOf(10), BigDecimal.TEN));
		this.list.add(new TestSet(Long.valueOf(100), BigDecimal.valueOf(100)));
		this.list
				.add(new TestSet(Long.valueOf(1000), BigDecimal.valueOf(1000)));

		// Traitement des valeurs de type Float
		this.list.add(new TestSet(Float.valueOf(-1000), new BigDecimal(
				"-1000.0")));
		this.list
				.add(new TestSet(Float.valueOf(-100), new BigDecimal("-100.0")));
		this.list.add(new TestSet(Float.valueOf(-10), new BigDecimal("-10.0")));
		this.list.add(new TestSet(Float.valueOf(-1), new BigDecimal("-1.0")));
		this.list.add(new TestSet(Float.valueOf(0), new BigDecimal("0.0")));
		this.list.add(new TestSet(Float.valueOf(1), new BigDecimal("1.0")));
		this.list.add(new TestSet(Float.valueOf(10), new BigDecimal("10.0")));
		this.list.add(new TestSet(Float.valueOf(100), new BigDecimal("100.0")));
		this.list
				.add(new TestSet(Float.valueOf(1000), new BigDecimal("1000.0")));
	}

	/**
	 * Teste la conversion d'un objet en BigDecimal
	 */
	@Test
	public final void testParseToBigDecimal() {

		for (TestSet testSet : this.list) {
			try {
				BigDecimal result = BigDecimalHelper.parseToBigDecimal(testSet
						.getValue());

				log.info("Valeur : %s - Attendu : %s - Trouvé : %s",
						testSet.getValue(), testSet.getExpectedResult(), result);

				assertEquals(testSet.getExpectedResult(), result);
			} catch (Exception e) {
				fail();
				return;
			}
		}
	}
}
