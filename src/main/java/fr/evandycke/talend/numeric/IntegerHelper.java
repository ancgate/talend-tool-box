package fr.evandycke.talend.numeric;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fr.evandycke.talend.string.StringHelper;

/**
 * IntegerHelper est la classe des outils de manipulation des objets de type
 * Integer.
 * 
 * @author elie.vandycke
 * 
 */
public final class IntegerHelper {

	/**
	 * Entier par d�faut
	 */
	private static final Integer DEFAULT_INTEGER = 0;

	/**
	 * D�termine si l'objet sp�cifi� est nul ou vaut z�ro
	 * 
	 * @param value
	 *            Valeur � tester
	 * @return Vrai si l'objet sp�cifi� est nul ou vaut z�ro ; Sinon Faux
	 */
	public static boolean isNullOrZero(final Object value) {

		final Integer intValue = tryParse(value);
		return intValue == null || DEFAULT_INTEGER.equals(intValue);
	}

	/**
	 * Convertit l'objet sp�cifi� en objet de type Integer
	 * 
	 * @param value
	 *            Valeur � convertir
	 * @return Valeur convertie
	 */
	private static Integer parse(final Object value) {

		if (value == null) {
			return null;
		}

		Integer returnValue;

		if (value instanceof String) {
			final String strVal = ((String) value).trim();
			if ("".equals(strVal)) {
				return null;
			} else {
				// Remplacement de " " par "" sinon on obtiendra z�ro
				final String s = StringHelper
						.removeAllWhitespaceCharacters(strVal);
				returnValue = Integer.valueOf(s);
			}
		} else if (value instanceof Integer) {
			returnValue = (Integer) value;
		} else if (value instanceof Double) {
			returnValue = ((Double) value).intValue();
		} else if (value instanceof BigDecimal) {
			returnValue = ((BigDecimal) value).intValue();
		} else if (value instanceof Long) {
			returnValue = ((Long) value).intValue();
		} else if (value instanceof Float) {
			returnValue = ((Float) value).intValue();
		} else {
			returnValue = null;
		}

		return returnValue;
	}

	/**
	 * Retourne une valeur al�atoire, comprise dans la liste des valeurs
	 * sp�cifi�es
	 * 
	 * @param values
	 *            Valeurs
	 * @return Valeur al�atoire
	 */
	public static int random(final int... values) {

		int result = 0;

		if (values != null) {

			final List<Integer> valueList = new ArrayList<Integer>();
			for (final int i : values) {
				valueList.add(i);
			}

			final Random randomGenerator = new Random();
			final int index = randomGenerator.nextInt(valueList.size());
			result = valueList.get(index);
		}

		return result;
	}

	/**
	 * Retourne une valeur al�atoire, comprise entre les valeurs sp�cifi�es
	 * 
	 * @param min
	 *            Minimum
	 * @param max
	 *            Maximum
	 * @return Valeur al�atoire (y compris min/max)
	 */
	public static int random(final int min, final int max) {

		int result;
		final Random rand = new Random();

		try {
			result = rand.nextInt(max - min + 1) + min;
		} catch (IllegalArgumentException e) {
			result = 0;
		}
		return result;
	}

	/**
	 * Calcule la somme des valeurs sp�cifi�es et retourne la r�sultat
	 * 
	 * @param values
	 *            Valeurs � sommer
	 * @return Somme des valeurs
	 */
	public static int sum(final Integer... values) {
		int result = 0;

		if (values != null) {
			for (final Integer v : values) {
				if (v != null) {
					result = result + v;
				}
			}
		}

		return result;
	}

	/**
	 * Essaie de convertir l'objet sp�cifi� en objet de type Integer
	 * 
	 * @param value
	 *            Valeur � convertir
	 * @return Valeur convertie
	 */
	public static Integer tryParse(final Object value) {

		return tryParse(value, DEFAULT_INTEGER);
	}

	/**
	 * Essaie de convertir l'objet sp�cifi� en objet de type Integer
	 * 
	 * @param value
	 *            Valeur � convertir
	 * @param defaultValue
	 *            Valeur par d�faut
	 * @return Valeur convertie ; Sinon valeur par d�faut
	 */
	public static Integer tryParse(final Object value,
			final Integer defaultValue) {

		Integer result = defaultValue;

		try {
			result = parse(value);
		} catch (NumberFormatException e) {
			result = defaultValue;
		}

		return result == null ? defaultValue : result;
	}

	/**
	 * Initialise une nouvelle instance priv�e de la classe IntegerHelper
	 */
	private IntegerHelper() {

	}
}
