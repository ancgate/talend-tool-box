package fr.evandycke.talend.cryptography;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.evandycke.talend.log.AppLog;
import junit.framework.TestCase;

/**
 * MD5SumTester représente la classe de test d'obtention d'une empreinte
 * numérique MD5.
 * 
 * @author elie.vandycke
 * 
 */
public class MD5SumTester extends TestCase {

	/**
	 * TestSet représente un élément à tester.
	 * 
	 * @author user
	 * 
	 */
	public class TestSet {

		/**
		 * Fichier
		 */
		private File file;

		/**
		 * Résultat attendu
		 */
		private String expectedResult;

		/**
		 * Initialise une nouvelle instance de la classe TestSet
		 * 
		 * @param file
		 *            Fichier
		 * @param expectedResult
		 *            Résultat attendu
		 */
		public TestSet(File file, String expectedResult) {

			this.file = file;
			this.expectedResult = expectedResult;
		}

		/**
		 * Obtient le fichier
		 * 
		 * @return Fichier
		 */
		public File getFile() {
			return file;
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

		}
	}

	/**
	 * Finalise le test unitaire
	 */
	@After
	public void tearDown() {

		list = null;
	}

	@Test
	public void testMD5SumTester() {

		for (TestSet t : list) {

			String result = FileHashHelper.md5sum(t.getFile());
			log.debug("Valeur : %s - Attendu : %s - Trouvé : %s", t.getFile(),
					t.getExpectedResult(), result);
			assertEquals(t.getExpectedResult(), result);
		}
	}
}
