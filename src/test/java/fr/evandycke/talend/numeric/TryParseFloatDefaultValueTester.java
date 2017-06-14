package fr.evandycke.talend.numeric;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import fr.evandycke.talend.log.AppLog;

/**
 * TryParseFloatDefaultValueTester est la classe de test de conversion d'un
 * objet en Float, avec une valeur par défaut.
 * 
 * @author elie.vandycke
 * 
 */
public class TryParseFloatDefaultValueTester extends TestCase {

	/**
	 * Valeur par défaut
	 */
	private static final Float DEFAULT_VALUE = Float.valueOf("1");

	/**
	 * TestSet est la classe d'un élément testé dans ce test unitaire.
	 * 
	 * @author elie.vandycke
	 * 
	 */
	static class TestSet {

		/**
		 * Valeur
		 */
		private Object value;

		/**
		 * Résultat attendu
		 */
		private Float expectedResult;

		/**
		 * Initialise une nouvelle instance de la classe TestSet
		 * 
		 * @param value
		 *            Valeur
		 * @param expectedResult
		 *            Résultat attendu
		 */
		public TestSet(Object value, Float expectedResult) {
			this.value = value;
			this.expectedResult = expectedResult;
		}

		/**
		 * Obtient le résultat attendu
		 * 
		 * @return Résultat attendu
		 */
		public Float getExpectedResult() {
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

		if (this.list.isEmpty()) {

			// Traitement des exceptions
			this.list.add(new TestSet(null, DEFAULT_VALUE));
			this.list.add(new TestSet("", DEFAULT_VALUE));

			// Traitement des valeurs de type String
			this.list.add(new TestSet("-1000", new Float(-1000)));
			this.list.add(new TestSet("-100", new Float(-100)));
			this.list.add(new TestSet("-10", new Float(-10)));
			this.list.add(new TestSet("-1", new Float(-1)));
			this.list.add(new TestSet("0", new Float(0)));
			this.list.add(new TestSet("1", new Float(1)));
			this.list.add(new TestSet("10", new Float(10)));
			this.list.add(new TestSet("100", new Float(100)));
			this.list.add(new TestSet("1000", new Float(1000)));

			// Traitement des valeurs de type Integer
			this.list.add(new TestSet(new Integer(-1000), new Float(-1000)));
			this.list.add(new TestSet(new Integer(-100), new Float(-100)));
			this.list.add(new TestSet(new Integer(-10), new Float(-10)));
			this.list.add(new TestSet(new Integer(-1), new Float(-1)));
			this.list.add(new TestSet(new Integer(0), new Float(0)));
			this.list.add(new TestSet(new Integer(1), new Float(1)));
			this.list.add(new TestSet(new Integer(10), new Float(10)));
			this.list.add(new TestSet(new Integer(100), new Float(100)));
			this.list.add(new TestSet(new Integer(1000), new Float(1000)));

			// Traitement des valeurs de type int
			this.list.add(new TestSet(-1000, new Float(-1000)));
			this.list.add(new TestSet(-100, new Float(-100)));
			this.list.add(new TestSet(-10, new Float(-10)));
			this.list.add(new TestSet(-1, new Float(-1)));
			this.list.add(new TestSet(0, new Float(0)));
			this.list.add(new TestSet(1, new Float(1)));
			this.list.add(new TestSet(10, new Float(10)));
			this.list.add(new TestSet(100, new Float(100)));
			this.list.add(new TestSet(1000, new Float(1000)));

			// Traitement des valeurs de type Double
			this.list.add(new TestSet(new Double(-10.10), new Float(-10.1)));
			this.list.add(new TestSet(new Double(-9.99), new Float(-9.99)));
			this.list.add(new TestSet(new Double(-8.88), new Float(-8.88)));
			this.list.add(new TestSet(new Double(7.6), new Float(7.6)));
			this.list.add(new TestSet(new Double(5.4), new Float(5.4)));
			this.list.add(new TestSet(new Double(4.3), new Float(4.3)));
			this.list.add(new TestSet(new Double("25.34"), new Float("25.34")));
			this.list
					.add(new TestSet(new Double("-12.34"), new Float("-12.34")));

			// Traitement des valeurs de type BigDecimal
			this.list
					.add(new TestSet(new BigDecimal(-10.10), new Float(-10.1)));
			this.list.add(new TestSet(new BigDecimal(-9.99), new Float(-9.99)));
			this.list.add(new TestSet(new BigDecimal(7.6), new Float(7.6)));
			this.list.add(new TestSet(new BigDecimal(5.4), new Float(5.4)));
			this.list.add(new TestSet(new BigDecimal("25.34"), new Float(
					"25.34")));
			this.list.add(new TestSet(new BigDecimal("-12.34"), new Float(
					"-12.34")));
			this.list.add(new TestSet(new BigDecimal(-11), new Float(-11)));
			this.list.add(new TestSet(new BigDecimal(12), new Float(12)));

			// Traitement des valeurs de type Float
			this.list.add(new TestSet(new Float(-10.10), new Float(-10.1)));
			this.list.add(new TestSet(new Float(-9.99), new Float(-9.99)));
			this.list.add(new TestSet(new Float(7.6), new Float(7.6)));
			this.list.add(new TestSet(new Float(5.4), new Float(5.4)));
			this.list.add(new TestSet(new Float("25.34"), new Float("25.34")));
			this.list
					.add(new TestSet(new Float("-12.34"), new Float("-12.34")));
			this.list.add(new TestSet(new Float(-11), new Float(-11)));
			this.list.add(new TestSet(new Float(12), new Float(12)));
		}
	}

	/**
	 * Teste la conversion d'un objet en Float
	 */
	@Test
	public final void testTryParseToFloatObjectFloat() {

		for (TestSet t : this.list) {
			Float result = FloatHelper.tryParse(t.getValue(), DEFAULT_VALUE);

			log.info("Valeur : %s - Attendu : %s - Trouvé : %s", t.getValue(),
					t.getExpectedResult(), result);
			assertEquals(t.getExpectedResult(), result);
		}
	}
}
