package fr.evandycke.talend.numeric;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import junit.framework.TestCase;

import org.junit.Test;

import fr.evandycke.talend.log.AppLog;
import fr.evandycke.talend.numeric.IntegerHelper;

/**
 * ConstructorTester est la classe de test des constructeurs privés du package
 * Numeric.
 * 
 * @author elie.vandycke
 * 
 */
public class ConstructorTester extends TestCase {

	/**
	 * Helper de logging
	 */
	private static AppLog log = AppLog.getInstanceForThisClass();

	/**
	 * Teste le constructeur privé
	 * 
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 */
	@Test
	public void testIntegerHelperPrivateConstructor()
			throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException {

		Constructor<IntegerHelper> constructor = IntegerHelper.class
				.getDeclaredConstructor();
		assertTrue(Modifier.isPrivate(constructor.getModifiers()));
		constructor.setAccessible(true);

		log.info("Test du constructeur privé : %s", constructor);
		assertNotNull(constructor.newInstance());
	}
}
