package fr.evandycke.talend.numeric;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import fr.evandycke.talend.log.AppLog;

/**
 * RandomIntegerTester est la classe de test d'obtention d'une valeur aléatoire.
 * 
 * @author elie.vandycke
 * 
 */
public class RandomIntegerTester extends TestCase {

	/**
	 * Helper logging
	 */
	private static AppLog log = AppLog.getInstanceForThisClass();

	/**
	 * Initialise le test unitaire
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {

	}

	/**
	 * Teste l'obtention d'une valeur aléatoire
	 */
	@Test
	public void testRandom() {

		int min = 0;
		int max = 5;
		int result = IntegerHelper.random(min, max);
		log.info("Min : %s - Max : %s - Trouvé : %s", min, max, result);
		assertTrue(min <= result);
		assertTrue(result <= max);
	}

	/**
	 * Teste l'obtention d'une valeur aléatoire avec des bornes incohérentes
	 */
	@Test
	public void testRandomWithWrongValue() {

		int min = 5;
		int max = 0;
		int expected = 0;
		int result = IntegerHelper.random(min, max);
		log.info("Min : %s - Max : %s - Attendu : %s - Trouvé : %s", min, max,
				expected, result);
		assertEquals(expected, result);
	}
}
