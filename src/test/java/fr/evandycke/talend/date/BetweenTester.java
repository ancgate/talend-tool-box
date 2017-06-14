package fr.evandycke.talend.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.evandycke.talend.log.AppLog;

public class BetweenTester extends TestCase {

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
		private String date, dateMin, dateMax;
		private boolean includeBorne, expectedResult;
		private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

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
		public TestSet(String date, String dateMin, String dateMax, boolean includeBorne, boolean expectedResult) {
			this.date = date;
			this.dateMin = dateMin;
			this.dateMax = dateMax;
			this.includeBorne = includeBorne;
			this.expectedResult = expectedResult;
		}

		/**
		 * Obtient la valeur à tester
		 * 
		 * @return Valeur à tester
		 */
		public Date getDate() {
			try {
				return sdf.parse(date);
			} catch (NullPointerException e) {
				return null;
			} catch (ParseException p) {
				return null;
			}
		}

		/**
		 * Obtient la date minimale
		 * 
		 * @return Date minimale
		 */
		public Date getDateMin() {
			try {
				return sdf.parse(dateMin);
			} catch (NullPointerException e) {
				return null;
			} catch (ParseException p) {
				return null;
			}
		}

		/**
		 * Obtient la date maximale
		 * 
		 * @return Date maximale
		 */
		public Date getDateMax() {
			try {
				return sdf.parse(dateMax);
			} catch (NullPointerException e) {
				return null;
			} catch (ParseException p) {
				return null;
			}
		}

		/**
		 * Détermine si l'on doit inclure ou non la borne
		 * 
		 * @return Vrai si l'on doit inclure la borne ; Sinon Faux
		 */
		public boolean getIncludeBorne() {
			return includeBorne;
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

			list.add(new TestSet(null, null, null, false, false));
			list.add(new TestSet("11/12/1988", "11/12/1900", "11/12/2015", false, true));
			list.add(new TestSet("11/12/1888", "11/12/1900", "11/12/1890", false, false));
			list.add(new TestSet("11/12/1988", "11/12/1988", "11/12/2015", false, false));
			list.add(new TestSet("11/12/1988", "11/12/1988", "11/12/2015", true, true));
			list.add(new TestSet("11/12/2015", "11/12/1988", "11/12/2015", true, true));
			list.add(new TestSet("11/12/2015", "11/12/1988", "11/12/2015", false, false));
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
	 * Teste si la date est postérieure
	 */
	@Test
	public void testBetween() {

		for (TestSet t : list) {

			boolean result = DateHelper.between(t.getDate(), t.getDateMin(), t.getDateMax(), t.getIncludeBorne());
			log.debug("Date : %s - Date min : %s - Date max : %s - Borne inclue : %s - Attendu : %s - Trouvé : %s",
					t.date, t.dateMin, t.dateMax, t.includeBorne, t.expectedResult, result);
			assertEquals(t.getExpectedResult(), result);
		}
	}

}
