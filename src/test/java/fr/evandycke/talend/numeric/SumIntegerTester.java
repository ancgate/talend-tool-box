package fr.evandycke.talend.numeric;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import fr.evandycke.talend.log.AppLog;
import junit.framework.TestCase;

/**
 * SumIntegerTester représente la classe de test du calcul d'une somme d'Integer
 * 
 * @author elie.vandycke
 * 
 */
public class SumIntegerTester extends TestCase {

	/**
	 * TestSet représente un élément testé
	 * 
	 * @author elie.vandycke
	 * 
	 */
	static class TestSet {

		/**
		 * Valeurs
		 */
		private Integer[] values;

		/**
		 * Résultat attendu
		 */
		private int expectedResult;

		/**
		 * Initialise une nouvelle instance de la classe TestSet
		 * 
		 * @param values
		 *            Valeurs
		 * @param expectedResult
		 *            Résultat attendu
		 */
		public TestSet(Integer[] values, int expectedResult) {
			this.values = values;
			this.expectedResult = expectedResult;
		}

		/**
		 * Obtient le résultat attendu
		 * 
		 * @return Résultat attendu
		 */
		public int getExpectedResult() {
			return expectedResult;
		}

		/**
		 * Obtient la valeur
		 * 
		 * @return Valeur
		 */
		public Integer[] getValues() {
			return values;
		}

		/**
		 * Obtient les valeurs dans une chaine de caractère
		 * 
		 * @return Valeurs dans une chaine de caractère
		 */
		public String getValuesInString() {

			StringBuilder strBld = new StringBuilder();

			if (values != null) {
				for (final Integer v : values) {
					if (v != null) {
						strBld.append(v).append(" ");
					} else {
						strBld.append("null").append(" ");
					}
				}
			}

			return strBld.toString();
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

			this.list.add(new TestSet(null, 0));

			Integer[] values1 = { 1, 2, 3 };
			this.list.add(new TestSet(values1, 6));

			Integer[] values2 = { 1, null, 3 };
			this.list.add(new TestSet(values2, 4));

			Integer[] values3 = { null, 2, 3 };
			this.list.add(new TestSet(values3, 5));

			Integer[] values4 = { 1, 2, null };
			this.list.add(new TestSet(values4, 3));

			Integer[] values5 = { null, null, null };
			this.list.add(new TestSet(values5, 0));
		}
	}

	/**
	 * Teste la somme
	 */
	@Test
	public void testSum() {

		for (TestSet t : this.list) {
			int result = IntegerHelper.sum(t.getValues());
			log.debug("Valeur : %s - Attendu : %s - Trouvé : %s",
					t.getValuesInString(), t.getExpectedResult(), result);
			assertEquals(t.getExpectedResult(), result);
		}
	}
}
