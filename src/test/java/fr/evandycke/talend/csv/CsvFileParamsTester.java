package fr.evandycke.talend.csv;

import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.evandycke.talend.log.AppLog;

/**
 * CsvFileParamsTester représente la classe de test des paramètres d'un fichier
 * CSV.
 * 
 * @author elie.vandycke
 * 
 */
public class CsvFileParamsTester extends TestCase {

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
		private CsvFileParams value;

		/**
		 * Résultat attendu
		 */
		private boolean expectedResult;

		/**
		 * Initialise une nouvelle instance de la classe TestSet
		 * 
		 * @param value
		 *            Valeur
		 * @param expectedResult
		 *            Résultat attendu
		 */
		public TestSet(CsvFileParams value, boolean expectedResult) {

			this.value = value;
			this.expectedResult = expectedResult;
		}

		/**
		 * Obtient la valeur à tester
		 * 
		 * @return Valeur à tester
		 */
		public CsvFileParams getValue() {
			return value;
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
	 * Paramètres d'un fichier CSV
	 */
	private CsvFileParams params;

	/**
	 * Nombre de colonnes
	 */
	private static final int NB_COLONNES = 8;

	/**
	 * Séparateur
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
	 * Teste l'obtention et la définition des valeurs
	 */
	@Test
	public void testGetterSetter() {

		log.debug("Longueur - Attendu : %s - Trouvé : %s", NB_COLONNES,
				params.getNbColonnes());
		assertEquals(NB_COLONNES, params.getNbColonnes());

		log.debug("Séparateur - Attendu : %s - Trouvé : %s", SEPARATEUR,
				params.getSeparator());
		assertEquals(SEPARATEUR, params.getSeparator());
	}

	/**
	 * Teste la validité des paramètres
	 */
	@Test
	public void testIsValid() {

		for (TestSet t : list) {

			boolean result = t.getValue().isValid();
			log.debug("Valeur : %s - Attendu : %s - Trouvé : %s", t.getValue(),
					t.getExpectedResult(), result);
			assertEquals(t.getExpectedResult(), result);
		}
	}
}
