package fr.evandycke.talend.string;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import junit.framework.TestCase;

import org.junit.Test;

import fr.evandycke.talend.log.AppLog;

/**
 * ConstructorTester est la classe de test du constructeur priv� de
 * StringHelper.
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
	 * Teste le constructeur priv�
	 * 
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 */
	@Test
	public void testPrivateConstructor() throws InstantiationException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {

		Constructor<StringHelper> constructor = StringHelper.class
				.getDeclaredConstructor();
		assertTrue(Modifier.isPrivate(constructor.getModifiers()));
		constructor.setAccessible(true);

		log.info("Test du constructeur priv� : %s", constructor);
		assertNotNull(constructor.newInstance());
	}

}
