package fr.evandycke.talend.file;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import junit.framework.TestCase;

import org.junit.Test;

import fr.evandycke.talend.log.AppLog;
import fr.evandycke.talend.file.FileHelper;

/**
 * ConstructorTester est la classe de test du constructeur privé de
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
	public void testPrivateConstructor() throws InstantiationException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {

		Constructor<FileHelper> constructor = FileHelper.class
				.getDeclaredConstructor();
		assertTrue(Modifier.isPrivate(constructor.getModifiers()));
		constructor.setAccessible(true);

		log.info("Test du constructeur privé : %s", constructor);
		assertNotNull(constructor.newInstance());
	}

}
