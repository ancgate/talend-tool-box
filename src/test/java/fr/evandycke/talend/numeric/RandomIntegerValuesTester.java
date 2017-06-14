package fr.evandycke.talend.numeric;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import fr.evandycke.talend.log.AppLog;

/**
 * RandomIntegerValuesTester est la classe de test d'obtention d'une valeur
 * aléatoire.
 * 
 * @author elie.vandycke
 * 
 */
public class RandomIntegerValuesTester extends TestCase {

	/**
	 * Helper logging
	 */
	private static AppLog log = AppLog.getInstanceForThisClass();

	/**
	 * Liste de valeurs
	 */
	private List<Integer> list;

	/**
	 * Tableau de valeurs
	 */
	private static final int[] VALUES = { 10, 20, 30, 40, 50 };

	/**
	 * Initialise le test unitaire
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {

		if (list == null) {
			list = new ArrayList<Integer>();
			for (int i : VALUES) {
				list.add(i);
			}
		}
	}

	/**
	 * Teste l'obtention d'une valeur aléatoire
	 */
	@Test
	public void testRandom() {

		int result = IntegerHelper.random(VALUES);
		log.info("Valeurs : %s - Trouvé : %s", list.toString(), result);
		assertTrue(list.contains(result));
	}
}
