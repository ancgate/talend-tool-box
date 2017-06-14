package fr.evandycke.talend.date;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.evandycke.talend.log.AppLog;

/**
 * TransformDateTester représente la classe de test de transformation d'une
 * date.
 * 
 * @author elie.vandycke
 * 
 */
public class TransformDateTester extends TestCase {

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
		private String dateATransformer;

		/**
		 * Résultat attendu
		 */
		private String expectedResult;

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
		public TestSet(String valeur, String expectedResult) {

			this.dateATransformer = valeur;
			this.expectedResult = expectedResult;
		}

		/**
		 * Obtient la valeur à tester
		 * 
		 * @return Valeur à tester
		 */
		public String getValue() {
			return dateATransformer;
		}

		/**
		 * Obtient le résultat attendu
		 * 
		 * @return Résultat attendu
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
	 * Liste des éléments à tester
	 */
	private List<TestSet> list = new ArrayList<TestSet>();

	/**
	 * Initialise le test unitaire
	 */
	@Before
	public void setUp() {

		if (list.isEmpty()) {

			list.add(new TestSet(null, "null Format non géré"));
			list.add(new TestSet("15/11/1930", "15/11/1930"));
			list.add(new TestSet("1950-10-20", "20/10/1950"));
			list.add(new TestSet("6/6/90", "06/06/1990"));
			list.add(new TestSet("6/6/05", "06/06/2005"));
			list.add(new TestSet("6/16/05 15:25", "16/06/2005"));
			list.add(new TestSet("2015-10-15 15:25:25.265", "15/10/2015 15:25:25"));
			list.add(new TestSet("2015-10-15 15:25:02", "15/10/2015 15:25:02"));
			list.add(new TestSet("2015/10-15 15:25:25.265", "2015/10-15 15:25:25.265 Format non géré"));
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
	 * Teste la transformation d'une date
	 */
	@Test
	public void testTransform() {

		for (TestSet t : list) {

			String result = DateHelper.transformDate(t.getValue());
			log.debug("Valeur : %s - Attendu : %s - Trouvé : %s", t.getValue(), t.getExpectedResult(), result);
			assertEquals(t.getExpectedResult(), result);
		}
	}

}
