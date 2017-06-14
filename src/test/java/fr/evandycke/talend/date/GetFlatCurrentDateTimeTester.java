package fr.evandycke.talend.date;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.evandycke.talend.log.AppLog;
import junit.framework.TestCase;

/**
 * GetFlatCurrentDateTimeTester représente la classe de test d'obtention d'une
 * date/heure au format plat.
 * 
 * @author elie.vandycke
 * 
 */
public class GetFlatCurrentDateTimeTester extends TestCase {

	/**
	 * Valeur par défaut
	 */
	private static final String DEFAULT_VALUE = "";

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
		private Date value;

		/**
		 * Résultat attendu
		 */
		private String expectedResult;

		/**
		 * Initialise une nouvelle instance de la classe TestSet
		 * 
		 * @param value
		 *            Valeur
		 * @param expectedResult
		 *            Résultat attendu
		 */
		public TestSet(Date value, String expectedResult) {

			this.value = value;
			this.expectedResult = expectedResult;
		}

		/**
		 * Obtient la valeur à tester
		 * 
		 * @return Valeur à tester
		 */
		public Date getValue() {
			return value;
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

			list.add(new TestSet(null, DEFAULT_VALUE));

			Date valDate = DateHelper.getDate(1979, 12, 25);
			list.add(new TestSet(valDate, "19791225000000"));

			Date valDateTime = DateHelper.getDateTime(1977, 10, 1, 14, 15, 16);
			list.add(new TestSet(valDateTime, "19771001141516"));
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
	 * Teste l'obtention de la date et l'heure courante au format plat
	 */
	@Test
	public void testGetFlatCurrentDateTime() {

		for (TestSet t : list) {

			String result = DateHelper.getFlatCurrentDateTime(t.getValue());
			log.debug("Valeur : %s - Attendu : %s - Trouvé : %s", t.getValue(),
					t.getExpectedResult(), result);
			assertEquals(t.getExpectedResult(), result);
		}
	}
}
