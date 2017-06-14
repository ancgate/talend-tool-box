package fr.evandycke.talend.csv;

import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.evandycke.talend.log.AppLog;

/**
 * CsvFileParamsTester repr�sente la classe de test des param�tres d'un fichier
 * CSV.
 * 
 * @author elie.vandycke
 * 
 */
public class CsvFileParamsTester extends TestCase {

	/**
	 * TestSet repr�sente un �l�ment � tester.
	 * 
	 * @author user
	 * 
	 */
	public class TestSet {

		/**
		 * Valeur
		 */
		private CsvFileParams value;

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
		public TestSet(CsvFileParams value, boolean expectedResult) {

			this.value = value;
			this.expectedResult = expectedResult;
		}

		/**
		 * Obtient la valeur � tester
		 * 
		 * @return Valeur � tester
		 */
		public CsvFileParams getValue() {
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
	 * Param�tres d'un fichier CSV
	 */
	private CsvFileParams params;

	/**
	 * Nombre de colonnes
	 */
	private static final int NB_COLONNES = 8;

	/**
	 * S�parateur
	 */
	private static final String SEPARATEUR = ";";

	/**
	 * Initialise le test unitaire
	 */
	@Before
	public void setUp() {

		if (params == null) {
			params = new CsvFileParams();
			params.setNbColonnes(NB_COLONNES);
			params.setSeparator(SEPARATEUR);
		}

		if (list.isEmpty()) {

			list.add(new TestSet(new CsvFileParams(), false));
			list.add(new TestSet(new CsvFileParams(-1,";"), false));
			list.add(new TestSet(new CsvFileParams(0,";"), false));
			list.add(new TestSet(new CsvFileParams(1,null), false));
			list.add(new TestSet(new CsvFileParams(2,""), false));
			list.add(new TestSet(new CsvFileParams(3," "), false));
			list.add(new TestSet(new CsvFileParams(4,";;"), false));
			list.add(new TestSet(new CsvFileParams(5,";"), true));
		}
	}

	/**
	 * Finalise le test unitaire
	 */
	@After
	public void tearDown() {

		list = null;
		params = null;
	}

	/**
	 * Teste l'obtention et la d�finition des valeurs
	 */
	@Test
	public void testGetterSetter() {

		log.debug("Longueur - Attendu : %s - Trouv� : %s", NB_COLONNES,
				params.getNbColonnes());
		assertEquals(NB_COLONNES, params.getNbColonnes());

		log.debug("S�parateur - Attendu : %s - Trouv� : %s", SEPARATEUR,
				params.getSeparator());
		assertEquals(SEPARATEUR, params.getSeparator());
	}

	/**
	 * Teste la validit� des param�tres
	 */
	@Test
	public void testIsValid() {

		for (TestSet t : list) {

			boolean result = t.getValue().isValid();
			log.debug("Valeur : %s - Attendu : %s - Trouv� : %s", t.getValue(),
					t.getExpectedResult(), result);
			assertEquals(t.getExpectedResult(), result);
		}
	}
}
