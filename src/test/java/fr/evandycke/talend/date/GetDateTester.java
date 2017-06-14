package fr.evandycke.talend.date;

import java.util.Calendar;
import java.util.Locale;

import junit.framework.TestCase;

import org.junit.Test;

import fr.evandycke.talend.log.AppLog;

/**
 * GetDateTester représente la classe de test d'obtention d'une date.
 * 
 * @author elie.vandycke
 * 
 */
public class GetDateTester extends TestCase {

	/**
	 * Helper de logging
	 */
	private static AppLog log = AppLog.getInstanceForThisClass();

	/**
	 * Teste l'obtention d'une date
	 */
	@Test
	public void testGetDate() {

		Calendar cal = Calendar.getInstance(Locale.FRANCE);
		cal.set(Calendar.YEAR, 1979);
		cal.set(Calendar.MONTH, Calendar.DECEMBER);
		cal.set(Calendar.DATE, 25);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		log.debug("Attendu : %s - Trouvé : %s", cal.getTime(),
				DateHelper.getDate(1979, 12, 25));
		assertEquals(cal.getTime(), DateHelper.getDate(1979, 12, 25));
	}

	/**
	 * Teste l'obtention d'une date incohérente
	 */
	@Test
	public void testGetDateWrongMonth() {

		Calendar cal = Calendar.getInstance(Locale.FRANCE);
		cal.set(Calendar.YEAR, 1979);
		cal.set(Calendar.MONTH, 0);
		cal.set(Calendar.DATE, 25);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		log.debug("Attendu : %s - Trouvé : %s", cal.getTime(),
				DateHelper.getDate(1979, 1, 25));
		assertEquals(cal.getTime(), DateHelper.getDate(1979, 1, 25));
	}

	/**
	 * Teste l'obtention d'une date incohérente
	 */
	@Test
	public void testGetDateWrongDay() {

		Calendar cal = Calendar.getInstance(Locale.FRANCE);
		cal.set(Calendar.YEAR, 1979);
		cal.set(Calendar.MONTH, 11);
		cal.set(Calendar.DATE, 32);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		log.debug("Attendu : %s - Trouvé : %s", cal.getTime(),
				DateHelper.getDate(1980, 1, 1));
		assertEquals(cal.getTime(), DateHelper.getDate(1980, 1, 1));
	}
}
